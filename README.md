# HospitalSystem

# Checkpoint 3:

-added api gateway
-added kafka for messaging
-added serviceAdministeredEvent model for use with Kafka messaging
-added Zipkin and Micrometer for tracing
-partial deployment to Amazon AWS...RDS/EKS/ECS created, docker images pushed, but non-functional and wasn't able to demonstrate

* Was unable to get Security to work


Config Service GitHub: https://github.com/CHarrison7/configrepo/

---------------------------
Steps to run:

1. Clone master (.idea folder and Kubernetes-relate .yaml files not needed)
2. run mvn clean package -Dmaven.test.skip=true
3. run docker build -t ehealth/patientservice ./patientservice
4. run docker build -t ehealth/billingservice ./billingserver
5. run docker build -t ehealth/configserver ./configserver
6. run docker build -t ehealth/eurekaserver ./eurekaserver
7. Run docker build -t ehealth/gatewayserver ./gatewayserver
8. run docker-compose up

-Make sure above commands are run at project root
-See ./docs for deployment diagram that shows port numbers for each service, in case you wish to access Zipkin backend

====================================================================
====================================================================

# Checkpoint 2:
- added Billing Service
- added Eureka service discovery
- added cross-service communication via Netflix Feign Client

GitHub: https://github.com/CHarrison7/HospitalSystem --> Use MASTER branch!

Config Service GitHub: https://github.com/CHarrison7/configrepo/

Steps to run:

1. Clone master (.idea folder not needed)
2. run mvn clean package -Dmaven.test.skip=true
3. run docker build -t ehealth/patientservice ./patientservice
4. run docker build -t ehealth/billingservice ./billingserver
5. run docker build -t ehealth/configserver ./configserver
6. run docker build -t ehealth/eurekaserver ./eurekaserver
7. run docker-compose up

Make sure above commands are run at project root


====================================================================
====================================================================

# Checkpoint 1:
1. Clone master (.idea folder not needed)
2. run mvn clean package -Dmaven.test.skip=true
3. run docker build -t ehealth/patientservice ./patientservice
4. run docker build -t ehealth/patientservice ./patientservice
5. run docker-compose up
6. Make sure above commands are run at project root
