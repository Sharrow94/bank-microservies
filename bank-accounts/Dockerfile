#Start with a base image containing Java runtime
FROM openjdk:17-alpine as build

#Information around who maintains the image
MAINTAINER sharro94

# Add the application's jar to the container
COPY target/bank-accounts-0.0.1-SNAPSHOT.jar bank-accounts-0.0.1-SNAPSHOT.jar

#execute the application
ENTRYPOINT ["java","-jar","/bank-accounts-0.0.1-SNAPSHOT.jar"]