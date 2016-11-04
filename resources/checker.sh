#!/bin/sh

TIMESTAMP=`stat -x traffic_light.txt |grep "Modify" | grep -o "[0-9]*:[0-9]*:[0-9]*"`
echo "$TIMESTAMP"
