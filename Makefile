PROJECTS := apigateway id.generator serviceregistry

ACCOUNTS := accounts products

all: build build_apis run

build:
	@for project in $(PROJECTS); do \
  		cd $$project && mvn clean package; \
  		cd ..; \
  	done

build_apis:
	@for project in $(ACCOUNTS); do \
  		cd $$project && mvn package spring-boot:repackage; \
  		cd ..; \
	done

clean:
	@for project in $(PROJECTS); do \
  		cd $$project && ./mvnw clean; \
  		cd ..; \
  	done

run:
	@echo "Running docker"
	docker-compose up --build
