pipeline {
  agent any
  stages {
    stage('Build project') {
      steps {
        sh 'mvn clean install'
      }
    }

    stage('Build docker image') {
      steps {
        sh 'echo $USER'
        sh 'docker image prune'
        sh 'docker build -t goncalvesgabrielsilva/controle-coleta:1.0 docker/'
      }
    }

    stage('Deploy aplicação') {
      steps {
        sh 'docker start goncalvesgabrielsilva/controle-coleta:1.0'
      }
    }

  }
  environment {
    JAVA_HOME = '/opt/jdk1.8.0_231/'
    PATH = "/opt/apache-maven-3.6.3/bin:$PATH"
  }
}