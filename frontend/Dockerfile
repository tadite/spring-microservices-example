FROM nginx:1.16.0-alpine
RUN rm /etc/nginx/conf.d/default.conf
COPY conf /etc/nginx
COPY build /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]