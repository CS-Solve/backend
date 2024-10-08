FROM openjdk:11

COPY build/libs/server-0.0.1-SNAPSHOT.jar server.jar
COPY pinpoint-agent-2.2.3-NCP/ /usr/local

ENV TZ=Asia/Seoul
ENV SPRING_PROFILES_ACTIVE=deploy

ENTRYPOINT ["java","-jar", \
"-javaagent:/usr/local/pinpoint-bootstrap-2.2.3-NCP-RC1.jar", \
"-Dpinpoint.applicationName=ComSsa-server", \
"-Dpinpoint.agentId=ComSsa-Id", \
"/server.jar"]