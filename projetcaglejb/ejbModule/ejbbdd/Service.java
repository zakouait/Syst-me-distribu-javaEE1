package ejbbdd;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Service implements Serializable {
       @Id
       private int numero;
       private String nom;
       private String discription;

    public Service(int numero, String nom, String discription) {

        this.numero = numero;
        this.nom = nom;
        this.discription = discription;
    }
    public Service() {

        this.numero = 0;
        this.nom = null;
        this.discription = null;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

}

