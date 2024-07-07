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

public class C02 {
    public static void main(String[] args) {
        try {
            DatagramSocket c = new DatagramSocket();
            byte[] sentData = new byte[10];
            int s = 1; // Change from String to int
            int[] listeservice = {15,1,4,2,3,6,7,10,1,13,6,14,2,0,1,3,2,7,0,1};
            int i = 1;
            String q;
            Scanner sc = new Scanner(System.in);
            InetAddress ip = InetAddress.getByName("localhost");
            byte[] receiveData = new byte[10];
            Service ret;
            Service serv = new Service();
            Registry r = LocateRegistry.getRegistry("localhost", 8001);
            traitement ip2 = (traitement) r.lookup("refinter");
            DatagramSocket cc = new DatagramSocket(9002);
int cmt=0;

            while (i == 1) {
                while ( cmt<listeservice.length) {Timer timer= new Timer() ;
                	//recevoir token
                    DatagramPacket receivep = new DatagramPacket(receiveData, receiveData.length);
                    System.out.println("wait");
                    cc.receive(receivep);
                    s = Integer.parseInt(new String(receivep.getData(), 0, receivep.getLength()));
                    

                	
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
                                        DatagramPacket sendm = new DatagramPacket(newToken.getBytes(), newToken.getBytes().length, ip,9003);
                                        try {
                                        	 final DatagramSocket ccc = new DatagramSocket();
                                        	 ccc.send(sendm);
                                            System.out.println("Creation nouveau token  : ");
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }, 99999);                             }};

                				timer.schedule(receiveTask,99999);
                    
                    
                    try {//partie rmi
                        serv.setNumero(listeservice[cmt]);
                        ret = ip2.traiter(serv);

                        // Check if serv is not null before printing
                        if (serv == null) {
                           // Increment s
                        	System.out.println("Service null");
                        s = s + 1;
                        }else {
                        	 System.out.println(ret.getDiscription());
                        	
                        }

                        
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                  //envoyer toker
                    sentData = Integer.toString(s).getBytes();
                    DatagramPacket sendp = new DatagramPacket(sentData, sentData.length, ip, 9003);
                    c.send(sendp);cmt++;
                }
                //recevoir token
                DatagramPacket receivep = new DatagramPacket(receiveData, receiveData.length);
                System.out.println("wait");
                cc.receive(receivep);
                s = Integer.parseInt(new String(receivep.getData(), 0, receivep.getLength()));
                //envoyer toker
                sentData = Integer.toString(s).getBytes();
                DatagramPacket sendp = new DatagramPacket(sentData, sentData.length, ip, 9003);
                c.send(sendp);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }}
