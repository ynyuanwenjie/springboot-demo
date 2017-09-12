FROM openjdk:8-jdk-alpine
MAINTAINER yuanwenjie <ynyuanwenjie@gmail.com>
VOLUME /tmp
WORKDIR /tmp
COPY . /tmp/
RUN ["mvn", "dependency:resolve"]
RUN ["mvn", "verify"]
RUN ["mvn", "clean package"]
EXPOSE 8116
EXPOSE 8080
CMD ["java", "-jar", "target/springbootdemo-0.0.1-SNAPSHOT.jar.jar"]