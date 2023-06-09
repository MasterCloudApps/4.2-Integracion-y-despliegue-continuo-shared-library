def call() {

    def mvnHome
    
    stage('Tooling') {
        mvnHome = tool 'MAVEN3'
    }

    stage('Test') {
        try {
            sh "${mvnHome}/bin/mvn test"
        }
        finally {
            junit 'target/surefire-reports/*.xml'
        }
    }

    stage('Package') {
        sh "${mvnHome}/bin/mvn -DskipTests clean package"
        archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
    }
}