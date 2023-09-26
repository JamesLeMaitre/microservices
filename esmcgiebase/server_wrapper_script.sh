java -Dserver.port=8761 -jar /discovery.jar --spring.profiles.active=prod &
sleep 20s &&
java -Dserver.port=8888 -jar /gateway.jar --spring.profiles.active=prod &
sleep 20s &&
java -Dserver.port=9999 -jar /config.jar --spring.profiles.active=prod &
sleep 30s &&
java -Dserver.port=8081 -jar /security.jar --spring.profiles.active=prod