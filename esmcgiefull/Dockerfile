#FROM java:8

FROM openjdk:17-jdk-alpine
VOLUME /tmp

copy demandeachatbanksu/target/demandeAchatBanKsu-0.0.1-SNAPSHOT.jar achat-ksu.jar
copy gestionksu/target/gestionKsu-0.0.1-SNAPSHOT.jar ksu.jar
copy gestionAgr/target/gestionAgr-0.0.1-SNAPSHOT.jar agr.jar
copy gestionformation/target/gestionformation-0.0.1-SNAPSHOT.jar formation.jar
copy gestionavr/target/gestionAvr-0.0.1-SNAPSHOT.jar avr.jar
copy gestionContrat/target/gestionContrat-0.0.1-SNAPSHOT.jar contrat.jar
copy gestionCertification/target/gestionCertification-0.0.1-SNAPSHOT.jar certification.jar
copy gestionte/target/gestionTe-0.0.1-SNAPSHOT.jar te.jar
copy gestionmembre/target/gestionMembre-0.0.1-SNAPSHOT.jar membre.jar
copy gestionachatfranchise/target/gestionAchatFranchise-0.0.1-SNAPSHOT.jar achat-franchise.jar
copy gestionFranchise/target/gestionFranchise-0.0.1-SNAPSHOT.jar franchise.jar
copy gestionpayement/target/gestionPayement-0.0.1-SNAPSHOT.jar payement.jar
copy mobileMoneyCredit/target/mobileMoneyCredit-0.0.1-SNAPSHOT.jar mobileMoneyCredit.jar

copy server_wrapper_script.sh server_wrapper_script.sh

RUN ["chmod", "+x", "server_wrapper_script.sh"]

CMD ./server_wrapper_script.sh

#CMD ["java", "-jar", "/achat-ksu.jar", "--spring.profiles.active=prod"]
#CMD ["java", "-jar", "/ksu.jar", "--spring.profiles.active=prod"]
#CMD ["java", "-jar", "/agr.jar", "--spring.profiles.active=prod"]
#CMD ["java", "-jar", "/avr.jar", "--spring.profiles.active=prod"]
#CMD ["java", "-jar", "/contrat.jar", "--spring.profiles.active=prod"]
#CMD ["java", "-jar", "/te.jar", "--spring.profiles.active=prod"]
#CMD ["java", "-jar", "/membre.jar", "--spring.profiles.active=prod"]
#CMD ["java", "-jar", "/achat-franchi se.jar", "--spring.profiles.active=prod"]
#CMD ["java", "-jar", "/franchise.jar", "--spring.profiles.active=prod"]

EXPOSE 9080
EXPOSE 9005
EXPOSE 9002
EXPOSE 9003
EXPOSE 9013
EXPOSE 9010
EXPOSE 9012
EXPOSE 9008
EXPOSE 9006
EXPOSE 9001
EXPOSE 9004
EXPOSE 9007
EXPOSE 9011

#ADD gestion-ksu-service-0.0.1-SNAPSHOT.jar gestion-ksu.jar
#CMD ["java", "-jar", "/gestion-ksu.jar", "--spring.profiles.active=prod"]
#EXPOSE 8080
#RUN sh -c 'touch /gestion-ksu.jar'
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/gestion-ksu.jar"]