FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD target/simple-1.0-SNAPSHOT.jar simple.jar
#RUN bash -c 'touch /api.jar'
ENTRYPOINT ["java", "-Xms256m", "-Xmx512m", "-Djava.security.egd=file:/pro/./urandom", "-jar", "/simple.jar"]
EXPOSE 8080