package fr.jasonbailleul.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Mail", schema = "dbo", catalog = "filrougeDTB")
public class MailEntity {

    @Id
    @Column(name = "idMail", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMail;
    @Column
    private String subject;
    @Column
    private String sendTo;
    @Column
    private LocalDateTime sendAt;

    public MailEntity(){
        this.sendAt = LocalDateTime.now();
    }

}
