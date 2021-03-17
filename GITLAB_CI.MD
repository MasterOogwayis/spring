# 1. .gitlab ci 脚本编写遇到过的坑 #

## .gitlab-ci 中有些命令无法使用问题 ##
```text
bash: line 80: mvn: command not found
ERROR: Job failed: exit status 1

或者其他找不到 JAVA_HOME 等变量或命令
```

## 解决方法有2个 ##

* 1.修改 gitlab-runner 的默认用户为 root
* 2. 给 gitlab-runner 的默认用户 gitlab-runner 授权

### 在安装maven及java时，一些环境变量是在~/.bashrc只添加的 ###
```text
export JAVA_HOME=/usr/lib/jvm/jdk1.8.0_221
export JRE_HOME=$JAVA_HOME/jre
export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib
export PATH=${JAVA_HOME}/bin:${JRE_HOME}/bin:$PATH

export M2_HOME=/opt/maven/apache-maven-3.6.1
export CLASSPATH=$CLASSPATH:$M2_HOME/lib
export PATH=$PATH:$M2_HOME/bin
```

而gitlab-runner是以gitlab-runner用户执行命令的，所以检测的mvn变量。
所以，只需要进行到/home/gitlab-runner下，编辑其下的.bashrc，
添加上面的命令，就可以正常执行了。

坑：在执行前已经预测到这问题了，所以特意把自己用户下的.bashrc只相关的环境变量添加到共用的/etc/bash.bashrc中了。在自己用户下可以正常使用命令，切换到gitlab-runner用户下时，也可正常使用mvn，但是就是gitlab-ci就是无法识别到mvn命令



# 2. 官方推荐的第二种方式 #

注册时使用 docker executor
```yaml
image: maven:latest
variables:
  MAVEN_CLI_OPTS: "--batch-mode --errors --fail-at-end --show-version -DinstallAtEnd=true -DdeployAtEnd=true"


stages:
  - test
  - deploy
  - dev
test:
  stage: test
  script:
#    - echo $JAVA_HOME
    - mvn $MAVEN_CLI_OPTS test-compile
  tags:
    - maven
  only:
    - master

deploy:
  stage: deploy
  script:
    - mvn clean package -T 1C -DskipTests -P nexus
    - sh /data/alt/navigation-api/kill.sh
    - find alt-navigation-api/target/*.jar -maxdepth 1 -type f ! -name '*-sources.jar' -exec cp {} /data/alt/navigation-api \\;
    - sh /data/alt/navigation-api/start.sh
  tags:
    - maven
  only:
    # 只在 master 上执行
    - master

dev:
  stage: dev
  script:
   - sh "提交信息匹配 deploy.* 才会执行"
  only:
      refs:
        - master
      variables:
        - $CI_COMMIT_TITLE =~ /^winsoe_wineventlog.*/
  tags:
    - cloud-test-1

```


## 3. 脚本 ##
在runner环境中和直接执行有些许区别，比如 使用 ps -ef|grep xxx|grep -v grep 等命令，最好要定位准确点，不要用模糊匹配


## 各种触发规则 ##
比如通过 commit message 关键字触发
* 1.例如下面，只在 master 分支且提交信息包含 "runfullpipeline" 关键字才会执行 job
* 2.详情见：<https://gitlab.com/gitlab-org/gitlab-foss/-/issues/39215>
```text
job:
  only:
    refs:
      - master  
        message:
          - /\[runfullpipeline\]/

```

具体 docs:
<https://docs.gitlab.com/13.7/ee/ci/yaml/>