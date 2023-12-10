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

# ENV JAVA_TOOL_OPTIONS=-agentlib:jdwp=transport=dt_socket,address=*:5005,server=y,suspend=n
RUN apk add --no-cache curl git maven

RUN curl --location https://services.gradle.org/distributions/gradle-8.5-bin.zip --output-dir /opt --create-dirs --remote-name \
    && unzip /opt/gradle-*-bin.zip -d /opt \
    && rm -rf /opt/gradle-*-bin.zip \
    && ln -s /opt/gradle-*/bin/gradle /usr/bin/gradle

WORKDIR /var/www/java-app

USER $USERNAME

ENTRYPOINT [ "tail", "-f", "/dev/null" ]

# ENTRYPOINT [ "sh", "-c", "mvn package ; java -jar /var/www/java-app/target/rest-service-0.1.0.jar -Djava.net.preferIPv4Stack=true" ]

# java -jar /var/www/java-app/target/rest-service-0.1.0.jar -Djava.net.preferIPv4Stack=true
