FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD target/api-server-1.0-SNAPSHOT.jar api.jar
#RUN bash -c 'touch /api.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/pro/./urandom","-jar","/api.jar"]
EXPOSE 1111