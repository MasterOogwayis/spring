#!/usr/bin/env groovy Jenkinsfile
//Jenkinsfile (Declarative Pipeline)
pipeline{
    agent any
    tools {
        maven 'maven'
        jdk 'jdk8'
    }
    stages{
        stage('编译：Compile') {
            steps {
                echo "Compile..."
                sh 'mvn clean compile'
            }
        }
        stage('单元测试：Test') {
            steps {
                echo 'Test....'
                sh 'mvn jacoco:prepare-agent test'
                // 聚合报告
                junit '**/target/surefire-reports/*.xml'
                jacoco changeBuildStatus: true, maximumLineCoverage: '5'
//                        execPattern: '**/**.exec',
//                        classPattern: '**/classes',
//                        sourcePattern: '**/src/main/java',
//                        exclusionPattern: 'src/test/*'
            }
        }
        stage('静态检查：SonarQube') {
            steps {
                echo "Run SonarQube..."
                script {
                    scannerHome = tool 'sonar scanner'
                }
                // 使用 jenkins 默认配置
                withSonarQubeEnv('sonar server') {
                    sh "${scannerHome}/bin/sonar-scanner"
                }
            }
        }
        stage('打包：Package') {
            steps {
                echo "Package..."
                sh 'mvn package -DskipTests'
                // 归档成品 记录指纹
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
            }
        }
        stage('打包docker镜像：DockerPackage') {
            steps {
                echo 'package docker images'
                sh 'mvn dockerfile:build -DskipTests'
                // 清除 所有废弃 none images
//                echo '清理 images'
//                sh 'docker rmi $(docker images | grep "^<none>" | awk "{print $3}") && exit 0'
            }
        }
        stage('发布测试环境: Deploy Test') {
            steps {
                echo 'Deploying Test env....'
//                sh 'docker-compose up -d'
            }
        }
        stage('接口测试：Api Test') {
            steps {
                echo 'Api Test....'
                sh 'katalon -propertiesFile="/data/katalon/auto-test-script/auto-test/Test Suites/TS1.properties" -runMode=console'
            }
        }
        stage('推送仓库：Docker push images') {
            steps {
                echo 'Pushing images....'
            }
        }
        stage('发布生产环境：Deploy Pro Env') {
            steps {
                echo 'Deploying pro env....'
            }
        }
    }
    post {
        success {
            echo 'SUCCESS......'
        }
        failure {
            echo 'FAILURE......'
        }
        unstable {
            echo 'UNSTABLE......'
        }
    }
}


//Jenkinsfile (Scripted Pipeline)
//node {
//    checkout scm
//    stage('编译：Compile') {
//        echo "Compile..."
//        // withEnv(["PATH+MAVEN=${tool 'maven'}/bin"])
//        withMaven(maven: 'maven') {
//            sh 'mvn clean compile'
//        }
//    }
//    stage('单元测试：Test') {
//        echo 'Test....'
//        withMaven(maven: 'maven') {
//            sh 'mvn jacoco:prepare-agent test'
//        }
//        // 聚合报告
//        junit '**/target/surefire-reports/*.xml'
//        jacoco hangeBuildStatus: true,
//                maximumLineCoverage: '5',
//                execPattern: '**/**.exec',
//                classPattern: '**/classes',
//                sourcePattern: '**/src/main/java',
//                exclusionPattern: 'src/test/*'
//    }
//    stage('静态检查：SonarQube') {
//        echo "Run SonarQube..."
//        def scannerHome = tool 'sonar scanner'
////        withSonarQubeEnv('sonar server')
//        // 使用 jenkins 默认配置
//        withSonarQubeEnv {
//            sh "${scannerHome}/bin/sonar-scanner"
//        }
//    }
//    stage('打包：Package') {
//        echo "Package..."
//        withMaven(maven: 'maven') {
//            sh 'mvn package -DskipTests'
//        }
//        // 归档成品 记录指纹
//        archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
//    }
//    stage('打包docker镜像：DockerPackage') {
//        echo 'package docker images'
//        withMaven(maven: 'maven') {
//            sh 'mvn dockerfile:build -DskipTests'
//        }
//        // 清除 所有废弃 none images
//        sh 'docker rmi $(docker images | grep "^<none>" | awk "{print $3}")'
//    }
//    stage('发布测试环境: Deploy Test') {
//        echo 'Deploying Test env....'
////        sh 'docker-compose up -d'
//    }
//    stage('接口测试：Api Test') {
//        echo 'Api Test....'
////        withEnv(["PATH+JDK=${tool 'jdk8'}/bin"]){
////            sh 'katalon -propertiesFile="/data/katalon/auto-test-script/auto-test/Test Suites/TS1.properties" -runMode=console'
////        }
//    }
//    stage('推送仓库：Docker push images') {
//        echo 'Pushing images....'
//    }
//    stage('发布生产环境：Deploy Pro Env') {
//        echo 'Deploying pro env....'
//    }
//}