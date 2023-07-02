package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.example.util.Employee;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.*;

public class ApiTest extends BaseTest {

    @Test
    public void testGetEmployees() {
        RestAssured
                .when()
                .get("/employees")
                .then()
                .assertThat()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("employees-schema.json"));
    }

    @Test
    public void testGetEmployeeById() {
        int employeeId = 1;

        RestAssured
                .given()
                .pathParam("id", employeeId)
                .when()
                .get("/employee/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .body("id", equalTo(employeeId));
    }

    @Test
    public void testCreateEmployee() {
        Employee newEmployee = new Employee("John Doe", 50000, 30);

        given()
                .contentType(ContentType.JSON)
                .body(newEmployee)
                .when()
                .post("/create")
                .then()
                .statusCode(200)
                .body("status", equalTo("success"))
                .body("data.name", equalTo(newEmployee.getName()))
                .body("data.salary", equalTo(newEmployee.getSalary()))
                .body("data.age", equalTo(newEmployee.getAge()))
                .body("data.id", instanceOf(Integer.class))
                .body("message", equalTo("Successfully! Record has been added."));
    }

    @Test
    public void testCreateEmployeeWithInvalidAge() {
        Employee newEmployeeWithInvalidAge = new Employee("John Doe", 50000, 0);

        given()
                .contentType(ContentType.JSON)
                .body(newEmployeeWithInvalidAge)
                .when()
                .post("/create")
                .then()
                .statusCode(400)
                .body("message", equalTo("Invalid age. Must be greater than or equal to 18."));
    }

    @Test
    public void testCreateEmployeeWithInvalidTypes() {
        String invalidEmployee = "{\"name\": \"John\",\"salary\": \"123\",\"age\": \"asdfg\"}";

        given()
                .contentType(ContentType.JSON)
                .body(invalidEmployee)
                .when()
                .post("/create")
                .then()
                .statusCode(400)
                .body("message", equalTo("Invalid age type!"));
    }
}
