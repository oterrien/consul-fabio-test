# consul-fabio-test

GET http://localhost:8500/v1/kv/config/server/my/prop

curl -X PUT -d 'message: Hello From Consul2\nsub:\n  port: 8500' -H 'accept:text/plain' http://localhost:8500/v1/kv/config/server/my/prop 