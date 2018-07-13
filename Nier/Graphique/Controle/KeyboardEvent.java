package nier.graphique.controle;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

import java.util.HashSet;
import java.util.Set;

/**
 * Gére les événement clavier en detectant les touches entrées.
 */
public class KeyboardEvent extends KeyAdapter {
    
    private Set touchePressed = new HashSet();
    private boolean shiftPressed = false;
    
    // REQUETE
    
    public Set getTouchePressed() {
        return touchePressed;
    }
    
    public boolean getShiftPressed() {
        return shiftPressed;
    }
    
    // EVENT
    
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            shiftPressed = false;
            return;
        }
        
        char c = Character.toLowerCase(e.getKeyChar());
        touchePressed.remove(c);
        
    }
 
    @Override
    public void keyPressed(KeyEvent e) {
        
        if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            shiftPressed = true;
            return;
        }
        
        char c = Character.toLowerCase(e.getKeyChar());
        if (c == 'z' || c == 'q' || c == 's' || c == 'd') {
            touchePressed.add(c);
        }
       
    }
}
