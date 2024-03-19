package fr.jasonbailleul.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MailDto {

    @Schema(required = true, example = "djadja59670@gmail.com")
    private String sendTo;

    private String subject;

    private LocalDateTime sendAt;

    private String texte;

}
