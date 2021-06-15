FROM php:fpm

COPY ./conf /usr/local/etc/php/conf.d
RUN chmod -R 664 /usr/local/etc/php/conf.d

RUN apt-get update \
    && apt-get install -y curl \
    && docker-php-ext-install pdo pdo_mysql

RUN curl -sS https://getcomposer.org/installer | php -- --install-dir=/usr/local/bin --filename=composer

WORKDIR /var/www

EXPOSE 9000
