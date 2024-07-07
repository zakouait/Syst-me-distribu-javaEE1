package ejbbdd;

import jakarta.ejb.Remote;

@Remote
public interface IBddEjb {

	public void ajouter(Serveur serveur);
	 public boolean rechercheserveur(String serv);
	 public Service Rechercheser(int num);
}
