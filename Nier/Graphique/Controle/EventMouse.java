package nier.graphique.controle;

import nier.exception.OutOfAreaException;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import nier.deplacement.ICoord;
import nier.deplacement.Coord;

/**
 * Gére les événements souris.
 */
public class EventMouse extends MouseAdapter {
    
    private final ICoord mouseCoord = new Coord(0, 0);
    
    // REQUETE
    
    public ICoord getMouseCoord() {
        return mouseCoord;
    }
    
    // EVENT
    
    @Override
    public void mouseMoved(MouseEvent e) {
        
        try {
            mouseCoord.setCol(e.getX() - nier.objet.ProjectilePlayer.WEIGHT);
        } catch (OutOfAreaException ex) {
            // Si la souris sors de l'écran tant pis
        }
        
        try {
            mouseCoord.setRow(ICoord.LAST_ROW - e.getY()
                    + nier.objet.ProjectilePlayer.HEIGHT);
        } catch (OutOfAreaException ex) {
            // Si la souris sors de l'écran tant pis
        }
    }
}
