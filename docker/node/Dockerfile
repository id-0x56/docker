FROM node:lts-alpine

RUN apk update \
    && apk add --no-cache curl git

WORKDIR /var/www/nuxt-app

ENV HOST 0.0.0.0
EXPOSE 3000

CMD [ "sh", "-c", "rm -rf node_modules ; /usr/local/bin/npm install ; /usr/local/bin/npm start"]
