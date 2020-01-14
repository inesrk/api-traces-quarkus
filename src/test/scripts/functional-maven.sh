#!/bin/bash

##
## This script tests the application based on the JUnit5 tests written in the Quarkys application.
##

set -e

echo -e "\n\033[1m⏳ Starting the database 'elastic/elasticsearch:$ELK_VERSION'...\033[0m"

# Start the database in background
docker run -d -p 9200:9200 -e "discovery.type=single-node" elastic/elasticsearch:$ELK_VERSION

echo -e "\n\033[1m🍽 Testing the app...\033[0m"

# Test the app
./mvnw verify

echo -e "\n\033[1m🌪 Cleaning up...\033[0m"

# Kill the app server
docker kill $(docker ps | grep elasticsearch | awk '{print $1}')
