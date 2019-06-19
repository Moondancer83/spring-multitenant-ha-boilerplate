# Multi-tenant and Highly Available Application Boilerplate
based on Spring Boot 2 

This an incubator project to try out various technologies in the domain of Cloud Applications.
This project is just a tool for creating modules that gives real value for developing applications.

## Concepts & Technologies
### Multi-tenant
#### Get tenant ID
 - [x] from server name (subdomain) *
 - [ ] from request header
 - [ ] from user DB entity
#### Separate tenant data
 - [ ] by using tenant descriptor field for all data
 - [x] by using separate DB schema per tenant *
 - [ ] by using separate physical database per tenant
#### Managing configuration externally
 - [x] Configuration is not packaged together with code *
 - [x] Configuration is updating without the need of application restart *
 - [ ] Tenant configuration if used from externalized config *

### Highly Available (HA)
- [ ] Persist session data *
- [ ] Persist application caches
- [ ] Persist data
- [ ] Zero downtime deployment support
- [ ] Communication between services & circuit breakers

### CI/CD
- [ ] Migrate the whole application inside Heroku infrastructure **
- [ ] Migrate the whole application inside Amazon infrastructure **
- [ ] Migrate the whole build and deploy pipeline to Amazon infrastructure **

Items marked with * (asterisk) is in the primary scope of this project.

Items marked with ** (double asterisk) is in the primary scope of this project.

## Artifacts to create (in separate repositories)
- [ ] Auto-configuration for retrieving tenant ID (e.g.: _spring-boot-starter-multitenancy_)
- [ ] Auto-configuration for separating tenant data (e.g.: _spring-boot-starter-multitenancy-data_)
- [ ] Auto-configuration for persisting session data (e.g.: _spring-boot-starter-multitenancy-session_)
- [ ] Auto-configuration for cloud config (e.g.: _spring-boot-starter-multitenancy-config_)

## To be supported technologies
- Spring Boot 2 / Spring Framework 5
- SQL databases (MySQL, AWS Aurora)
- no-SQL databases (MongoDB, AWS DynamoDB)

