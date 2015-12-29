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
public class RetourCoup extends Etat{

    public RetourCoup(Partie partie) {
        super(partie);
    }

   


    @Override
    public Message envoieAction() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Message receptionAction(Message message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
    
}
