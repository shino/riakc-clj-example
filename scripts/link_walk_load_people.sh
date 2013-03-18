#!/bin/sh

echo "Creating Sean."
curl -H "content-type: text/plain" -H 'Link: </buckets/people/keys/mark>; riaktag="friend", </buckets/people/keys/kevin>; riaktag="friend", </buckets/people/keys/notexist>; riaktag="friend"' -d "Sean" -X PUT http://127.0.0.1:8098/buckets/people/keys/sean
echo "Creating Mark."
curl -H "content-type: text/plain" -H 'Link: </buckets/people/keys/sean>; riaktag="friend", </buckets/people/keys/justin>; riaktag="friend"' -d "Mark" -X PUT http://127.0.0.1:8098/buckets/people/keys/mark
echo "Creating John."
curl -H "content-type: text/plain" -H 'Link: </buckets/people/keys/sean>; riaktag="supervises", </buckets/people/keys/mark>; riaktag="supervises"' -d "John" -X PUT http://127.0.0.1:8098/buckets/people/keys/john
echo "Creating Kevin."
curl -H "content-type: text/plain" -d "Kevin" -X PUT http://127.0.0.1:8098/buckets/people/keys/kevin
echo "Creating Justin."
curl -H "content-type: text/plain" -H 'Link: </buckets/people/keys/kevin>; riaktag="supervises"' -d "Justin" -X PUT http://127.0.0.1:8098/buckets/people/keys/justin
echo "Creating Tony."
curl -H "content-type: text/plain" -H 'Link: </buckets/people/keys/john>; riaktag="supervises", </buckets/people/keys/marisa>; riaktag="supervises"' -d "Tony" -X PUT http://127.0.0.1:8098/buckets/people/keys/tony
echo "Creating Marisa."
curl -H "content-type: text/plain" -H 'Link: </buckets/people/keys/maureen>; riaktag="supervises"' -d "Marisa" -X PUT http://127.0.0.1:8098/buckets/people/keys/marisa
echo "Creating Maureen."
curl -H "content-type: text/plain" -H 'Link: </buckets/people/keys/mark>; riaktag="friend"' -d "Maureen" -X PUT http://127.0.0.1:8098/buckets/people/keys/maureen

