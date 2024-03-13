package tests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import models.UserModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class BuilderTests {
    UserModel user1 = UserModel.builder()
            .name("morpheus")
            .job("leader").build();

    UserModel user2 = UserModel.builder()
            .name("morpheus").build();
    UserModel user3 = UserModel.builder()
            .job("leader").build();
    @Test
    public void builderTest() {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(user1)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().all()
                .statusCode(201)
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        Assertions.assertNotNull(jsonPath.get("id"));
        Assertions.assertEquals("morpheus", jsonPath.get("name"));
        Assertions.assertEquals("leader", jsonPath.get("job"));
        Assertions.assertNotNull(jsonPath.get("createdAt"));
    }

    @Test
    public void builderTest2() {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(user2)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().all()
                .statusCode(201)
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        Assertions.assertNotNull(jsonPath.get("id"));
        Assertions.assertEquals("morpheus", jsonPath.get("name"));
        Assertions.assertNull(jsonPath.get("job"));
        Assertions.assertNotNull(jsonPath.get("createdAt"));
    }

    @Test
    public void builderTest3() {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(user3)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().all()
                .statusCode(201)
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        Assertions.assertNotNull(jsonPath.get("id"));
        Assertions.assertNull(jsonPath.get("name"));
        Assertions.assertEquals("leader", jsonPath.get("job"));
        Assertions.assertNotNull(jsonPath.get("createdAt"));
    }
}
