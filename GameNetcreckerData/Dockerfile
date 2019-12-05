FROM openjdk:11
EXPOSE 8080
ARG JAR_FILE=target/GameNetcreckerData-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} GameNetcreckerData.jar
ADD default.conf /etc/nginx/conf.d/default.conf
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/GameNetcreckerData.jar"]
