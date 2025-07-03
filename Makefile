dbstart: 
	@echo "Starting the database..."
	@docker stop db_mv || true
	@docker rm db_mv || true
	@docker build -t db_mv -f ./Dockerfile.db .
	@docker run -d --name db_mv -p 5432:5432 db_mv
	@sleep 2 
	@bash ./scripts/db_script_init.bash

start_service: 
	@echo "Starting the order service..."
	@mvn clean install  && mvn spring-boot:run
