#!/usr/bin/env groovy Jenkinsfile
//Jenkinsfile (Declarative Pipeline)
pipeline {
    agent any
    options {
        // 不允许同时执行流水线, 防止同时访问共享资源等
        disableConcurrentBuilds()
        // 显示具体的构建流程时间戳
        timestamps()
    }
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
        booleanParam(name: 'clearImages', defaultValue: false, description: '清理 废弃 none images')
        booleanParam(name: 'startServers', defaultValue: false, description: '启动全部服务')
        booleanParam(name: 'katalon', defaultValue: false, description: 'katalon API 接口测试')
        booleanParam(name: 'repository', defaultValue: false, description: '推送测试仓库')
        text(name: 'instances', defaultValue: '', description: '需要发布的服务,逗号分隔')
        string(name: "eurekaPort", defaultValue: "19101", description: "注册服务端口")
        string(name: "configPort", defaultValue: "19102", description: "配置服务端口")
        string(name: "adminPort", defaultValue: "19104", description: "admin服务端口")
        string(name: "gatewayPort", defaultValue: "17100", description: "gateway服务端口")
        string(name: "icGatewayPort", defaultValue: "19110", description: "ic gateway服务端口")
        string(name: "openGatewayPort", defaultValue: "19111", description: "open gateway服务端口")
        // 注意这个多选框需要 jenkins 插件 Extended Choice Parameter Plug-In
//        extendedChoice(
//                name: 'yourchoices',
//                description: 'pick your choices',
//                multiSelectDelimiter: ',',
//                quoteValue: false,
//                saveJSONParameterToFile: false,
//                type: 'PT_CHECKBOX',
//                value: '1,2,3,4,5',
//                visibleItemCount: 5
//        )
    }
    //环境变量，初始确定后一般不需更改
//    tools {
//        maven 'maven3'
//        jdk 'jdk8'
//    }
//    options {
//        //保持构建的最大个数
//        buildDiscarder(logRotator(numToKeepStr: '10'))
//    }
    //定期检查开发代码更新，工作日每晚4点做daily build
//    triggers {
//        pollSCM('H 4 * * 1-5')
//    }
    stages {
//        stage('编译：Compile') {
//            tools {
//                maven 'maven'
//            }
//            steps {
//                echo "Compile..."
////                sh 'mvn clean compile'
//            }
//        }
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
                        // 代码覆盖率低于 5% 则失败
                        jacoco changeBuildStatus: true, maximumLineCoverage: '5',
//                        execPattern: '**/**.exec',
//                        classPattern: '**/classes',
//                        sourcePattern: '**/src/main/java',
                                exclusionPattern: 'src/test/*'
                    } else {
                        echo '跳过 jUnit Test...'
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
//                        timeout(time: 1, unit: 'HOURS') {
                        // 等待 sonar 反馈 30min 超时
//                        timeout(30) {
//                            //利用sonar webhook功能通知pipeline代码检测结果，未通过质量阈，pipeline将会fail
//                            def qg = waitForQualityGate()
//                            if (qg.status != 'OK') {
//                                error "未通过Sonarqube的代码质量阈检查，请及时修改！failure: ${qg.status}"
//                            }
//                        }
                    } else {
                        echo '跳过 sonarQube......'
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
//                        archiveArtifacts '**/target/*.jar'
//                        fingerprint '**/target/*.jar'
                    } else {
                        echo '跳过 package'
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
                    } else {
                        echo '跳过 build docker Images...... '
                    }
                    if (params.clearImages) {
                        try {
                            echo '清理 images'
                            sh 'docker rmi $(docker images | grep "^<none>" | awk "{print $3}")'
                        } catch (Exception e) {
                            echo '忽略清理报错......'
                        }
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
                        echo '跳过 API test...'
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
                                def image = "bcpt/${instance.trim()}:${params.tag}"
                                echo image
                                docker.image(image).push()
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

        stage('backup') {
            steps{
                // 防止 jenkins 杀进程
//                withEnv(['JENKINS_NODE_COOKIE=Execute Shells do not kill me']) {
//                    for (String shell : shellsToExecute) {
//                        shell = shell.trim()
//                        sh "${shell}"
//                    }
//                }
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
