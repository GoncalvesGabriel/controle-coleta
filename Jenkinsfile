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
    PATH = '/opt/maven/bin:$PATH'
  }
}