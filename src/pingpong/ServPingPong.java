/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpong;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import pingpong.Etat.PartieFinie;
import pingpong.Messages.Message;

/**
 *
 * @author MR
 */
public class ServPingPong {
        public static final int portEcoute = 5555;
       
            
    
    public static void main(String[] args){
        
        System.out.println("ok ok ");
       
	try {
            
            // Creation de la socket serveur
            ServerSocket socketServeur = null;
            try {
                
                socketServeur = new ServerSocket(portEcoute);
                
                System.out.println("socketServeur = new ServerSocket(portEcoute);");
            } catch(IOException e) {
                System.err.println("Creation de la socket impossible : " + e);
                System.exit(-1); // code retour pour le système
            }
            
            // Attente d'une connexion d'un client
            Socket socketClient = null;
            try {
                System.out.println("Attente de connexion");
                socketClient = socketServeur.accept();
                System.out.println("Connexion OK");
            } catch(IOException e) {
                System.err.println("Erreur lors de l'attente d'une connexion : " + e);
                System.exit(-1); // code retour pour le système
            }
            System.out.println("Association des flux en cours");
            // Association d'un flux d'entree et de sortie
            
            
            ObjectOutputStream output = null; // flux d'ecriture
            ObjectInputStream input = null;
            try {
                System.out.println("ok");
                input = new ObjectInputStream(socketClient.getInputStream());
                System.out.println("ok 1");
                output = new ObjectOutputStream(socketClient.getOutputStream());
                System.out.println("ok ok");
            } catch(IOException e) {
                System.err.println("Association des flux impossible : " + e);
                System.exit(-1);
            }
            
            
            //¨Partie
            // Creation de l'objet a envoyer et envoi
             Partie partie = new Partie();
             Object objet = null;
            System.out.println("Partie crée");
                        
            
            System.out.println("Reception du message");
            
            while(!(partie.getEtatCourant() instanceof PartieFinie)){
            
                objet = input.readObject();
                if (objet instanceof Message)
            {
                Message leMessage = (Message) objet;
              
                output.writeObject(partie.recevoirMessage(leMessage));
            }
            
            
            }
            
            // Fermeture des flux et des sockets
        try {
            input.close();
            output.close();
            socketClient.close();
            socketServeur.close();
            System.out.println("Serveur de'connecte'.");
            System.exit(0);
        } catch(IOException e) {
            System.err.println("Erreur lors de la fermeture des flux et des sockets : " + e);
            System.exit(-1);
        }            
            
        } catch(IOException ex) {
	        Logger.getLogger(ServPingPong.class.getName()).log(Level.SEVERE, null, ex);
	}   catch (ClassNotFoundException ex) {
                Logger.getLogger(ServPingPong.class.getName()).log(Level.SEVERE, null, ex);
            }

    }
}
