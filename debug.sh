#!/bin/bash

mvn package && \
sls invoke local -f main

