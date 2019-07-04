package com.anz.account.e2e;

import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountController_e2eTest {

    @LocalServerPort
    private Integer port;

    @Test
    public void getAccountByUserIdTest_404NotFoundTest() {
        String response =  given()
                .port(port)
                .when()
                .get("/api/v1/account/" )
                .then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .contentType(ContentType.JSON)
                .extract().body().asString();
        Assert.assertTrue(response.contains("404"));
    }

    @Test
    public void getAccountByUserIdTest() {
        String response =  given()
                .port(port)
                .when()
                .get("/api/v1/accounts/" + "u001")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON)
                .extract().body().asString();
        Assert.assertTrue(response.contains("a10001"));
    }


    @Test
    public void getAccountByUserIdTest_InvalidUser() {
        String response =  given()
                .port(port)
                .when()
                .get("/api/v1/accounts/" + "uooo")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON)
                .extract().body().asString();
        Assert.assertFalse(response.contains("a10001"));
    }

}
