#FROM java:8

FROM openjdk:8-jdk-alpine
VOLUME /tmp

copy discovery/target/discovery-0.0.1-SNAPSHOT.jar discovery.jar
copy gateway/target/gateway-0.0.1-SNAPSHOT.jar gateway.jar
copy config/target/config-0.0.1-SNAPSHOT.jar config.jar
copy security-service/target/security-service-0.0.1-SNAPSHOT.jar security.jar
copy server_wrapper_script.sh server_wrapper_script.sh

RUN ["chmod", "+x", "server_wrapper_script.sh"]

CMD ./server_wrapper_script.sh

EXPOSE 8761
EXPOSE 8888
EXPOSE 9999
EXPOSE 8081
#ADD gestion-ksu-service-0.0.1-SNAPSHOT.jar gestion-ksu.jar
#CMD ["java", "-jar", "/gestion-ksu.jar", "--spring.profiles.active=prod"]
#EXPOSE 8080
#RUN sh -c 'touch /gestion-ksu.jar'
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/gestion-ksu.jar"]