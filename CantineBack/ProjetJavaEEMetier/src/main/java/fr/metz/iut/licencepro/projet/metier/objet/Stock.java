package fr.metz.iut.licencepro.projet.metier.objet;

import javax.persistence.*;

@Entity
@Table(name = "stock")
public class Stock {

    public Stock() {
    }

    private int id;
    private Produit produit;
    private int qte;
    private int qtemini;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IdProduit")
    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {this.produit = produit;}

    @Column(name = "qte")
    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    @Column(name = "qtemini")
    public int getQtemini() {
        return qtemini;
    }

    public void setQtemini(int qtemini) {
        this.qtemini = qtemini;
    }
}
