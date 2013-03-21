#!/bin/sh

curl -X POST http://localhost:8098/mapred \
  -H 'Content-Type: application/json' \
  -d '{
    "inputs": {"bucket": "people", "key_filters": [["ends_with", "1234"]]},
    "query": [{"map": {"language": "javascript",
                       "source": "function(riakObject) {
                                     return [riakObject.values[0].data];
                                  }"}}]
    }'

