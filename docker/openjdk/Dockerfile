FROM openjdk:19-jdk-alpine

ARG USERNAME=alpine
ARG USER_UID=1000
ARG USER_GID=$USER_UID
RUN addgroup --system --gid $USER_GID $USERNAME \
    && adduser --system --uid $USER_UID --ingroup $USERNAME $USERNAME

ENV TZ=Europe/London
RUN apk add --no-cache tzdata \
    && rm -rf /var/cache/apk/* /var/tmp/* \
    && ln -snf /usr/share/zoneinfo/$TZ /etc/localtime \
    && echo $TZ > /etc/timezone

RUN apk add --no-cache curl git maven

WORKDIR /var/www/java-app

USER $USERNAME

ENTRYPOINT [ "tail", "-f", "/dev/null" ]
