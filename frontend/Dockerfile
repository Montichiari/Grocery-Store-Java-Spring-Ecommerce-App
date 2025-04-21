FROM node:23-alpine3.20

RUN addgroup app && adduser -S -G app app

USER app

WORKDIR /app 

COPY package*.json .

USER root 

RUN chown -R app:app .

RUN npm install 

COPY . .

RUN npm run build 

EXPOSE 5173

CMD ["npm", "run", "preview"]