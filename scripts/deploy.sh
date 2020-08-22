#!/usr/bin/env bash

echo Create directory...
ssh -i ~/.ssh/bookcrossby.pem ubuntu@3.135.204.233 \
  'rm -r ~/bookcrossby ~/uploads; mkdir ~/bookcrossby ~/uploads; chmod 777 ~/bookcrossby'
echo Copy files...
scp -i ~/.ssh/bookcrossby.pem ~/github/spring-web-app/target/spring-web-app-0.0.1-SNAPSHOT.jar \
    ubuntu@3.135.204.233:~/bookcrossby/bookcrossby.jar

echo Stop java...
ssh -i ~/.ssh/bookcrossby.pem ubuntu@3.135.204.233 \
    'pgrep java | xargs kill -9'
echo Run application...
ssh -i ~/.ssh/bookcrossby.pem ubuntu@3.135.204.233 \
    'nohup java -jar ~/bookcrossby/bookcrossby.jar'