package ru.stellar.utils;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.stellar.api.StellarBurgersApiClient;
import ru.stellar.api.model.User;

import static org.apache.http.HttpStatus.SC_OK;

public class UserFixture {

    public final String email;
    public final String password;
    public final String name;
    public final String accessToken;

    private UserFixture(String email, String password, String name, String accessToken) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.accessToken = accessToken;
    }

    @Step("Создание уникального пользователя через API")
    public static UserFixture createUnique(StellarBurgersApiClient client) {
        String email = UserGenerator.randomEmail();
        String password = UserGenerator.randomPassword();
        String name = UserGenerator.randomName();

        User user = new User(email, password, name);
        Response response = client.register(user);

        String accessToken = response.path("accessToken");

        response.then().statusCode(SC_OK);

        return new UserFixture(email, password, name, accessToken);
    }

    @Step("Удаление тестового пользователя через API")
    public void delete(StellarBurgersApiClient client) {
        if (accessToken != null) {
            client.deleteUser(accessToken);
        }
    }
}
