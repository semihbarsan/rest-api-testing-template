package com.semih;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AppTest {

    @Test
    public void testGETRequest() {
        long startTime = System.currentTimeMillis();
        Response response = RestAssured
                .given()
                .log().all()
                .when()
                .get("https://jsonplaceholder.typicode.com/users/1")
                .then()
                .log().all()
                .extract()
                .response();
        long elapsed = System.currentTimeMillis() - startTime;
        assertEquals(200, response.getStatusCode());
        assertEquals("Leanne Graham", response.jsonPath().getString("name"));
        assertEquals("get@gmail.com", response.jsonPath().getString("email"));

        if (elapsed > 1000) {
            System.out.println("UYARI: Yanıt süresi 1 saniyeyi geçti! Süre: " + elapsed + " ms");
        } else {
            System.out.println("Yanıt süresi: " + elapsed + " ms");
        }
    }

    @Test
    public void testPOSTRequest() {
        String jsonBody = "{ \"name\": \"Jane Doe\", \"email\": \"jane.doe@example.com\" }";
        long startTime = System.currentTimeMillis();
        Response response = RestAssured
                .given()
                .log().all()
                .header("Content-type", "application/json")
                .body(jsonBody)
                .when()
                .post("https://jsonplaceholder.typicode.com/users")
                .then()
                .log().all()
                .extract()
                .response();
        long elapsed = System.currentTimeMillis() - startTime;
        assertEquals(201, response.getStatusCode());
        assertEquals("Jane Doe", response.jsonPath().getString("name"));
        assertEquals("post@example.com", response.jsonPath().getString("email"));

        if (elapsed > 1000) {
            System.out.println("UYARI: Yanıt süresi 1 saniyeyi geçti! Süre: " + elapsed + " ms");
        } else {
            System.out.println("Yanıt süresi: " + elapsed + " ms");
        }
    }

}
