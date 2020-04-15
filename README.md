# Spring Cloud Eureka, Hystrix and Zuul poject for Uter cars

## Components
- `eureka-service` - The Eureka service which is the Service Registry
- `backoffice` - The Service which is going to give data to the backoffice.
- `frontend` - The Service which is going to get data from backoffice.
- `zuul-service` - This is the Gateway/Edge Service which is registered with Eureka and routes the requests to Client and Server using Eureka Service.
