FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD register-server-1.0-SNAPSHOT.jar register.jar
#RUN bash -c 'touch /register.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/register.jar"]
EXPOSE 1111