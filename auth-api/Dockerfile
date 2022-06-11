FROM node:14
WORKDIR .
COPY package*.json ./
RUN yarn 
COPY . .
EXPOSE 8080
CMD ["nome", "app.js"]