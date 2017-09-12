FROM openjdk:8-jdk-alpine
MAINTAINER yuanwenjie <ynyuanwenjie@gmail.com>
ENV JAVA_OPTS=""
ADD target/springbootdemo-0.0.1-SNAPSHOT.jar springbootdemo.jar
VOLUME /tmp
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /springbootdemo.jar" ]

