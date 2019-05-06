package fr.metz.iut.licencepro.projet.metier.objet;

import javax.persistence.*;

@Entity
@Table(name = "utilisateur")
public class Utilisateur {
    public Utilisateur() {
    }

    private int id;
    private String user;
    private String password;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "user")
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
