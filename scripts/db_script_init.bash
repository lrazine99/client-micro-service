#!/bin/bash

CONTAINER_NAME="db_mv"
DB_USER="postgres"
DB_NAME="products_db"

# DB_NAMES=("products_db" "clients_db" "orders_db")

docker exec -it "$CONTAINER_NAME" bash -c "command -v psql > /dev/null && \
    psql -U $DB_USER -tc \"SELECT 1 FROM pg_database WHERE datname = '$DB_NAME'\" | grep -q 1 || \
    psql -U $DB_USER -c \"CREATE DATABASE $DB_NAME\""

