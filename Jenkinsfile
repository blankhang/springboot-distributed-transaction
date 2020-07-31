#!/usr/bin/env groovy
pipeline {

    agent any
    // using the Timestamps plugin we can add timestamps to the console log
    options {
        timestamps()
    }


    // 参数
    parameters {

        choice(
                name: 'profile',
                choices: ['dev','test','prod'],
                description: '选择要部署的配置文件'
        )

    }

    stages {

          stage('初始化参数') {

                environment {
                      GROUPID = readMavenPom().getGroupId()//com.blankhang
                      ARTIFACTID = readMavenPom().getArtifactId()//springboot-distributed-transaction
                      VERSION = readMavenPom().getVersion()//1.0

                      ALI_REGISTRY = "reg-aliyun"
                      ALI_HOST = "registry-vpc.cn-shenzhen.aliyuncs.com"
                      ALI_LOGIN_REGISTRY = "https://${ALI_HOST}"
                      BUILD_PREFIX = "storlead"
                      ALI_IMG_NAME = "${ALI_HOST}/${BUILD_PREFIX}/${ARTIFACTID}-${params.profile}:${VERSION}"
                      DOCKER_RUN_IMG_NAME_WITHOUT_VERSION = "${ALI_HOST}/${BUILD_PREFIX}/${ARTIFACTID}"
                      SERVER_PORT = 8002
                      SERVER_REMOTE_DEBUG_PORT = 8003
                      SERVER_SSH_PORT = 53023
                      MODULE_NAMESPACE_RESTFUL = "system"
                      SWARM_INIT_REPLICAS_NUM  = 1
                      node = "test.s.com"
                  }
          }

           stage('处理参数') {

                    when {
                        environment name: 'buildApi', value: 'true'
                    }
                    steps {
                        script {
                            echo "为指定module 设定对应端口"
                            script {
                                if (params.profile == "dev") {
                                    SERVER_PORT = 8211
                                    SERVER_REMOTE_DEBUG_PORT = 8212
                                    ALI_HOST = "registry.cn-shenzhen.aliyuncs.com"
                                    ALI_LOGIN_REGISTRY = "https://${ALI_HOST}"
                                    ALI_IMG_NAME = "${ALI_HOST}/${BUILD_PREFIX}/${ARTIFACTID}-${params.profile}:${VERSION}"
                                    pushDockImg = false
                                }
                                if (params.profile == "test") {
                                    SERVER_PORT = 8213
                                    SERVER_REMOTE_DEBUG_PORT = 8214
                                    node = "test.storlead.com"
                                    pushDockImg = true
                                }
                                if (params.profile == "prod") {
                                    SERVER_PORT = 8215
                                    SERVER_REMOTE_DEBUG_PORT = 8216
                                    node = "node1.storlead.com"
                                    pushDockImg = true
                                }
                            }
                        }
                    }
          }
    }


    post {
        success {
            emailext (
                    subject: "【Jenkins 构建成功通知】${env.JOB_NAME} ${params.profile} 在:${env.JENKINS_URL}上 第 [${env.BUILD_NUMBER}] 次",
                    body: """
                    详情：
                    SUCCESSFUL:'${env.JOB_NAME} ${params.profile} [${env.BUILD_NUMBER}]'
                    状态：${env.JOB_NAME} jenkins 构建成功
                    URL ：${env.BUILD_URL}
                    构建次数：${env.BUILD_NUMBER}
                    项目名称 ：${env.JOB_NAME}
                    项目环境: ${params.profile}
                    """,
                    to: "blank@storlead.com",
                    recipientProviders: [[$class: 'DevelopersRecipientProvider']]
            )
        }
        failure {
            emailext (
                    subject: "【Jenkins 构建失败通知】${env.JOB_NAME} ${params.profile} 在:${env.JENKINS_URL}上 第 [${env.BUILD_NUMBER}] 次",
                    body: """
                    详情：
                    FAILED:'${env.JOB_NAME} ${params.profile} [${env.BUILD_NUMBER}]'
                    状态：${env.JOB_NAME} jenkins 构建失败
                    URL ：${env.BUILD_URL}
                    构建次数：${env.BUILD_NUMBER}
                    项目名称 ：${env.JOB_NAME}
                    项目环境: ${params.profile}
                    """,
                    to: "blank@storlead.com",
                    recipientProviders: [[$class: 'DevelopersRecipientProvider']]
            )
        }
    }
}