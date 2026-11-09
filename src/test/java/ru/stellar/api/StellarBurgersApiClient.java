package ru.stellar.api;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import ru.stellar.api.model.User;

import static io.restassured.RestAssured.given;

public class StellarBurgersApiClient {

    public static final String BASE_URL = "https://stellarburgers.education-services.ru";

    private final RequestSpecification requestSpec;

    public StellarBurgersApiClient() {
        RestAssured.baseURI = BASE_URL;
        this.requestSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(BASE_URL)
                .addFilter(new AllureRestAssured())
                .build();
    }

    @Step("API: регистрация пользователя {user.email}")
    public Response register(User user) {
        return given()
                .spec(requestSpec)
                .body(user)
                .when()
                .post("/api/auth/register");
    }

    @Step("API: удаление пользователя по токену")
    public Response deleteUser(String accessToken) {
        return given()
                .spec(requestSpec)
                .header("Authorization", accessToken)
                .when()
                .delete("/api/auth/user");
    }
}