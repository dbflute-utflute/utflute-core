#!/bin/bash

# Base
mvn -e clean deploy -Dgpg.keyname=$1
