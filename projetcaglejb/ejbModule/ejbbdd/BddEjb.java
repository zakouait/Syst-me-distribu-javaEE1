package ejbbdd;


import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


@Stateless
public class BddEjb implements IBddEjb {
	
	// les methode de bdd 
	
	//ajouter serveur et rechercher 
	// recuperer service 
	

	@PersistenceContext
	EntityManager em;
	 public void ajouter(Serveur serveur) {
	      em.persist(serveur);
	   }
	 public boolean rechercheserveur(String serv) {
		Serveur ser= em.find(Serveur.class, serv) ;
		if(ser!=null) {
			return true;
		}else {
			return false;
		}
		
	   }
	 public Service Rechercheser(int num) {
		 return  em.find(Service.class, num) ;
	   }
}

