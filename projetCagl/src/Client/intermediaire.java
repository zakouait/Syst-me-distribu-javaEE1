package Client;



import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;
import java.util.List;

import ejbbdd.Service;



public class intermediaire extends java.rmi.server.UnicastRemoteObject implements traitement {
	
	int port;
protected intermediaire() throws RemoteException {
	
		super();
		// TODO Auto-generated constructor stub
	}

public Service traiter(Service S) throws IOException, ClassNotFoundException {
	
	List<Integer>  S1 = Arrays.asList(0,1,2,3);
	List<Integer> S2 = Arrays.asList(4,5,6);
	List<Integer>  S3 = Arrays.asList(7,8,9);
	List<Integer>  S4 = Arrays.asList(10,11,12);
	List<Integer>  S5 = Arrays.asList(13,14,15);
	System.out.println("dans la fonction");
//choix serveur
	if(S1.contains(S.getNumero())) {
		port=2001;
	}
	if(S2.contains(S.getNumero())) {port=2002;
	}
	if(S3.contains(S.getNumero())) {port=2003;
	}
	if(S4.contains(S.getNumero())) {port=2004;
	}
	if(S5.contains(S.getNumero())) {port=2005;
		
	}
	
	Socket c1 = new Socket("localhost", port);//allocation bon port
	ObjectOutputStream out = new ObjectOutputStream(c1.getOutputStream());//aller ver serveur
		out.writeObject(S);
		 ObjectInputStream inp = new ObjectInputStream(c1.getInputStream());//retour depuis serveur
		 S=(Service)inp.readObject();
		 if(S!=null) {
			 System.out.println(S.getDiscription()); 
		 }
		
		 inp.close();
		 out.close();
		 c1.close();
		
		 return S;
}

public static void main(String[] args) throws Exception{
	
	intermediaire inter = new intermediaire();
	Registry r = LocateRegistry.createRegistry(8001);
	r.rebind("refinter", inter);
	System.out.println("lancer");
	
	
	}
}
