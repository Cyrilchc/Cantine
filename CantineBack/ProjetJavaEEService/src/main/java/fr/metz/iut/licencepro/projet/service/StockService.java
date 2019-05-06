package fr.metz.iut.licencepro.projet.service;

import fr.metz.iut.licencepro.projet.metier.manager.ProduitManager;
import fr.metz.iut.licencepro.projet.metier.manager.StockManager;
import fr.metz.iut.licencepro.projet.metier.objet.Produit;
import fr.metz.iut.licencepro.projet.metier.objet.Stock;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("stocks/{ref}")
public class StockService {
    StockManager stockManager = new StockManager();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStock(@PathParam("ref") String ref) {
        Stock stock = stockManager.getStockByProductReference(ref);
        if (stock != null) {
            return Response.ok(stock).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Aucune entrée stock trouvée").build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setstock(@PathParam("ref") String ref, final Stock stock){
        try{
            ProduitManager manager = new ProduitManager();
            Produit produit = manager.getProduitByReference(ref);
            Produit produitASauver = stock.getProduit();
            produitASauver.setId(produit.getId());
            manager.update(produitASauver);
            stockManager.update(stock);
            return Response.ok().build();
        }catch (Exception ex){
            return Response.status(Response.Status.NOT_MODIFIED).build();
        }
    }

    @DELETE
    public Response deletestock(@PathParam("ref") String ref){
        try {
            Stock stock = stockManager.getStockByProductReference(ref);
            stockManager.delete(stock);
            return Response.ok(stock).build();
        }catch (Exception ex){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
