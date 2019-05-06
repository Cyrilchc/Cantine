package fr.metz.iut.licencepro.projet.service;

import fr.metz.iut.licencepro.projet.metier.manager.ProduitManager;
import fr.metz.iut.licencepro.projet.metier.manager.StockManager;
import fr.metz.iut.licencepro.projet.metier.objet.Produit;
import fr.metz.iut.licencepro.projet.metier.objet.Stock;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("stocks")
public class StocksService {
    StockManager stockManager = new StockManager();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStocks() {
        List<Stock> stockList = stockManager.getAllStocks();
        if (stockList.size() < 1) {
            return Response.status(Response.Status.NOT_FOUND).entity("Aucun produit trouvé").build();
        }
        return Response.ok().entity(stockList).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addStock(Stock stock) {
        try {
            Produit produitACreer = stock.getProduit();
            ProduitManager manager = new ProduitManager();
            produitACreer = manager.create(produitACreer);
            stock.setProduit(produitACreer);
            stockManager.create(stock);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.NOT_IMPLEMENTED).entity("L'entrée stock n'a pas pu être créée").build();
        }
    }
}
