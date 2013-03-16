#!/bin/bash

CLASSPATH=java
CLASSPATH=${CLASSPATH}:lib/clojure-1.3.0.jar
CLASSPATH=${CLASSPATH}:lib/commons-codec-1.6.jar
CLASSPATH=${CLASSPATH}:lib/commons-logging-1.1.1.jar
CLASSPATH=${CLASSPATH}:lib/httpclient-4.2.2.jar
CLASSPATH=${CLASSPATH}:lib/httpcore-4.2.2.jar
CLASSPATH=${CLASSPATH}:lib/jackson-annotations-2.1.2.jar
CLASSPATH=${CLASSPATH}:lib/jackson-core-2.1.2.jar
CLASSPATH=${CLASSPATH}:lib/jackson-databind-2.1.2.jar
CLASSPATH=${CLASSPATH}:lib/json-20090211.jar
CLASSPATH=${CLASSPATH}:lib/protobuf-java-2.4.1.jar
CLASSPATH=${CLASSPATH}:lib/riak-client-1.1.1-SNAPSHOT.jar
CLASSPATH=${CLASSPATH}:lib/riak-pb-1.2.1.jar
export CLASSPATH

javac java/LinkWalkAppPB.java
java LinkWalkAppPB

