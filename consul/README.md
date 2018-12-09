# Consul

https://learn.hashicorp.com/consul/getting-started/agent 

To start Consul, launch ```consul agent -server -bootstrap-expect=1 -data-dir=consul-data -ui -bind=192.168.0.16``` 

where 192.168.0.16 is my IPv4

Once started, open URL : http://localhost:8500/ui/dc1/services 


