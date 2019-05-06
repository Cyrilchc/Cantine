package fr.metz.iut.licencepro.projet.service;

import fr.metz.iut.licencepro.projet.metier.manager.StockManager;
import fr.metz.iut.licencepro.projet.metier.objet.Stock;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("outStock")
public class StockOutService {

    StockManager stockManager = new StockManager();

    @GET
    @Path("/OutOfStock")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllOutOfStocks() {
        List<Stock> stockList = stockManager.getAllOutOfStocks();
        if (stockList.size() < 1) {
            return Response.status(Response.Status.NOT_FOUND).entity("Aucun produit trouvé").build();
        }
        return Response.ok().entity(stockList).build();
    }

    @GET
    @Path("/NearlyOutOfStock")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllNearlyOutOfStocks() {
        List<Stock> stockList = stockManager.getAllNearlyOutOfStocks();
        if (stockList.size() < 1) {
            return Response.status(Response.Status.NOT_FOUND).entity("Aucun produit trouvé").build();
        }
        return Response.ok().entity(stockList).build();
    }
}
