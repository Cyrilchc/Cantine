package fr.metz.iut.licencepro.projet.service;

import fr.metz.iut.licencepro.projet.metier.manager.ProduitManager;
import fr.metz.iut.licencepro.projet.metier.objet.Produit;

import javax.decorator.Delegate;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/prods/{ref}")
public class ProduitService {
    ProduitManager produitManager = new ProduitManager();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getproduct(@PathParam("ref") String ref) {
        Produit produit = produitManager.getProduitByReference(ref);
        if (produit != null) {
            return Response.ok(produit).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Aucun produit trouvé").build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setproduct(@PathParam("ref") String ref, final Produit produit){
        try{
            produitManager.update(produit);
            return Response.ok().build();
        }catch (Exception ex){
            return Response.status(Response.Status.NOT_MODIFIED).build();
        }
    }

    @DELETE
    public Response deleteproduct(@PathParam("ref") String ref){
        try {
            Produit produit = produitManager.getProduitByReference(ref);
            produitManager.delete(produit);
            return Response.ok(produit).build();
        }catch (Exception ex){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
