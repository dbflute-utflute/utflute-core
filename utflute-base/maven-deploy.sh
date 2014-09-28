#!/bin/bash

# Base
mvn -e clean deploy

# Core
cd ../utflute-core
. maven-deploy.sh

# Mocklet
cd ../../utflute-mocklet/utflute-mocklet
. maven-deploy.sh

# Seasar
cd ../../utflute-seasar/utflute-seasar
. maven-deploy.sh

# Spring
cd ../../utflute-spring/utflute-spring
. maven-deploy.sh

cd ../utflute-spring-web
. maven-deploy.sh

# Guice
cd ../../utflute-guice/utflute-guice
. maven-deploy.sh

cd ../utflute-guice-web
. maven-deploy.sh