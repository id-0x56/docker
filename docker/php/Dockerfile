FROM php:fpm-alpine

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

COPY ./conf /usr/local/etc/php/conf.d
RUN chmod -R 664 /usr/local/etc/php/conf.d

RUN apk add --no-cache curl git \
    && docker-php-ext-install pdo pdo_mysql

RUN curl -sS https://getcomposer.org/installer | php -- --install-dir=/usr/local/bin --filename=composer

WORKDIR /var/www/laravel-app

USER $USERNAME

EXPOSE 9000
