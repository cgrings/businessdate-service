#!/bin/bash
set -e

exec java -Djava.security.egd=file:/dev/./urandom -Djava.net.preferIPv4Stack=true -Djava.net.preferIPv4Addresses=true -jar /app.jar
