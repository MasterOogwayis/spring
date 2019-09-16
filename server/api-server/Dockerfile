FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD target/api-server-1.0-SNAPSHOT.jar api.jar
#RUN bash -c 'touch /core.jar'
ENTRYPOINT ["java", "-Xms256m", "-Xmx512m", "-Djava.security.egd=file:/pro/./urandom", "-jar", "/api.jar"]
EXPOSE 1111