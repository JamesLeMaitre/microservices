java -Dserver.port=9080 -jar /achat-ksu.jar --spring.profiles.active=prod &
sleep 20s &&
java -Dserver.port=9005 -jar /ksu.jar --spring.profiles.active=prod &
sleep 20s &&
java -Dserver.port=9002 -jar /agr.jar --spring.profiles.active=prod &
sleep 20s &&
java -Dserver.port=9013 -jar /formation.jar --spring.profiles.active=prod &
sleep 20s &&
java -Dserver.port=9003 -jar /avr.jar --spring.profiles.active=prod &
sleep 20s &&
java -Dserver.port=9010 -jar /contrat.jar --spring.profiles.active=prod &
sleep 20s &&
java -Dserver.port=9012 -jar /certification.jar --spring.profiles.active=prod &
sleep 20s &&
java -Dserver.port=9008 -jar /te.jar --spring.profiles.active=prod &
sleep 20s &&
java -Dserver.port=9006 -jar /membre.jar --spring.profiles.active=prod &
sleep 20s &&
java -Dserver.port=9001 -jar /achat-franchise.jar --spring.profiles.active=prod &
sleep 20s &&
java -Dserver.port=9004 -jar /franchise.jar --spring.profiles.active=prod &
sleep 20s &&
java -Dserver.port=9007 -jar /payement.jar --spring.profiles.active=prod &
sleep 20s &&
java -Dserver.port=9011 -jar /mobileMoneyCredit.jar --spring.profiles.active=prod
