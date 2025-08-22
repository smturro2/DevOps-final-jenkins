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
}

def pushDocker(String registry, String repo, String tag) {
    echo 'Pushing Docker image'
    sh "docker push ${registry}/${repo}:${tag}"
}

def conditionalDeployment(String branchName) {
    if (branchName == 'main') {
        // todo
        return
    } else if (branchName.startsWith('release/')) {
        // todo
        return
    } else if (branchName == 'develop') {
        // todo
        return
    }
    echo "Deployment skipped"
}