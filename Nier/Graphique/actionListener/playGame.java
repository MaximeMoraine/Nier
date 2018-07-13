package nier.graphique.actionListener;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Lance la partie lorsque l'event est déclencher
 */
public class playGame implements ActionListener {
    
    // Methode
    
    /**
     * On lance le jeu et on reset la fenetre (button + ennemi)
     */
    public void actionPerformed(ActionEvent arg0) {
        nier.Game.stop();
        nier.Level.restart();
        nier.Game.FENETRE.getPan().removeAll();
        nier.Game.FENETRE.removeButton();
        nier.Game.game();
    }
}
