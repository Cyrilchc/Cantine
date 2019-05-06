package fr.metz.iut.licencepro.projet.metier.manager;

import fr.metz.iut.licencepro.projet.metier.objet.Produit;
import fr.metz.iut.licencepro.projet.metier.objet.Utilisateur;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.rmi.CORBA.Util;

public class UtilisateurManager extends HibernateFactory<Utilisateur> {
    public UtilisateurManager() {
        super(Utilisateur.class);
    }

    public Utilisateur getUser(Utilisateur utilisateur){
        Session session = getSession();
        Query query = session.createQuery("select utilisateur from Utilisateur as utilisateur where utilisateur.user = :user and utilisateur.password = :password");
        query.setParameter("user", utilisateur.getUser());
        query.setParameter("password", utilisateur.getPassword());
        return (Utilisateur) query.getSingleResult();
    }
}
