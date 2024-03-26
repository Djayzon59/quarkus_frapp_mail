package fr.jasonbailleul.services;

import fr.jasonbailleul.dto.MailDto;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class MailServiceTest {
    private static MailDto mailDto1;
    private static String clef = "t56J6FiHFI8+dA==";
    private static String clef2 = "FALSE_KEY";

    @BeforeAll
    public static void setup() {
        mailDto1 = new MailDto();
        mailDto1.setSendTo("djadja59670@gmail.com");
        mailDto1.setSubject("Test subject");
        mailDto1.setTexte("Test message");

    }
    @Test
    void sendMail() {
        given()
                .contentType("application/json")
                .when()
                .body(mailDto1)
                .header("clef", clef)
                .post("/mails/")
                .then()
                .statusCode(200);
    }

    @Test
    void sendMailFalseKey(){
        given()
                .contentType("application/json")
                .when()
                .body(mailDto1)
                .header("clef", clef2)
                .post("/mails/")
                .then()
                .statusCode(250);
    }

    @Test
    void sendMailWithoutKey(){
        given()
                .contentType("application/json")
                .when()
                .body(mailDto1)
                .put("/mails/")
                .then()
                .statusCode(200);
    }

}