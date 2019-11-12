# 体积小 只有2m 但是如果需要使用命令则需要单独安装 如下面的curl
FROM openjdk:8-jdk-alpine
MAINTAINER 	ShaoweiZhang<ShaoweiZhang2016@outlook.com>
#FROM java:8 这个太大 有600m
VOLUME /tmp
COPY hello-world-1.0-SNAPSHOT.jar helloworld.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/pro/./urandom", "-jar", "/helloworld.jar"]