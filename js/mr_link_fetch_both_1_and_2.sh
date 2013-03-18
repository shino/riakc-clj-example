g#!/bin/bash

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
                         { return [v]; }", "keep": true}},
            {"reduce":  {"language":"javascript","source":"function(vs)
                         { var ls = [];
                           for(var i=0; i<vs.length; i++) {
                               if(\"values\" in vs[i]) {
                                   var l = vs[i].values[0].metadata.Links;
                                   ls = ls.concat(l);
                               }
                           }
                           return ls;
                         }"}},
            {"map":  {"language":"javascript","source":"function(v)
                         { return [v]; }"}}
 ]
}
EOF

