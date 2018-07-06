package Nier.Graphique.Controle;

import Nier.Exception.OutOfAreaException;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Nier.Deplacement.ICoord;
import Nier.Deplacement.Coord;

/**
 * Gére les événements souris.
 */
public class EventMouse extends MouseAdapter {
    
    private final ICoord mouseCoord = new Coord(0, 0);
    private boolean mousePressed = false;
    
    // REQUETE
    
    public ICoord getMouseCoord() {
        return mouseCoord;
    }
    
    public boolean mousePressed() {
        return mousePressed;
    }
    
    // EVENT
    
    @Override
    public void mousePressed(MouseEvent e) {
        mousePressed = true;
    }
    
    public void mouseReleased(MouseEvent e) {
        mousePressed = false;
    }   
    
    @Override
    public void mouseMoved(MouseEvent e) {
        
        try {
            mouseCoord.setCol(e.getX() - Nier.Objet.ProjectilePlayer.WEIGHT);
        } catch (OutOfAreaException ex) {
            // Si la souris sors de l'écran tant pis
        }
        
        try {
            mouseCoord.setRow(ICoord.LAST_ROW - e.getY()
                    + Nier.Objet.ProjectilePlayer.HEIGHT);
        } catch (OutOfAreaException ex) {
            // Si la souris sors de l'écran tant pis
        }
        
    }
}
