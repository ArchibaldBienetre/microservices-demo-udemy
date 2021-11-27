
* Course wants me to call `docker-compose -f common.yml -f kafka_cluster.yml up`,  
but it works with plain `docker-compose up`. 
* Recommended: [kafkacat tool](https://docs.confluent.io/platform/current/app-development/kafkacat-usage.html)
```
$ kafkacat -L -b localhost:19092
Metadata for all topics (from broker 1: localhost:19092/1):
3 brokers:
broker 2 at localhost:29092 (controller)
broker 1 at localhost:19092
broker 3 at localhost:39092
2 topics:
topic "__confluent.support.metrics" with 1 partitions:
partition 0, leader 1, replicas: 1,3,2, isrs: 1,3,2
topic "_schemas" with 1 partitions:
partition 0, leader 2, replicas: 2,1,3, isrs: 2,1,3
```
 