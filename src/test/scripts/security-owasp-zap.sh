#!/bin/bash

##
## This script automatically tests the application built by penetration methods. The tool used is ZAP, from the OWASP organization.
# See: https://github.com/zaproxy/zaproxy
##

set -e

echo -e "\n\033[1m🤗 Starting our new container '$CONTAINER_NAME'...\033[0m"

# Start our application in background
docker run -d $CONTAINER_NAME

echo -e "\n\033[1m🔥 Trying to kill the beast...\033[0m"

# Attacking the app with active scanning (time & CPU consuming)
# See: https://github.com/zaproxy/zaproxy/wiki/ZAP-Baseline-Scan
docker run -t owasp/zap2docker-weekly zap-full-scan.py -t http://172.17.0.2:8080/

echo -e "\n\033[1m🚮 Picking up everything...\033[0m"

# Kill the app server
docker kill $(docker ps | grep $CONTAINER_NAME | awk '{print $1}')
