Log Management for Spring Boot Applications with Logstash, Elasticsearch and Kibana 
====================================================================================

For further informations and detailed installation instructions please refer to [my blog post](https://blog.codecentric.de/en/2014/10/log-management-spring-boot-applications-logstash-elastichsearch-kibana/) on the codecentric Blog. The following instructions give you only a quick overview.

## Clone the repository

```shell
git clone http://github.com/denschu/elk-example
cd elk-example
```

## Build the Example Application

cd loggging-example-batch
mvn clean install

## Run the ELK Stack with Docker (Compose)

docker-compose up

## Open Kibana

```shell
http://<boot2docker-ip>:5601
```

## Logback configuration

```xml
<property name="FILE_LOG_PATTERN" value="%d{yyyy-MM-dd HH:mm:ss.SSS} %5p ${PID:- } [%t] --- %-40.40logger{39} : %m%n%wex"/>
```

Example log statement

```shell
2014-10-10 16:34:07.089  INFO 11322 [main] --- s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8090/http
```

## Grok Pattern

Logstash Grok Pattern

```shell
%{TIMESTAMP_ISO8601:logtime}%{SPACE}%{LOGLEVEL:loglevel} %{SPACE}%{NUMBER:pid}%{SPACE}%{SYSLOG5424SD:threadname}%{SPACE}---%{SPACE}%{JAVACLASSSHORT:classname}%{SPACE}:%{SPACE}%{GREEDYDATA:logmessage}
```

```shell
JAVACLASSSHORT (?:[\.]?[a-zA-Z0-9-]+\.)*[A-Za-z0-9$]+
```

## Run the batch test application and start a batch job

To start a job use this curl command:

```shell
curl --data 'jobParameters=pathToFile=classpath:partner-import.csv' <boot2docker-ip>:8090/batch/operations/jobs/flatfileJob
```

Open the preconfigured Logstash Dashboard in Kibana again and you will see upcoming logstatements

```shell
http://<boot2docker-ip>:5601
```

## See also

http://logstash.net/docs/1.4.2/tutorials/getting-started-with-logstash
http://www.elasticsearch.org/overview/kibana/installation/
https://github.com/elasticsearch/logstash-forwarder

## Generate SSL Certs for Logstash Forwarder

openssl req -x509  -batch -nodes -newkey rsa:2048 -keyout logstash-forwarder.key -out logstash-forwarder.crt -subj /CN=logstash








