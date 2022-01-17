# FROM alpine
FROM ubuntu

RUN apt update \
    && apt install openjdk-8-jre -y \
    && apt install mysql-server-8.0 -y

RUN service mysql start \
    && mysql -u root -proot -e "CREATE USER 'belka'@'localhost' IDENTIFIED BY 'belka';" \
    && mysql -u root -proot -e "CREATE DATABASE spring;" \
    && mysql -u root -proot -e "GRANT ALL PRIVILEGES ON *.* TO 'belka'@'localhost'; FLUSH PRIVILEGES;" \
    # && mysql -u root -proot -e "FLUSH PRIVILEGES;" \
    && cd ~ \
    && mkdir uploads

COPY spring-web-app-0.0.1-SNAPSHOT.jar /root/app.jar

EXPOSE 8080

CMD cd /root/ \
    && service mysql start \
    && chmod +x app.jar \
    && java -jar app.jar