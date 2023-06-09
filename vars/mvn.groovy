def call() {
    try {
        sh "mvn test"
        sh "mvn -DskipTests clean package"
        archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
    }
    finally {
        junit 'target/surefire-reports/*.xml'
    }
}