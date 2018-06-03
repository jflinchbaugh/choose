FROM java:8-alpine
MAINTAINER Your Name <you@example.com>

ADD target/uberjar/choose.jar /choose/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/choose/app.jar"]
