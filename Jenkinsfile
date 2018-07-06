#!/usr/bin/env groovy Jenkinsfile
//Jenkinsfile (Declarative Pipeline)
pipeline {
    agent any
    // 参数，可以在流水线启动前输入
    parameters {
        // tag 版本 artifactId
        string(name: "tag", defaultValue: "2.0-SNAPSHOT", description: "版本号")
        string(name: "dockerRepository", defaultValue: "192.168.1.12:18082", description: "docker仓库地址")
        choice(name: 'env', choices: 'dev\ntest\ncloud\npro', description: '环境')
        booleanParam(name: 'junitTest', defaultValue: false, description: '单元测试')
        booleanParam(name: 'sonarQube', defaultValue: false, description: '静态检查')
        booleanParam(name: 'package', defaultValue: false, description: '打包 package')
        booleanParam(name: 'buildImages', defaultValue: false, description: '构建 image，必须先打包')
        booleanParam(name: 'startServers', defaultValue: false, description: '启动全部服务')
        booleanParam(name: 'katalon', defaultValue: false, description: 'katalon API 接口测试')
        booleanParam(name: 'repository', defaultValue: false, description: '推送测试仓库')
        text(name: 'instances', defaultValue: '', description: '需要发布的服务,逗号分隔',)
        string(name: "eurekaPort", defaultValue: "19101", description: "注册服务端口")
        string(name: "configPort", defaultValue: "19102", description: "配置服务端口")
        string(name: "adminPort", defaultValue: "19104", description: "admin服务端口")
        string(name: "gatewayPort", defaultValue: "17100", description: "gateway服务端口")
        string(name: "icGatewayPort", defaultValue: "19110", description: "ic gateway服务端口")
        string(name: "openGatewayPort", defaultValue: "19111", description: "open gateway服务端口")
    }
    stages {
        stage('编译：Compile') {
            tools {
                maven 'maven'
            }
            steps {
                echo "Compile..."
//                sh 'mvn clean compile'
            }
        }
        stage('单元测试：Test') {
            tools {
                maven 'maven'
            }
            steps {
                script {
                    if (params.junitTest) {
                        sh 'mvn jacoco:prepare-agent test'
                        // 聚合报告
                        junit '**/target/surefire-reports/*.xml'
                        jacoco changeBuildStatus: true, deltaLineCoverage: '5',
//                        execPattern: '**/**.exec',
//                        classPattern: '**/classes',
//                        sourcePattern: '**/src/main/java',
                                exclusionPattern: 'src/test/*'
                    } else {
                        echo 'skip jUnit Test...'
                    }
                }
            }
        }
        stage('静态检查：SonarQube') {
            steps {
                script {
                    if (params.sonarQube) {
                        scannerHome = tool 'sonar scanner'
                        // 使用 jenkins 默认配置
                        withSonarQubeEnv('sonar server') {
                            sh "${scannerHome}/bin/sonar-scanner"
                        }
                    } else {
                        echo 'skip sonarQube......'
                    }
                }
            }
        }
        stage('打包：Package') {
            tools {
                maven 'maven'
            }
            steps {
                script {
                    if (params.package) {
                        echo "Package..."
                        sh 'mvn package -DskipTests'
                        // 归档成品 记录指纹
                        archiveArtifacts '**/target/*.jar'
                        fingerprint '**/target/*.jar'
                    } else {
                        echo '跳过打包'
                    }
                }
            }
        }
        stage('打包docker镜像：DockerPackage') {
            tools {
                maven 'maven'
            }
            steps {
                // 清除 所有废弃 none images
                script {
                    if (params.buildImages) {
                        echo 'build docker Images......'
                        sh 'mvn dockerfile:build -DskipTests'
                        try {
                            echo '清理 images'
                            sh 'docker rmi $(docker images | grep "^<none>" | awk "{print $3}")'
                        } catch (Exception e) {
                            echo '忽略清理报错......'
                        }
                    } else {
                        echo 'skip build docker Images...... '
                    }
                }
            }
        }
        stage('启动服务') {
            environment {
                START_ENV = "${params.env}"
                EUREKA_PORT = "${params.eurekaPort}"
                CONFIG_PORT = "${params.configPort}"
                ADMIN_PORT = "${params.adminPort}"
                GATEWAY_PORT = "${params.gatewayPort}"
                IC_GATEWAY_PORT = "${params.icGatewayPort}"
                OPEN_GATEWAY_PORT = "${params.openGatewayPort}"
            }
            steps {
                script {
                    if (params.startServers) {
                        echo "启动 ${params.env} 环境"
                        sh "docker-compose up -d"
                    } else {
                        echo '不启动 服务'
                    }
                }
            }
        }
        stage('接口测试：Api Test') {
            tools {
                jdk 'jdk8'
            }
            steps {
                script {
                    if (params.katalon) {
                        sh 'katalon -propertiesFile="/data/katalon/auto-test-script/auto-test/Test Suites/TS1.properties" -runMode=console'
                    } else {
                        echo 'skip API test...'
                    }
                }
            }
        }
        stage('推送本地仓库：Docker push images') {
            environment {
                // docker仓库地址
                DOCKER_REGISTRY_URL = "http://${params.dockerRepository}"
            }
            steps {
                script {
                    if (params.repository && params.instances != '') {
                        def instances = params.instances.split(",")
                        withDockerRegistry(url: "${params.dockerRepository}") {
                            for (String instance : instances) {
                                docker.image("bcpt/${instance.trim()}:${params.tag}").push()
                            }
                        }
                    } else {
                        echo '没有需要推送的 images'
                    }
                }
            }
        }
        stage('发布生产仓库...') {
            steps {
                echo '推送image到生产仓库'
            }
        }
        stage('更换新包...') {
            steps {
                echo '更换新包...'
            }
        }
    }
    // 需要设置操作用户的邮件地址
//    post {
//        success {
//            script {
//                wrap([$class: 'BuildUser']) {
//                    mail to: "${env.BUILD_USER_EMAIL}",
//                            subject: "PineLine '${JOB_NAME}' (${BUILD_NUMBER}) result",
//                            body: "${env.BUILD_USER}'s pineline '${JOB_NAME}' (${BUILD_NUMBER}) run success\n请及时前往${env.BUILD_URL} 进行查看"
//                }
//            }
//        }
//        failure {
//            script {
//                wrap([$class: 'BuildUser']) {
//                    mail to: "${env.BUILD_USER_EMAIL}",
//                            subject: "PineLine '${JOB_NAME}' (${BUILD_NUMBER}) result",
//                            body: "${env.BUILD_USER}'s pineline  '${JOB_NAME}' (${BUILD_NUMBER}) run failure\n请及时前往 ${env.BUILD_URL} 进行查看"
//                }
//            }
//
//        }
//        unstable {
//            script {
//                wrap([$class: 'BuildUser']) {
//                    mail to: "${env.BUILD_USER_EMAIL}",
//                            subject: "PineLine '${JOB_NAME}' (${BUILD_NUMBER})结果",
//                            body: "${env.BUILD_USER}'s pineline '${JOB_NAME}' (${BUILD_NUMBER}) run unstable\n请及时前往${env.BUILD_URL}进行查看"
//                }
//            }
//        }
//    }
}
