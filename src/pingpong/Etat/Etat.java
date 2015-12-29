/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pingpong.Etat;

import pingpong.Messages.Message;
import pingpong.Partie;

/**
 *
 * @author qmarecha
 */
public abstract class Etat {
    Partie partie;

    public Etat(Partie partie) {
        this.partie=partie;
    }
    
    public abstract Message envoieAction();
    public abstract Message receptionAction(Message message);
}
