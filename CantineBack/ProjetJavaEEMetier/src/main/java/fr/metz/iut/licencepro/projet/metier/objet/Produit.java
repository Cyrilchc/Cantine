package fr.metz.iut.licencepro.projet.metier.objet;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "produit")
public class Produit {
    public Produit() {
    }

    private int id;
    private String libelle;
    private String marque;
    private String reference;
    private Date dateperemption;
    private double prix;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "libelle")
    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Column(name = "marque")
    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    @Column(name = "reference")
    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    @Column(name = "dateperemption")
    public Date getDateperemption() {
        return dateperemption;
    }

    public void setDateperemption(Date dateperemption) {
        this.dateperemption = dateperemption;
    }

    @Column(name = "prix")
    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
}
