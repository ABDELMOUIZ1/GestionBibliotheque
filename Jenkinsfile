pipeline {
    agent any
    tools {
        jdk 'jdk 20'       // Use the correct JDK name
        maven 'maven 3'    // Use the correct Maven name
    }
    environment {
        REPO_URL = 'https://github.com/ABDELMOUIZ1/GestionBibliotheque.git'
        SONARQUBE_CREDENTIALS_ID = 'sonar'
    }
    stages {
        stage('Clean Workspace') {
            steps {
                cleanWs()
            }
        }
        stage('Checkout from GitHub') {
            steps {
                git branch: 'main',
                    credentialsId: 'github',
                    url: 'https://github.com/ABDELMOUIZ1/GestionBibliotheque.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Quality Analysis') {
            steps {
                withSonarQubeEnv('sonar') {
                    sh 'mvn sonar:sonar'
                }
            }
        }
        stage('Deploy') {
            steps {
                echo 'Déploiement simulé réussi'
            }
        }
    }
    post {
        success {
            mail(
                to: 'abdelmoize1234@gmail.com',
                subject: 'Build Success',
                body: 'Le build a été complété avec succès.'
            )
        }
        failure {
            mail(
                to: 'abdelmoize1234@gmail.com',
                subject: 'Build Failed',
                body: 'Le build a échoué.'
            )
        }
    }
}
