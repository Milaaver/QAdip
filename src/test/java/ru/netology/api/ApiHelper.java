package ru.netology.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import ru.netology.data.DataHelper;

import static io.restassured.RestAssured.given;

public class ApiHelper {
    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(8080)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
//            .log(LogDetail.ALL)
            .build();

    public String sendPostStatusSuccess(DataHelper.AuthInfo holder, String path, int statusCode) {
        String status =
                given()
                        .spec(requestSpec)
                        .body(holder)
                        .when()
                        .post(path)
                        .then()
                        .statusCode(statusCode)
                        .extract()
                        .path("status");
        return status;
    }

    public void sendPostStatusError(DataHelper.AuthInfo holder, String path, int statusCode) {
        given()
                .spec(requestSpec)
                .body(holder)
                .when()
                .post(path)
                .then()
                .statusCode(statusCode);
    }
}
