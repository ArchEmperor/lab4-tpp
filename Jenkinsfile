pipeline {
    agent any

    tools {
        maven 'maven'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn package'
            }
        }
    }

    post {
        success {
            echo '✅ Збірка пройшла успішно!'
        }
        failure {
            echo '❌ Помилка під час збірки!'
        }
    }
}