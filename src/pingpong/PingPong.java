/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pingpong;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import pingpong.Etat.EnAttente;
import pingpong.Etat.PartieFinie;
import pingpong.Messages.Message;

/**
 *
 * @author qmarecha
 */
public class PingPong {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
       
        Socket socket = null;
        try {
            socket = new Socket("localhost", Serveur.portEcoute);
            // socket = new Socket(InetAddress.getByName("10.192.51.67"),5555);
        } catch(UnknownHostException e) {
            System.err.println("Erreur sur l'hôte : " + e);
            System.exit(-1);
        } catch(IOException e) {
            System.err.println("Creation de la socket impossible : " + e);
            System.exit(-1);
        }
        System.out.println("Assoss flux");
        ObjectInputStream input = null;
        ObjectOutputStream output = null;
        try {
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());
        } catch(IOException e) {
            System.err.println("Association des flux impossible : " + e);
            System.exit(-1);
        }
        
        
        
        
        
        
        Partie partie = new Partie();
        Object objet= null;
        System.out.println("Preparation");
        output.writeObject(partie.envoyerMessage());
        System.out.println("message 1 envoyé");
        System.out.println("Reception du message");

        
        System.out.println("Reception du message");
            
            while(!(partie.getEtatCourant() instanceof PartieFinie)){
            
            try {
                System.out.println("ok");
                objet = input.readObject();
                System.out.println("aaaaa");
                if (objet instanceof Message)
                {
                    Message leMessage = (Message) objet;
                    
                    output.writeObject(partie.recevoirMessage(leMessage));
                }
                else{ System.out.println("errorbug mrd");}
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(PingPong.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            }
            
            
            
        try {
            input.close();
            output.close();
            socket.close();
            System.out.println("Client de'connecte'.");
            System.exit(0);
        } catch(IOException e) {
            System.err.println("Erreur lors de la fermeture des flux et des sockets : " + e);
            System.exit(-1);
        }
    }
}

    

