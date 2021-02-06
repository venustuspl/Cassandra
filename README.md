# Cassandra

Is REST Application made for store data on NoSQL database type Casandra.

## Instalation

First pull Cassandra image:
docker pull cassandra

Then run cassandra container:
docker run -p 9042:9042 --rm --name cassandra -d cassandra:latest

Then enter inside cassandra:
docker exec -it cassandra bash

Then start cql shell:
cqlsh

Then make keyspace:
create keyspace venustus with replication={'class':'SimpleStrategy', 'replication_factor':1};

Then use this keyspace:
use venustus;

Then create table:  CREATE TABLE message(id timeuuid , email text, title text, description text, content text,
magicnumber int, PRIMARY KEY( magicnumber, id));

Then create materialized view for avoid @AllowFiltering: create materialized view vmessage as select * from message
where email is not null and id is not null and magicnumber is not null primary key(email, magicnumber, id);

## Usage

After run springboot, on terminal I've send:
curl -X POST localhost:8080/api/message -H "Content-Type: application/json" -d "{\"email\":
\"jan.kowalski@example.com\",\"title\":\"Interview\",\"content\":\"simpletext\",\"magic_number\":101}"
curl -X POST localhost:8080/api/message -H "Content-Type: application/json" -d "{\"email\":
\"jan.kowalski@example.com\",\"title\":\"Interview 2\",\"content\":\"simple text 2\",\"magic_number\":22}"
curl -X POST localhost:8080/api/message -H "Content-Type: application/json" -d "{\"email\":
\"anna.zajkowska@example.com\",\"title\":\"Interview 3\",\"content\":\"simple text 3\",\"magic_number\":101}"
I don't know why here wasn't content type:
curl -X POST localhost:8080/api/send -d '{"magic_number":101}' in normal situation, I should ask if this could be
mistake, but for now I've added json type:
curl -X POST localhost:8080/api/send -H "Content-Type: application/json" -d "{\"magic_number\":101}"

##

I've tought that I should more automate this process, but for now I wanted to made features.




