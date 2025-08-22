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
    echo 'Building database application'
    // todo
}

def testJavascript() {
    echo 'Testing web application'
    sh 'npm test'
}

def runStaticScan() {
    // todo
}

def buildDocker(String registry, String repo, String tag) {
    echo 'Building Docker image'
    sh "docker build -t ${registry}/${repo}:${tag} ."
    sh "docker build -t ${registry}/${repo}:latest ."
}

def pushDocker(String registry, String repo, String tag, String username, String password) {
    echo 'Logging into docker hub'
    sh "echo ${password} | docker login -u ${username} --password-stdin"

    echo 'Pushing Docker image'
    sh "docker push ${registry}/${repo}:${tag}"
    sh "docker push ${registry}/${repo}:latest"
}

def conditionalDeployment(String branchName) {
    if (branchName == 'main') {
        echo "todo main"
    } else if (branchName.startsWith('release/')) {
        echo "todo release"
    } else if (branchName == 'develop') {
        echo "todo develop"
    }
    echo "Deployment skipped"
}