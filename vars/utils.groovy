def buildWebApp() {
    echo 'Building web application'
    sh 'npm install'
    sh 'npm run build'

    echo 'Running linters'
    sh 'npm run lint'
}

def buildAPI() {
    echo 'Building API application'
    sh 'npm install'

    echo 'Running linters'
    sh 'npm run lint'
}

def buildDB() {
    echo 'Build skipped for database'
}

def testJavascript() {
    echo 'Testing web application'
    sh 'npm test'
}

def buildDocker(String registry, String repo, String tag) {
    echo 'Building Docker image'
    sh "docker build -t ${registry}/${repo}:beta ."
    sh "docker build -t ${registry}/${repo}:${tag} ."
    sh "docker build -t ${registry}/${repo}:latest ."
}

def conditionalDeployment(String branchName, String registry, String repo, String tag, String username, String password) {
    if (branchName == 'main') {
        // Push version tag and latest
        echo 'Logging into docker hub'
        sh "echo ${password} | docker login -u ${username} --password-stdin"

        echo 'Pushing Docker image'
        sh "docker push ${registry}/${repo}:${tag}"
        sh "docker push ${registry}/${repo}:latest"
    } else if (branchName.startsWith('release/')) {
        // Push beta tag
        echo 'Logging into docker hub'
        sh "echo ${password} | docker login -u ${username} --password-stdin"

        echo 'Pushing Docker image'
        sh "docker push ${registry}/${repo}:beta"
    } else {
        echo "Deployment skipped for branch: ${branchName}"
    }
}