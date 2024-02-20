package tests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import models.UserModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import users.Create;

import java.util.stream.Stream;

import static io.restassured.RestAssured.given;

public class DataDrivenTests {
    @ParameterizedTest
    @CsvSource({"morpheus,leader", "jack,seller", "john,builder", "mik,postman"})
    public void paramsTest(String name, String job){
        System.out.println(name);
        System.out.println(job);
        Create user = new Create(name, job);
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
        Assertions.assertEquals(name, jsonPath.get("name"));
        Assertions.assertEquals(job, jsonPath.get("job"));
    }

    @ParameterizedTest
    @MethodSource(value = "testUsers")
    public void objectParamsTest(UserModel userModel){
        System.out.println(userModel);
        Response response = given()
                .contentType(ContentType.JSON)
                .body(userModel)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().all()
                .statusCode(201)
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        Assertions.assertEquals(userModel.getName(), jsonPath.get("name"));
        Assertions.assertEquals(userModel.getJob(), jsonPath.get("job"));
    }

    private static Stream<UserModel> testUsers(){
        UserModel user1 = UserModel.builder()
                .name("morpheus")
                .job("leader").build();
        UserModel user2 = UserModel.builder()
                .name("jack")
                .job("seller").build();
        UserModel user3 = UserModel.builder()
                .name("john")
                .job("builder").build();
        UserModel user4 = UserModel.builder()
                .name("mik")
                .job("postman").build();
        return Stream.of(user1,user2,user3,user4);
    }
}