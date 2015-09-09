# CassandraJson

This is a simple Eclipse Project that will show how to convert a Cassandra Query made with the Java Driver of Datastax to Json format. That could be useful to make some kind of import or ETL. 

Basically, query Cassandra and returns the result in Json format.

### Getting started
For this example, I have a KEYSPACE named "test" and a TABLE with same name. So, we have to run ./cqlsh (after having Cassandra server running in background) and:

* Create keyspace
```sh
cqlsh> CREATE KEYSPACE test WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};
```
* Create table
```sh
cqlsh> CREATE TABLE test.test (id int PRIMARY KEY, name text, values map<text,text>);
```
* Insert data
```sh
cqlsh> INSERT INTO test.test (id, name, values) VALUES (1,'Josemy Duarte',{'J':'D'});
```
After having Cassandra ready, we have to:

1. Clone or download the git.

2. Import the project in Eclipse.

3. Run the application in Eclipse.

### Libraries
Libraries used:
* cassandra-driver-core-2.1.5.jar
* gson-2.3.1.jar
* guava-16.0.jar
* metrics-core-3.0.1.jar
* netty-3.7.0.Final.jar
* slf4j-api-1.7.2.jar

### Link to Cassandra
- [DataStax Community Edition — Apache Cassandra](http://www.planetcassandra.org/cassandra/) Version that I used: 2.1.9

