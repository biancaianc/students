FROM adoptopenjdk/openjdk11:alpine-jre
COPY target/students-0.0.1-SNAPSHOT.jar students.jar
ENTRYPOINT ["java","-jar","/students.jar"]