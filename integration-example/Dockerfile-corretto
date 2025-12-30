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
COPY --from=builder /app/target/*.jar /usr/share/app/app.jar

RUN mkdir -p /usr/share/app/logs

EXPOSE 8080

VOLUME /usr/share/app/config
VOLUME /usr/share/app/logs

#HEALTHCHECK --interval=60s --timeout=5s --retries=3 CMD echo $(wget --no-check-certificate --post-data '' -O - -q https://192.168.58.110:8080/integration/status/ping) | grep -oP '"code":\s*\K\d+' | grep -q "200" || exit 1

#HEALTHCHECK --interval=60s --timeout=5s --retries=3 CMD echo $(wget --no-check-certificate --post-data '' -O - -q https://192.168.58.110:8080/integration/status/ping) | grep -o '"code":[[:space:]]*[0-9]\+' | grep -o '[0-9]\+' | grep -q "200"

HEALTHCHECK --interval=60s --timeout=5s --retries=3 \
	CMD echo $(wget --no-check-certificate --post-data '' -O - -q https://localhost:8090/integration/status/ping) |  sed -n 's/.*"code":[[:space:]]*\([0-9][0-9]*\).*/\1/p' | grep -q "200" || exit 1


ENTRYPOINT ["java","-jar","app.jar"]
