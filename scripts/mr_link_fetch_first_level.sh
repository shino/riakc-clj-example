#!/bin/bash

# cf. Link Walking By Example | Basho
#   http://basho.com/link-walking-by-example/

curl -s -X POST -H "content-type:application/json" \
    --data @- \
    http://localhost:8098/mapred \
<<EOF
{
 "inputs": [["people", "sean"]],
 "query":  [{"link": {"tag": "friend"}},
            {"map":  {"language":"javascript","source":"function(v)
                         { return [v]; }"}}]
}
EOF
