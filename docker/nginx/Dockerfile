FROM nginx:latest

COPY ./conf /etc/nginx/conf.d
RUN chmod -R 664 /etc/nginx/conf.d

WORKDIR /etc/nginx/conf.d

EXPOSE 80 443
