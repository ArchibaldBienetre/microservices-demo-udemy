#!/bin/bash

function check_endpoint_is_up() {
    curlResult=$(curl -s -o /dev/null -I -w "%{http_code}" "http://config-server:8888/actuator/health")
}

apt-get update -y

yes | apt-get install curl

check_endpoint_is_up
echo "Result status code: $curlResult"

while [[ ! "$curlResult" == "200" ]]; do
  echo "Config server is not up yet!" >&2
  sleep 2
  check_endpoint_is_up
done

# continue launching application

./cnb/lifecycle/launcher