package ejbbdd;


import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class Serveur implements Serializable {
       @Id
       private String id;
       private String adressIP;
       private int port;


       public Serveur() {
          super();
       }


    public Serveur(String id, String adressIP, int port) {
        this.id = id;
        this.adressIP = adressIP;
        this.port = port;
    }


    public String getid() {
        return id;
    }


    public void setid(String id) {
        this.id = id;
    }


    public String getAdressIP() {
        return adressIP;
    }


    public void setAdressIP(String adressIP) {
        this.adressIP = adressIP;
    }


    public int getPort() {
        return port;
    }


    public void setPort(int port) {
        this.port = port;
    }

}

