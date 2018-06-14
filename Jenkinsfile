//pipeline{
//    agent any
//    stages{
//        stage('Build'){
//            steps {
//                echo "Building..."
//            }
//        }
//        stage('Test') {
//            steps {
//                echo "Testing..."
//            }
//        }
//        stage('Deploy') {
//            steps {
//                echo "Deploying..."
//            }
//        }
//    }
//}
node {
    stage('Build') {
        echo 'Building....'
        sh 'mvn clean package'
    }
    stage('Test') {
        echo 'Building....'
        sh 'mvn test'
    }
    stage('Package') {
        echo 'package docker images'
        sh 'mvn dockerfile:build'
    }
    stage('Deploy') {
        echo 'Deploying....'
        sh 'docker-compose up -d'
    }
}