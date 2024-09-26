FROM openjdk:11

COPY build/libs/server-0.0.1-SNAPSHOT.jar server.jar

ENV TZ=Asia/Seoul
ENV SPRING_PROFILES_ACTIVE=deploy

ENTRYPOINT ["java","-jar", \
"/server.jar"]