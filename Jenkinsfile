pipeline {
  agent any
  
  environment {
    JAVA_HOME="/opt/jdk1.8.0_231/"
    PATH = "/opt/apache-maven-3.6.3/bin:$PATH"
  }
  
  stages {
    stage('Build') {
      steps {
        sh "mvn clean install"
      }
    }
  }
}
