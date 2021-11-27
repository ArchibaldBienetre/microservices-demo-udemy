
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
 
* consumer on topic 'twitter-topic'
```
$ kafkacat -C -b localhost:19092 -t twitter-topic
$ kafkacat -C -b localhost:19092 -t twitter-topic
[...]
���$���弛��(�RT @nuicemedia: One death came from Lampung. Jakarta retains lead with 51 new cases, Central Java 33, East Java 32, Riau 26.

Nine province…��߬_
% Reached end of topic twitter-topic [1] at offset 16
�����������������(�RT @shroom4all: shrooms pink pills are good for microdosing
#developer #css #java #software
#javascript
#Python
#DataScience
#DEVCommunity…���߬_
�����筻#��������(�RT @shroom4all: shrooms pink pills are good for microdosing
#developer #css #java #software
#javascript
#Python
#DataScience
#DEVCommunity…���߬_
% Reached end of topic twitter-topic [0] at offset 14
% Reached end of topic twitter-topic [2] at offset 11
���Ѵ���'���ᘜ��(�RT @shroom4all: shrooms pink pills are good for microdosing
#developer #css #java #software
#javascript
#Python
#DataScience
#DEVCommunity…�߬_
% Reached end of topic twitter-topic [0] at offset 15
ꔋ�
  ��������(�RT @shroom4all: Are you in need of a pshychedelic plug? We got top grade  LSD..,DMT,..Shrooms, MDMA, Chocolate bars...and Ibogaine.  for mo…�߬_
% Reached end of topic twitter-topic [1] at offset 17
�����������������(�RT @_Carine_C: Il est des livres qui sont comme une clé ouvrant les salles inconnues de notre propre château..Belle citation de  #Kafka qui…���߬_
% Reached end of topic twitter-topic [0] at offset 16
��������&��������(�RT @ideiasmine: Como colocar uma localização personalizada no seu mapa #Minecraft
 
Lembrando só funciona na versão Java por enquanto. http…���߬_
% Reached end of topic twitter-topic [1] at offset 18
��������'��������(�RT @_Carine_C: Il est des livres qui sont comme une clé ouvrant les salles inconnues de notre propre château..Belle citation de  #Kafka qui…ྸ߬_
% Reached end of topic twitter-topic [2] at offset 12
�����測(���񄡭�(�RT @Crimsonfunkin: Then @JavaCoded and the rest of @Lyte_Games  decided to reject x-event which made my blood boiled 100% stronger than mos…�θ߬_
% Reached end of topic twitter-topic [0] at offset 17
�����������ś���(�RT @shroom4all: shrooms pink pills are good for microdosing
#developer #css #java #software
#javascript
#Python
#DataScience
#DEVCommunity…�θ߬_
% Reached end of topic twitter-topic [2] at offset 13
```