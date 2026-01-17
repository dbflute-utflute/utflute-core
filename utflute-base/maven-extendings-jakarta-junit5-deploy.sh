#!/bin/bash

# Core
cd ../utflute-core
. maven-deploy.sh $1

# not related to JUnit5
# Mocklet
#cd ../../utflute-mocklet/utflute-mocklet
#. maven-deploy.sh $1

# LastaFlute
cd ../../utflute-lasta/utflute-lasta-di
. maven-deploy.sh $1

cd ../utflute-lastaflute
. maven-deploy.sh $1
