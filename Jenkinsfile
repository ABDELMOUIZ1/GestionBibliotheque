pipeline {
    agent any
    tools {
            jdk 'jdk21'
            maven 'maven'
    }
    environment {
            REPO_URL = 'git remote add origin https://github.com/ABDELMOUIZ1/GestionBibliotheque.git'
            SONARQUBE_CREDENTIALS_ID = 'sonar'
    }
    stages {
         stage('clean work-space') {
                    steps {
                        cleanWs()
                    }
         }
         stage('Checkout from github') {
                     steps {
                         git branch: 'main',
                         credentialsId: 'github',
                         url: 'https://github.com/ABDELMOUIZ1'
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
                withSonarQubeEnv(installationName: 'sonar' , credentialsId: SONARQUBE_CREDENTIALS_ID) {
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