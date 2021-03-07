#!/bin/bash

# Base
mvn -e clean deploy -Dgpg.keyname=$1

# Core
cd ../utflute-core
. maven-deploy.sh $1

# LastaFlute
cd ../../utflute-lasta/utflute-lasta-di
. maven-deploy.sh $1

cd ../utflute-lastaflute
. maven-deploy.sh $1
