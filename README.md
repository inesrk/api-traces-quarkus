# API traces

[![Build Status](https://travis-ci.org/EnterpriseFlowsRepository/api-traces-quarkus.svg?branch=dev)](https://travis-ci.org/EnterpriseFlowsRepository/api-traces-quarkus)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=EnterpriseFlowsRepository_api-traces-quarkus&metric=alert_status)](https://sonarcloud.io/api-traces-quarkus?id=EnterpriseFlowsRepository_api-traces-quarkus)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=EnterpriseFlowsRepository_api-traces-quarkus&metric=coverage)](https://sonarcloud.io/api-traces-quarkus?id=EnterpriseFlowsRepository_api-traces-quarkus)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=EnterpriseFlowsRepository_api-traces-quarkus&metric=security_rating)](https://sonarcloud.io/api-traces-quarkus?id=EnterpriseFlowsRepository_api-traces-quarkus)
![Licence](https://img.shields.io/github/license/EnterpriseFlowsRepository/api-traces-quarkus)
![Last commit](https://img.shields.io/github/last-commit/EnterpriseFlowsRepository/api-traces-quarkus)

Service implementation for the EFR traces module.

## Getting started

To start working on the project:

1. Clone the project with `git clone git@github.com:EnterpriseFlowsRepository/api-traces-quarkus.git`
2. Install the Maven dependencies with `./mvnw install`
3. Start the development server with `./mvnw compile quarkus:dev`

### Prerequisites

- Install JDK (i.e. `openjdk-11-jdk` on Ubuntu)

> Maven is already included within the scripts `mvnw`, you do not need to install it

### Installing

In order to start with the project, first install a JDK then install the project dependencies:

```bash
./mvnw install
```

```bash
➜  api-traces-quarkus git:(quality-travis) ✗ ./mvnw install
[...]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  5.441 s
[INFO] Finished at: 2019-11-26T11:11:07+01:00
[INFO] ------------------------------------------------------------------------
```

## Running the tests

Unit tests are integrated within the project. [We are use JUnit 5](https://junit.org/junit5). Start them with:

```bash
./mvnw clean verify
```

## Deployment

Enterprise Flows Repisitory is straightforward: [a container image is published to Docker Hub](https://hub.docker.com/r/enterpriseflowsrepository/api-traces-quarkus).

### Development environement

Start the container to your own environement (50m RAM and 0.05 CPU):

```bash
docker run --memory="50m" --cpus=".05" -p 8080:8080 enterpriseflowsrepository/api-traces-quarkus
```

### Production environement

We advise the usage of Kubernetes as the orchestrator of the application. Thanks to its stateless behavior, you can easily integrate it. See the k8s deployement files included for more info about it (path `src/main/k8s`).

## Built With

- [Quarkus](https://quarkus.io) - Kubernetes Native Java EE stack tailored for OpenJDK HotSpot and GraalVM
- [RESTEasy](https://resteasy.github.io) - Helper librairies to built RESTful applications
- [JUnit 5](https://junit.org/junit5) - Unitary tests platform for Java
- [REST-assured](http://rest-assured.io) - REST library validator
- [SmallRye OpenAPI](https://github.com/smallrye/smallrye-open-api) - Implementation of OpenAPI v3 specifications for JEE
- [SmallRye Health](https://github.com/smallrye/smallrye-health) - REST healthcheck library for Java EE
- [SmallRye Fault Tolerance](https://github.com/smallrye/smallrye-fault-tolerance) - Caching & retry requests automations library for Java EE
- [Swagger UI](https://swagger.io/tools/swagger-ui) - OpenAPI v3 UI
- [README-template.md](https://gist.github.com/PurpleBooth/109311bb0361f32d87a2) - An awesome README template from @PurpleBooth

## Contributing

Please read [CODE_OF_CONDUCT.md](CODE_OF_CONDUCT.md) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/EnterpriseFlowsRepository/api-traces-quarkus/tags).

## Authors

- **Emmanuel Lesné** - *Project creator & maintainer* - [Emmanuel35](https://github.com/Emmanuel35)
- **Clément Lesné** - *Maintainer* - [clementlesne](https://github.com/clementlesne)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.
