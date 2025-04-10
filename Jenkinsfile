pipeline {
    agent any

    tools {
        maven 'Maven 3.9.9'
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