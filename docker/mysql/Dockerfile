FROM mysql:latest

COPY ./conf /etc/mysql/conf.d
RUN chmod -R 444 /etc/mysql/conf.d

WORKDIR /var/lib/mysql

EXPOSE 3306
