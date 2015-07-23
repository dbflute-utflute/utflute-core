#!/bin/bash

# Base
mvn -e clean deploy -Dgpg.keyname=$1

# Core
cd ../utflute-core
. maven-deploy.sh $1

# Mocklet
cd ../../utflute-mocklet/utflute-mocklet
. maven-deploy.sh $1

# Seasar
cd ../../utflute-seasar/utflute-seasar
. maven-deploy.sh $1

# Spring
cd ../../utflute-spring/utflute-spring
. maven-deploy.sh $1

cd ../utflute-spring-web
. maven-deploy.sh $1

# Guice
cd ../../utflute-guice/utflute-guice
. maven-deploy.sh $1

cd ../utflute-guice-web
. maven-deploy.sh $1

# LastaFlute
cd ../../utflute-lasta/utflute-lasta-di
. maven-deploy.sh $1

cd ../utflute-lastaflute
. maven-deploy.sh $1
