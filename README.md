# HospitalSystem

# Checkpoint 2:
- added Billing Service
- added Eureka service discovery
- added cross-service communication via Netflix Feign Client

  Read Me:

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


# Checkpoint 1:
1. Clone master (.idea folder not needed)
2. run mvn clean package -Dmaven.test.skip=true
3. run docker build -t ehealth/patientservice ./patientservice
4. run docker build -t ehealth/patientservice ./patientservice
5. run docker-compose up
6. Make sure above commands are run at project root
