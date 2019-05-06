package fr.metz.iut.licencepro.projet.metier.manager;

import fr.metz.iut.licencepro.projet.metier.objet.Produit;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class ProduitManager extends HibernateFactory<Produit> {
    public ProduitManager() {
        super(Produit.class);
    }

    public Produit getProduitByReference(String ref){
        Session session = getSession();
        Query query = session.createQuery("select produit from Produit as produit where produit.reference = :laref");
        query.setParameter("laref", ref);
        return (Produit) query.getSingleResult();
    }

    public List<Produit> getOutDatedProducts(){
        try {
            Session session = getSession();
            Query query = session.createQuery("from Produit as produit where produit.dateperemption < current_date");
            return query.list();
        }catch (Exception ex){
            System.out.println(ex.toString());
        }

        return null;
    }

    public List<Produit> getAllProduits(){
        try {
            Session session = getSession();
            Query query = session.createQuery("from Produit as produit");
            return query.list();
        }catch (Exception ex){
            System.out.println(ex.toString());
        }

        return null;
    }



    public Produit getProduitByid(int id){
        return read(id);
    }


}
