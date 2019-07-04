package com.anz.account.e2e;

import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionController_e2eTest {

    @LocalServerPort
    private Integer port;

   @Test
    public void getTransactionByAccountNumberTest_404NotFoundTest() {
        String response =  given()
                .port(port)
                .when()
                .get("/api/v1/accounts/u001/transaction" )
                .then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .contentType(ContentType.JSON)
                .extract().body().asString();
        Assert.assertTrue(response.contains("404"));
    }

    @Test
    public void getTransactionByAccountNumberTest() {
        String response =  given()
                .port(port)
                .when()
                .get("/api/v1/accounts/a10001/transactions")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON)
                .extract().body().asString();
        Assert.assertTrue(response.contains("a10001"));
    }

    @Test
    public void getTransactionByAccountNumberTest_InvalidAccount() {
        String response =  given()
                .port(port)
                .when()
                .get("/api/v1/accounts/uooo/transactions")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON)
                .extract().body().asString();
        Assert.assertFalse(response.contains("a10001"));
    }

}
