FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD target/register-server-1.0-SNAPSHOT.jar eureka-server.jar
#RUN bash -c 'touch /eureka-server.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/pro/./urandom","-jar","/eureka-server.jar"]
EXPOSE 1111