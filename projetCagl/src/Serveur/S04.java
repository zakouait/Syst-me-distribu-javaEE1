package Serveur;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

import javax.naming.InitialContext;


import ejbbdd.IBddEjb;
import ejbbdd.Serveur;
import ejbbdd.Service;

public class S04 {
	public static void main(String[] args) {
		try{System.out.println("marche");
			Properties props = new Properties();
		 
		 props.put("java.naming.factory.url.pkgs","org.jboss.ejb.client.naming");
		 props.put("jboss.naming.client.ejb.context",true);
		 InitialContext context = new InitialContext(props);
		 
		 IBddEjb in = (IBddEjb)context.lookup("ejb:/projetcaglejb/BddEjb!ejbbdd.IBddEjb");
		
		
			ServerSocket S4 = new ServerSocket(2004);
		InetAddress ip=InetAddress.getByName("localhost");
		
		 
		while(true) {
			
			   System.out.println("serveur s2 en attente...");
				
			 
			Socket connexion4 = S4.accept();
			   System.out.println(" accepter...");
			   
				
			   ObjectInputStream inp3 = new ObjectInputStream(connexion4.getInputStream());
			   Service s=(Service)inp3.readObject();//recupéré l'envoyer par le client fin aller
						System.out.println(s.getNumero());
						
				
				

						S04 Myclass=new S04();
						
						String nom=Myclass.getClass().getName();//recupere nom class
						
						Serveur ss=new Serveur(nom,ip.toString(),S4.getLocalPort());//crée serveur
				//ajouter si il existe pas
						if(in.rechercheserveur(nom)==false) {
							in.ajouter(ss);
						}
						s=in.Rechercheser(s.getNumero());//rechercher service
						
						if(s!=null) {
							System.out.println(s.getDiscription());
						}
						ObjectOutputStream out2 = new ObjectOutputStream(connexion4.getOutputStream());//le retour
						out2.writeObject(s);
						
						connexion4.close();
						out2.close();
						inp3.close();
						
						
		} }catch(Exception e){System.out.println(e.toString());
		}
		
		
}}