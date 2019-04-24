# job-opening-service

How to start the job-opening-service application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/sample-job-opening-service-1.0.0.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`

Rest Service
----

1 Get Job openings by id - GET
2 Add Job openings - POST
3 Update Job openings - PUT
4 Get all Job openings - GET

GraphQL service
-----

Single rest points where you query two different types/methods
	
	jobOpeningsById(_id: ID): JobOpenings
	jobOpenings: [JobOpenings]
