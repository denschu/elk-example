Log Management with Logstash, Elastichsearch and Kibana
======================================================================

For detailed installation instructions please refer to my blog post.

# Clone the repository

```shell
git clone http://github.com/denschu/elk-example
cd elk-example
```

# Install and run Elasticsearch

```shell
curl -O https://download.elasticsearch.org/elasticsearch/elasticsearch/elasticsearch-1.1.1.tar.gz
tar zxvf elasticsearch-1.1.1.tar.gz
./elasticsearch-1.1.1/bin/elasticsearch 
```

# Install and Run Kibana Dashboard

```shell
curl -O https://download.elasticsearch.org/kibana/kibana/kibana-3.1.0.tar.gz
tar zxvf kibana-3.1.0.tar.gz
cd kibana-3.1.0/
python -m SimpleHTTPServer 8087
```

Open the preconfigured Logstash Dashboard in Kibana

```shell
http://localhost:8087/index.html#/dashboard/file/logstash.json
```

# Install Logstash Agent

```shell
curl -O https://download.elasticsearch.org/logstash/logstash/logstash-1.4.2.tar.gz
tar zxvf logstash-1.4.2.tar.gz
```

Copy additional grok pattern to the agent directory

```shell
cp custompatterns logstash-1.4.2/patterns/
```

# Run Logstash Agent with Spring Boot Log Configuration

```shell
./logstash-1.4.2/bin/logstash agent -v -f logstash-spring-boot.conf
```

Corresponding Logback configuration:

```xml
<property name="FILE_LOG_PATTERN" value="%d{yyyy-MM-dd HH:mm:ss.SSS} %5p ${PID:- } [%t] --- %-40.40logger{39} : %m%n%wex"/>
```

Example log statement

```shell
2014-10-10 16:34:07.089  INFO 11322 [main] --- s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8090/http
```

Logstash Grok Pattern

```shell
%{TIMESTAMP_ISO8601:logtime}%{SPACE}%{LOGLEVEL:loglevel} %{SPACE}%{NUMBER:pid}%{SPACE}%{SYSLOG5424SD:threadname}%{SPACE}---%{SPACE}%{JAVACLASSSHORT:classname}%{SPACE}:%{SPACE}%{GREEDYDATA:logmessage}
```

```shell
JAVACLASSSHORT (?:[\.]?[a-zA-Z0-9-]+\.)*[A-Za-z0-9$]+
```

# Run the batch test application and start a batch job

```shell
cd loggging-example-batch/
mvn spring-boot:run
```

To start a job use this curl command:

```shell
curl --data 'jobParameters=pathToFile=classpath:partner-import.csv' localhost:8090/batch/operations/jobs/flatfileJob
```

Open the preconfigured Logstash Dashboard in Kibana again and you will see upcoming logstatements

```shell
http://localhost:8087/index.html#/dashboard/file/logstash.json
```

# See also

http://logstash.net/docs/1.4.2/tutorials/getting-started-with-logstash
http://www.elasticsearch.org/overview/kibana/installation/
