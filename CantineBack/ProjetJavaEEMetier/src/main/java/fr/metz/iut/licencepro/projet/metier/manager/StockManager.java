package fr.metz.iut.licencepro.projet.metier.manager;

import fr.metz.iut.licencepro.projet.metier.objet.Produit;
import fr.metz.iut.licencepro.projet.metier.objet.Stock;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class StockManager extends HibernateFactory<Stock> {
    public StockManager() {
        super(Stock.class);
    }

    public List<Stock> getAllStocks(){
        Session session = getSession();
        Query query = session.createQuery("from Stock as stock");
        return query.list();
    }

    public List<Stock> getAllOutOfStocks(){
        Session session = getSession();
        Query query = session.createQuery("select stock.produit from Stock as stock where stock.qte < 1");
        return query.list();
    }

    public List<Stock> getAllNearlyOutOfStocks(){
        Session session = getSession();
        Query query = session.createQuery("select stock.produit from Stock as stock where stock.qte < stock.qtemini");
        return query.list();
    }

    public Stock getStockByProductReference(String reference){
        Session session = getSession();
        Query query = session.createQuery("select stock from Stock as stock where stock.produit.reference = :laref");
        query.setParameter("laref", reference);
        return (Stock) query.getSingleResult();
    }
}
