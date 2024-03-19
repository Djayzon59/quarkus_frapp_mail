package fr.jasonbailleul.services;

import fr.jasonbailleul.dto.MailDto;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;

class MailServiceTest {

    /*@Test
    void sendMail() {
        MailDto mailDto = new MailDto();
        mailDto.setSendTo("djadja59670@gmail.com");
        mailDto.setSubject("Test Subject");
        mailDto.setTexte("Test Text");
        String clef = "SECRET_KEY";

        given()
                .header("SECRET_KEY", clef)
                .contentType("application/json")
                .body(mailDto)
                .when().post("http://localhost:8081/Mails")
                .then()
                .statusCode(200)
                .body(containsString("Le Message : Test Subject, a été envoyé à : djadja59670@gmail.com"));
    }*/
}