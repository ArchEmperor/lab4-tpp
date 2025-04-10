pipeline {
    agent any

    tools {
        maven 'Maven 3.8.1'
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/ArchEmperor/lab4-tpp.git'
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