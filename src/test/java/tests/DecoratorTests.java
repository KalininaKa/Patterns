package tests;

import decorator.LogginExtension;
import decorator.RandomUser;
import decorator.RandomUserResolver;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import models.UserModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static io.restassured.RestAssured.given;


@ExtendWith({LogginExtension.class, RandomUserResolver.class})
//наблюдатель декоратор
public class DecoratorTests {

    @Test
    @DisplayName("Test_1 Создание произвольного пользователя")
    public void createUserTest(@RandomUser UserModel user) {
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
        Assertions.assertEquals(user.getName(), jsonPath.get("name"));
        Assertions.assertEquals(user.getJob(), jsonPath.get("job"));
        Assertions.assertNotNull(jsonPath.get("createdAt"));
    }

    @Test
    @DisplayName("Test_2")
    public void Test() {
        System.out.println("Просто какой-то тест на сравнение двух чисел");
        Assertions.assertTrue(96 < 1658);
    }
}