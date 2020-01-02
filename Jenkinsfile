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
        sh 'docker image prune -f -a'
        sh 'docker build -t goncalvesgabrielsilva/controle-coleta:1.0 docker/'
      }
    }

    stage('Deploy App') {
      steps {
        sh '''
docker container stop $(docker container ls -aq)'''
        sh 'docker container prune -f'
        sh 'docker run goncalvesgabrielsilva/controle-coleta:1.0 aplicacao > log.txt &'
      }
    }

  }
  environment {
    JAVA_HOME = '/opt/jdk1.8.0_231/'
    PATH = "/opt/apache-maven-3.6.3/bin:$PATH"
  }
}