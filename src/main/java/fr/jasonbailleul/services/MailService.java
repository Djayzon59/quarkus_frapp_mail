package fr.jasonbailleul.services;

import fr.jasonbailleul.dto.MailDto;
import fr.jasonbailleul.outils.Validator;
import fr.jasonbailleul.repositories.MailRepo;
import fr.jasonbailleul.restCLient.ApiKeyClient;
import io.quarkus.mailer.Mailer;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/mails/")
@Tag(name = "mails")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MailService {

    @Inject
    private MailRepo mailRepo;
    @Inject
    Mailer mailer;
    @RestClient
    private ApiKeyClient apiKeyClient;


    @Transactional
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @APIResponse(responseCode = "200", description = "Mail envoyé !")
    @APIResponse(responseCode = "250", description = "Clef Invalide !")
    @APIResponse(responseCode = "251", description = "Quota dépassé !")
    @APIResponse(responseCode = "400", description = "Mail invalide !")
    public Response sendMail(MailDto mailDto, @HeaderParam("clef") String clef) {

        if (!Validator.isMailValid(mailDto.getSendTo()))
            return Response.ok("Adresse mail invalide ! ").status(400).build();

        Response response = apiKeyClient.chekUsage(clef);
        int statusCode = response.getStatus();

        if (statusCode == 200) {
            mailer.send(io.quarkus.mailer.Mail.withText(mailDto.getSendTo(), mailDto.getSubject(), mailDto.getTexte()));
            apiKeyClient.saveMail(mailDto, clef);
            return Response.ok(String.format("Le Message : %s, a été envoyé à : %s", mailDto.getSubject(), mailDto.getSendTo())).build();
        } else {
            return Response.status(statusCode).build();
        }

    }

    @Transactional
    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    @APIResponse(responseCode = "200", description = "Mail envoyé !")
    @APIResponse(responseCode = "400", description = "Mail invalide !")
    public Response sendMailWithoutKey(MailDto mailDto) {

        if (!Validator.isMailValid(mailDto.getSendTo()))
            return Response.ok("Adresse mail invalide ! ").status(400).build();

        mailer.send(io.quarkus.mailer.Mail.withText(mailDto.getSendTo(), mailDto.getSubject(), mailDto.getTexte()));
        return Response.ok(String.format("Le Message : %s, a été envoyé à : %s", mailDto.getSubject(), mailDto.getSendTo())).build();

    }
}
