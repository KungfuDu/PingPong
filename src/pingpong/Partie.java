/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pingpong;

import pingpong.Etat.*;
import pingpong.Etat.Etat;
import pingpong.Messages.Message;

/**
 *
 * @author qmarecha
 */
public class Partie {
   private Etat etatCourant;
   private Etat etatEnAttente;
   private Etat etatRetourCoup;
   private Etat etatPartieFinie;
   private Etat etatEnAttenteCoup;
   private int monScore;
   private int advScore;
   private int nombreDePointMaX;
   private int numeroCoup;

    public Partie() {
        this.etatCourant = new EnAttente(this);
        this.etatEnAttente =new EnAttente(this);
        this.etatRetourCoup = new RetourCoup(this);
        this.etatPartieFinie = new PartieFinie(this);
        this.etatEnAttenteCoup = new EnAttenteCoup(this);
        this.advScore=0;
        this.monScore=0;
        this.numeroCoup=0;
        this.nombreDePointMaX=10;
    }
   
   public Message recevoirMessage(Message mess){
      return this.etatCourant.receptionAction(mess);
   }
   
   public Message envoyerMessage(){
       return this.etatCourant.envoieAction();
   }

    public int getNombreDePointMaX() {
        return nombreDePointMaX;
    }

    public void setNombreDePointMaX(int nombreDePointMaX) {
        this.nombreDePointMaX = nombreDePointMaX;
    }

   
   
   
   
   
   
   
   
   
   
   
   
    public Etat getEtatCourant() {
        return etatCourant;
    }

    public void setEtatCourant(Etat etatCourant) {
        this.etatCourant = etatCourant;
    }

    public int getMonScore() {
        return monScore;
    }

    public void setMonScore(int monScore) {
        this.monScore = monScore;
    }

    public int getAdvScore() {
        return advScore;
    }

    public void setAdvScore(int advScore) {
        this.advScore = advScore;
    }


    public int getNumeroCoup() {
        return numeroCoup;
    }

    public void setNumeroCoup(int numeroCoup) {
        this.numeroCoup = numeroCoup;
    }

    public Etat getEtatEnAttente() {
        return etatEnAttente;
    }

    public Etat getEtatRetourCoup() {
        return etatRetourCoup;
    }

    public Etat getEtatPartieFinie() {
        return etatPartieFinie;
    }

    public Etat getEtatEnAttenteCoup() {
       return etatEnAttenteCoup;
    }
    
    
   
    
}
