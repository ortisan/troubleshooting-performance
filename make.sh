#!/bin/sh

#https://stackoverflow.com/questions/2870992/automatic-exit-from-bash-shell-script-on-error
abort()
{
    echo >&2 '
***************
*** ABORTED ***
***************
'
    echo "An error occurred. Exiting..." >&2
    exit 1
}

trap 'abort' 0

set -e

## Building app
mvn clean compile package
docker build -t tentativafc/troubleshooting-performance:1.0.0-snapshot -f Dockerfile .
docker push tentativafc/troubleshooting-performance:1.0.0-snapshot

# Start docker containers. Obs: the services will build Dockerfile
docker-compose up --build -d
cd -

trap : 0

echo >&2 '
************
*** DONE ***
************
'