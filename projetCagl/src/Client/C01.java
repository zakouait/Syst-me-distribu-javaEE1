package Client;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import ejbbdd.Service;

public class C01 {
    public static void main(String[] args) {
        try {
            DatagramSocket c = new DatagramSocket();
            byte[] sentData = new byte[10];
            int s = 1; // Change from String to int
            int[] listeservice = {7, 10, 1, 2, 4, 3, 0, 6, 12, 11, 8, 5, 2, 1, 0, 5, 1, 7, 9, 2};
            int i = 1;
            String q;
            Scanner sc = new Scanner(System.in);
            InetAddress ip = InetAddress.getByName("localhost");
            byte[] receiveData = new byte[10];
            Service ret;
            Service serv = new Service();
            Registry r = LocateRegistry.getRegistry("localhost", 8001);
            traitement ip2 = (traitement) r.lookup("refinter");
            DatagramSocket cc = new DatagramSocket(9001);
int cmt=0;

            while (i == 1) {
                while ( cmt<listeservice.length) {
                Timer timer= new Timer() ;
                	
                	TimerTask receiveTask = new TimerTask() {
                		 @Override
                        public void run() {
                			 
                                System.out.println("purge.");
                               

                                
								// Réinitialiser le timer pour le prochain envoi
                                timer.cancel(); // Annule le timer actuel
                                Timer timer = new Timer(); // Crée un nouveau timer
                                timer.schedule(new TimerTask() {
                                    @Override
                                    public void run() {
                                        // Logique pour lancer l'envoi du token avec le nouveau candidat
                                        int newTokenint = 0; // Nouveau token à envoyer
                                        String newToken=Integer.toString(newTokenint);
                                        DatagramPacket sendm = new DatagramPacket(newToken.getBytes(), newToken.getBytes().length, ip,9002);
                                        try {
                                        	 final DatagramSocket ccc = new DatagramSocket();
                                        	 ccc.send(sendm);
                                            System.out.println("Creation nouveau token  : ");
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }, 1);                             }};

                				timer.schedule(receiveTask,1);
                    
                				
                                try {
                                    serv.setNumero(listeservice[cmt]);//partie rmi
                                    ret = ip2.traiter(serv);

                                    // Check if serv is not null before printing
                                    if (serv == null) {
                                       // Increment s
                                    	System.out.println("service null");
                                    s = s + 1;
                                    }else {
                                    	 System.out.println(ret.getDiscription());
                                    	
                                    }

                                    
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                
                  //envoyer toker
                    sentData = Integer.toString(s).getBytes();
                    DatagramPacket sendp = new DatagramPacket(sentData, sentData.length, ip, 9002);
                    c.send(sendp);
                    
                    
                  //recevoir token
                    DatagramPacket receivep = new DatagramPacket(receiveData, receiveData.length);
                    System.out.println("wait");
                    cc.receive(receivep);
                    s = Integer.parseInt(new String(receivep.getData(), 0, receivep.getLength()));
                    cmt++;
                } 
               
                sc.next();
                //recevoir token
                DatagramPacket receivep = new DatagramPacket(receiveData, receiveData.length);
                System.out.println("wait");
                cc.receive(receivep);
                s = Integer.parseInt(new String(receivep.getData(), 0, receivep.getLength()));
                //envoyer toker
                sentData = Integer.toString(s).getBytes();
                DatagramPacket sendp = new DatagramPacket(sentData, sentData.length, ip, 9002);
                c.send(sendp);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}