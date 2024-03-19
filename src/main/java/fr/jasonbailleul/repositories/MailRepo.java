package fr.jasonbailleul.repositories;

import fr.jasonbailleul.entities.MailEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class MailRepo implements PanacheRepositoryBase<MailEntity,Integer> {
}
