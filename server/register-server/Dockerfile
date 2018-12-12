# 体积小 只有2m 但是如果需要使用命令则需要单独安装 如下面的curl
FROM openjdk:8-jdk-alpine
#FROM java:8 这个太大 有600m
VOLUME /tmp

ARG JAR_FILE

ADD ${JAR_FILE} app.jar

# 使用 docker 专用的 配置文件 不和代码冲突
ADD application_docker.yml application.yml

# 安装 curl 命令，用于健康检查访问 health 接口
#修改镜像源
RUN sed -i 's/dl-cdn.alpinelinux.org/mirrors.ustc.edu.cn/g' /etc/apk/repositories
RUN apk update
RUN apk add --no-cache curl
#时间插件，用于设置本地时间
RUN apk add --no-cache tzdata
#COPY docker-healthcheck /usr/local/bin/
#RUN chmod 777 /usr/local/bin/docker-healthcheck
# 健康检查 当检查成功后其他依赖容器才会启动 --- 脚本
#HEALTHCHECK --interval=10s CMD docker-healthcheck
# 健康检查配置放到 docker-compose 指定      --- 简单的 请求成功 200
#HEALTHCHECK CMD curl --fail http://localhost:1111/health

#RUN bash -c 'touch /app.jar'

#ENTRYPOINT ["java","-Djava.security.egd=file:/pro/./urandom","-jar","/app.jar"]

# docker镜像服务的内存不能全部给“-Xmx”，因为JVM消耗的内存不仅仅是Heap。
# JVM = Heap + Method Area +Constant Pool + Thread Stack * num of thread 。
# Xmx的值可设置为镜像上限减去150m或200m。当然还需要考虑应用自身的特点，比如class数目，并发线程数等
ENTRYPOINT ["java", "-Xms256m", "-Xmx256m", "-Djava.security.egd=file:/pro/./urandom", "-jar", "/app.jar"]