def call() {
    try {
        def mvnHome = tool 'MAVEN3'
        sh "${mvnHome}/bin/mvn test"
        sh "${mvnHome}/bin/mvn -DskipTests clean package"
        archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
    }
    finally {
        junit 'target/surefire-reports/*.xml'
    }
}