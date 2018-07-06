package Nier.Graphique.Controle;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

import java.util.HashSet;
import java.util.Set;

/**
 * Gére les événement clavier en detectant les touches entrées.
 */
public class KeyboardEvent extends KeyAdapter {
    
    private Set touchePressed = new HashSet();
    
    // REQUETE
    
    public Set getTouchePressed() {
        return touchePressed;
    }
    
    // EVENT
    
    @Override
    public void keyPressed(KeyEvent e) {
        // rien
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        touchePressed.remove(e.getKeyChar());
    }
 
    public void keyTyped(KeyEvent e) {
        // TODO: METTRE EN LISTE
        if (e.getKeyChar() == 'z' || e.getKeyChar() == 'q'
                || e.getKeyChar() == 's' || e.getKeyChar() == 'd') {
                    
            touchePressed.add(e.getKeyChar());
        }
    }
}