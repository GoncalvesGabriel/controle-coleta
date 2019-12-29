pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh '''mvn clean install -Dlicense.skip=true
'''
      }
    }

  }
  environment {
    MAVEN_HOME = '/opt/maven'
  }
}