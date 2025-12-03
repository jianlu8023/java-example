FROM alpine:3.19 AS databuilder

RUN sed -i 's#dl-cdn.alpinelinux.org#mirrors.ustc.edu.cn#g' /etc/apk/repositories \
    && apk update \
    && apk add tzdata

RUN echo 'Asia/Shanghai' > /etc/timezone \
    && cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

FROM maven:3.6.3 AS builder

WORKDIR /app

COPY . .

RUN mvn -Dmaven.test.skip=true -DarchetypeCatalog=internal -Dfile.encoding=UTF-8 clean compile package

FROM amazoncorretto:8-alpine3.19-jdk
LABEL author=jianlu
#MAINTAINER jianlu

ENV LANG=C.UTF-8

USER root

COPY --from=databuilder /etc/localtime /etc/localtime
COPY --from=databuilder /etc/timezone /etc/timezone

WORKDIR /usr/share/app

COPY --from=builder /app/target/lib /usr/share/app/lib
COPY --from=builder /app/target/config /usr/share/app/config
COPY --from=builder /app/target/docker-deployment*.jar /usr/share/app/docker-deployment.jar
RUN mkdir -p /usr/share/app/logs

EXPOSE 8080

ENTRYPOINT ["java","-jar"]

CMD ["docker-deployment.jar"]
