#!/bin/bash

if [ x$1 != x ] ; then
  startNumber=$1
else
  startNumber=0
fi
echo "StartNumber: $startNumber"

modules=(
  "tools4j"
  "tools4j-annotation"
  "tools4j-annotation-ktx"
  "tools4j-base64"
  "tools4j-base64-ktx"
  "tools4j-collections"
  "tools4j-collections-ktx"
  "tools4j-common-action"
  "tools4j-common-iterable"
  "tools4j-common-rangesbase"
  "tools4j-compare"
  "tools4j-crypto"
  "tools4j-crypto-ktx"
  "tools4j-date"
  "tools4j-date-ktx"
  "tools4j-environment"
  "tools4j-io"
  "tools4j-io-ktx"
  "tools4j-ktx"
  "tools4j-lang"
  "tools4j-lang-ktx"
  "tools4j-math"
  "tools4j-math-ktx"
  "tools4j-net"
  "tools4j-premise"
  "tools4j-premise-ktx"
  "tools4j-ranges"
  "tools4j-ranges-ktx"
  "tools4j-ranges-date"
  "tools4j-ranges-date-ktx"
  "tools4j-reflect"
  "tools4j-reflect-ktx"
  "tools4j-regex"
  "tools4j-regex-ktx"
  "tools4j-resources"
  "tools4j-security"
  "tools4j-security-ktx"
  "tools4j-sequences"
  "tools4j-test"
  "tools4j-test-ktx"
  "tools4j-zip"
  "tools4j-zip-ktx"
  )
number=0
for module in ${modules[@]};do
  if [ "$number" -ge "$startNumber" ] ; then
    ./gradlew $module:bU -PdryRun=false -Poverride=true
    if [ $? -ne 0 ];then
        echo "module $module failed. number: $number"
        exit 1
    fi
  fi
  let number++
done