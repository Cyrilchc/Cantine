package fr.metz.iut.licencepro.projet.service;

import fr.metz.iut.licencepro.projet.metier.manager.UtilisateurManager;
import fr.metz.iut.licencepro.projet.metier.objet.Utilisateur;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/co")
public class UtilisateurService {

    UtilisateurManager utilisateurManager= new UtilisateurManager();

    @POST
    @Path("/connexion")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postconnexion(Utilisateur utilisateur) {
        try {
            utilisateurManager.getUser(utilisateur);
            return Response.ok().build();
        }catch(Exception ex) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }
}
