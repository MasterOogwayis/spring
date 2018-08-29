FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD target/register-server-1.0-SNAPSHOT.jar eureka-server.jar

# 安装 curl 命令，用于健康检查访问 health 接口
#修改镜像源
RUN sed -i 's/dl-cdn.alpinelinux.org/mirrors.ustc.edu.cn/g' /etc/apk/repositories
RUN apk update
RUN apk add --no-cache curl
RUN apk add --no-cache tzdata

#RUN bash -c 'touch /eureka-server.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/pro/./urandom","-jar","/eureka-server.jar"]
EXPOSE 1111