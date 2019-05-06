package fr.metz.iut.licencepro.projet.service;

import fr.metz.iut.licencepro.projet.metier.manager.ProduitManager;
import fr.metz.iut.licencepro.projet.metier.objet.Produit;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("prods")
public class ProduitsService {

    ProduitManager produitManager = new ProduitManager();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getproducts() {
        List<Produit> products = produitManager.getAllProduits();
        if (products.size() < 1) {
            return Response.status(Response.Status.NOT_FOUND).entity("Aucun produit trouvé").build();
        }
        return Response.ok().entity(products).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addproduct(Produit produit) {
        try {
            Produit res = produitManager.create(produit);
            return Response.ok().entity(res).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.NOT_IMPLEMENTED).entity("Produit non créé").build();
        }
    }
}
