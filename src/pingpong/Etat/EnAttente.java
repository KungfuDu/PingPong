/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pingpong.Etat;

import java.io.IOException;
import pingpong.Messages.Abandonner;
import pingpong.Messages.Ace;
import pingpong.Messages.DonnerService;
import pingpong.Messages.Erreur;
import pingpong.Messages.Gagne;
import pingpong.Messages.Message;
import pingpong.Messages.Perdu;
import pingpong.Messages.Ping;
import pingpong.Partie;

/**
 *
 * @author qmarecha
 */
public class EnAttente extends Etat{

    public EnAttente(Partie partie) {
        super(partie);
    }

    @Override
    public Message envoieAction() {
        int random = (int)(Math.random() * (10-0)) ;
        if(random == 5 && this.partie.getMonScore()< this.partie.getNombreDePointMaX() && this.partie.getAdvScore()< this.partie.getNombreDePointMaX()){
            System.out.println("Je donne le service");
            return new DonnerService();
        }
        
        else if(this.partie.getMonScore()== this.partie.getNombreDePointMaX()){
            this.partie.setEtatCourant(this.partie.getEtatPartieFinie());
            System.out.println("Jai gagné");
            return new Gagne();
        }
        
         else if( this.partie.getAdvScore()== this.partie.getNombreDePointMaX()){
            this.partie.setEtatCourant(this.partie.getEtatPartieFinie());
            System.out.println("jai Perdu");
            return new Perdu();
        }
         
        else if( (this.partie.getAdvScore() - this.partie.getMonScore()) >=3 && random == 2){
            this.partie.setEtatCourant(this.partie.getEtatPartieFinie());
            System.out.println("J'abandonne ");
            return new Abandonner();
        }
        
        else {
            this.partie.setEtatCourant(this.partie.getEtatEnAttenteCoup());
            System.out.println("je Ping");
            return new Ping();
        }
        
    }

    @Override
    public Message receptionAction(Message message) {
        
        if(message instanceof DonnerService){
            System.out.println("j'ai recu le service");
            this.partie.setEtatCourant(this.partie.getEtatEnAttenteCoup());
            return new Ping();
        }
        else if (message instanceof Ping){
            System.out.println("jai recu un ping");
             int random = (int)(Math.random() * (10-0)) ;
             if(random == 4){
                 System.out.println("Jai recu un ace !");
                 this.partie.setAdvScore(this.partie.getAdvScore()+1);
              
                 return new Ace();
             }
             else{
                 System.out.println("L'adversaire vient de servire ...");
                 this.partie.setNumeroCoup(this.partie.getNumeroCoup()+1);
                 this.partie.setEtatCourant(this.partie.getEtatEnAttenteCoup());
                 return this.envoieAction();}
        }
        else if(message instanceof Abandonner){
             System.out.println("l'adversaire a abandonné");
             this.partie.setEtatCourant(this.partie.getEtatPartieFinie());
             return new Gagne();
        }
        else if(message instanceof Perdu){
             System.out.println("l'adversaire a perdu");
             this.partie.setEtatCourant(this.partie.getEtatPartieFinie());
             return new Gagne();
        }
        else if(message instanceof Gagne){
             System.out.println("l'adversaire a gagné");
             this.partie.setEtatCourant(this.partie.getEtatPartieFinie());
             return new Perdu();
        }
        else return new Erreur();
    }

   
    
}
