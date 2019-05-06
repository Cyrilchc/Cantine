package fr.metz.iut.licencepro.projet.service;

import fr.metz.iut.licencepro.projet.metier.manager.ProduitManager;
import fr.metz.iut.licencepro.projet.metier.objet.Produit;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("outDate")
public class ProduitsOutService {

    ProduitManager produitManager = new ProduitManager();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOutDatedProducts() {
        List<Produit> products = produitManager.getOutDatedProducts();
        if (products.size() < 1) {
            return Response.status(Response.Status.NOT_FOUND).entity("Aucun produit trouvé").build();
        }
        return Response.ok().entity(products).build();
    }
}
