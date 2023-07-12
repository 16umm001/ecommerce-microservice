PROJECTS := apigateway id.generator serviceregistry

ACCOUNTS := accounts products

all: build build_apis run

export DATABASE_URL=jdbc:postgresql://localhost:5432/accounts
export REGISTRY_SERVICE_URL=http://localhost:8761/eureka
export PRODUCT_DATABASE_URL=mongodb://localhost:27017/products

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
