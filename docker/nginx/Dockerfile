FROM nginx:latest

COPY ./conf /etc/nginx/conf.d
RUN chmod -R 664 /etc/nginx/conf.d

WORKDIR /var/www

EXPOSE 80 443
