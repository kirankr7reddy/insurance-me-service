pipeline {
  agent any

  environment {
    DOCKERHUB_USER = 'kiranreddykr7'
    IMAGE_NAME = 'insureme-policy-service'
    IMAGE_TAG = "latest"
    DOCKER_IMAGE = "${DOCKERHUB_USER}/${IMAGE_NAME}:${IMAGE_TAG}"
  }

  tools { maven 'Maven 3' }

  stages {
    stage('Checkout') {
      steps { checkout scm }
    }

    stage('Build & Test') {
      steps {
        sh 'mvn -B clean package -DskipTests'
      }
    }

    stage('Docker Build') {
      steps {
        sh "docker build -t ${DOCKER_IMAGE} ."
      }
    }

    stage('Docker Push') {
      steps {
        withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
          sh 'echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin'
          sh "docker push ${DOCKER_IMAGE}"
        }
      }
    }

    stage('Deploy with Ansible') {
      steps {
        // Use the exact command you run manually
        sh 'ansible-playbook -i ~/hosts.ini setup.yml'
      }
    }
  }

  post {
    success { echo 'Pipeline succeeded ✅' }
    failure { echo 'Pipeline failed ❌' }
  }
}

