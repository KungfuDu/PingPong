/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pingpong.Etat;

import pingpong.Messages.Erreur;
import pingpong.Messages.Message;
import pingpong.Messages.Ping;
import pingpong.Messages.Smash;
import pingpong.Partie;

/**
 *
 * @author Quentin M
 */
public class EnAttenteCoup extends Etat {

    public EnAttenteCoup(Partie partie) {
        super(partie);
    }

    @Override
    public Message envoieAction() {
         int random = (int)(Math.random() * (10-0)) ;
         if(random > 4 && this.partie.getNumeroCoup()>0){
             System.out.println("Je Smash !");
             this.partie.setMonScore(this.partie.getMonScore()+1);
             this.partie.setEtatCourant(this.partie.getEtatEnAttente());
             return new Smash();
         }
         else {
             System.out.println("j'envoi un ping");
             return new Ping();
         }
             
        
    }

    @Override
    public Message receptionAction(Message message) {
       if (message instanceof Smash){
            System.out.println("j'ai recu un smash");
            this.partie.setAdvScore(this.partie.getAdvScore()+1);
            this.partie.setEtatCourant(this.partie.getEtatEnAttente());
            this.partie.setNumeroCoup(0);
            return this.partie.envoyerMessage();
       }
       else if(message instanceof Ping){
           System.out.println("Jai recu un ping");
           this.partie.setNumeroCoup(this.partie.getNumeroCoup()+1);
          return this.partie.envoyerMessage();
       }
       else return new Erreur();
    }
    
}
