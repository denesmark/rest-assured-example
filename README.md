# REST Assured Demo

This is a demonstration project for REST Assured, a Java library for validating REST services. The tests are written using the TestNG testing framework.

The test suite in this project targets a specific API endpoint. However, due to security mechanisms of the provided API, all calls originating from this test suite will be blocked. As such, running the test suite will result in failing tests.

## Preconditions

To run this project, you will need:

- Java 11 or later
- Maven
- Docker

You can choose to run the project either with Maven or Docker.

## Running with Maven

First, install the project dependencies:

```bash
mvn clean install -DskipTests 
```

Then, you can execute the test suite with the following command:

```bash
mvn test
```

Running with Docker
First, build the Docker image:

```bash
docker build -t my-test-image .
```


Then, run the container:
```bash
docker run --name my-test-container my-test-image
```
⚠️
Please note that due to the API's security mechanisms, the tests are expected to fail 
(They return with 429 status code: Too Many Requests)
⚠️
