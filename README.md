# client-micro-service

## Routes

* `GET http://localhost:8083/clients` - Retrieve a list of clients
* `POST http://localhost:8083/clients` - Create a new client
* `PUT http://localhost:8083/clients/{id}` - Update an existing client
* `DELETE http://localhost:8083/clients/{id}` - Delete an client
* `GET http://localhost:8083/clients/{id}` - Retrieve an client by ID

## Cli with makefile

* make dbstart (if already launched in other micro service don't to do it)
* make create_db (create the database if don't exist)
* make start_service (build project with dependencies and run with hot releaod mandatory to already have java and maven)
