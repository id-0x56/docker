FROM node:lts-alpine

ENV TZ=Europe/London
RUN apk add --no-cache tzdata \
    && rm -rf /var/cache/apk/* /var/tmp/* \
    && ln -snf /usr/share/zoneinfo/$TZ /etc/localtime \
    && echo $TZ > /etc/timezone

RUN apk add --no-cache curl git

WORKDIR /var/www/nuxt-app

USER node

ENV HOST 0.0.0.0
EXPOSE 3000

CMD [ "sh", "-c", "rm -rf node_modules ; npm install ; npm run dev"]
