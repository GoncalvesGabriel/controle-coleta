pipeline {
  agent any
  
  environment {
    JAVA_HOME="/opt/jdk1.8.0_231/"
    PATH = "/opt/maven/bin:$PATH"
  }
  
  stages {
    stage('Build') {
      steps {
        sh "mvn clean install"
      }
    }
  }
}
