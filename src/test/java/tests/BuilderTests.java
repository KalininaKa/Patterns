package tests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import models.UserModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class BuilderTests {
    @Test
    public void builderTest() {
        UserModel user = UserModel.builder()
                .name("morpheus")
                .job("leader").build();

        Response response = given()
                .contentType(ContentType.JSON)
                .body(user)
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
}
