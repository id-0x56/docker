FROM node:lts-alpine

ENV TZ=Europe/London
RUN apk add --no-cache tzdata \
    && rm -rf /var/cache/apk/* /var/tmp/* \
    && ln -snf /usr/share/zoneinfo/$TZ /etc/localtime \
    && echo $TZ > /etc/timezone

RUN apk add --no-cache git curl

WORKDIR /var/www/nuxt-app

USER node

ENV HOST 0.0.0.0
ENV NODE_TLS_REJECT_UNAUTHORIZED 0

EXPOSE 3000

CMD [ "sh", "-c", "rm -rf node_modules ; yarn install ; yarn run dev" ]
