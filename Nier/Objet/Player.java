package Nier.Objet;

import java.util.Set;
import java.util.HashSet;
import Nier.Deplacement.*;
import Nier.Exception.OutOfAreaException;

/**
 * Classe héritiére de Actor, implémentant les joueurs.
 */
public class Player extends Actor {
    // CONSTANTE
    
        public static final int FORM = Obj.PLAYER;;
        public static final int WEIGHT = 37;
        public static final int HEIGHT = 40;
        
        public static final int SPEED_PROJECT = 7;
        
        private ICoord mousePosition = new Coord(0, 0);
        
        private static Set ALL_PLAYER = new HashSet();
        
    // CONSTRUCTEUR
    
    public Player(ICoord pos, IMovement mov) {
        super(pos, mov, 3);
        ALL_PLAYER.add(this);
    }
    
    // REQUÊTES
    
    public int getForm() {
        return FORM;
    }
    
    public int getWeight() {
        return WEIGHT;
    }
    
    public int getHeight() {
        return HEIGHT;
    }
    
    public ICoord getPlayerMousePosition() {
        return mousePosition;
    }
    
    public static Set getAllPlayer() {
        return ALL_PLAYER;
    }
    
    // METHODE
    
    /**
     * Déplace le joueur celon les touches préssées.
     */
    public void push(boolean z, boolean q, boolean s, boolean d) {
        ((PlayerMovement) getMovement()).nextCoord(getPosition(), z, q, s, d);
        
        try {
            getPosition().setCol(getMovement().getCol());
        } catch (OutOfAreaException e) {
            // On ne change rien.
            // ainsi on empeche le joueur de sortir de la carte
        }
        
        try {
            getPosition().setRow(getMovement().getRow());
        } catch (OutOfAreaException e) {
            // On ne change rien.
            // ainsi on empeche le joueur de sortir de la carte
        }
    }
           
    /**
     * Permet d'actualiser les coordonnées de la souris.
     */
    public void setNewPlayerMousePosition(ICoord c) {
        try {
            getPlayerMousePosition().setCol(c.getCol());
            getPlayerMousePosition().setRow(c.getRow());
        } catch (OutOfAreaException e) {
            // ce cas ne peut normalement pas arriver
            // car c est supposé conforme.
        }
    }
    
    /**
     * permet au joueur de tirer des projectiles en direction
     * de getPlayerMousePosition()
     */
    public void fire() {
        ICoord start = new Coord(getPosition().getCol() + getWeight() / 2,
                               getPosition().getRow() - getHeight() / 2);
        
        // Recherche de l'angle
        // Mx = o.x + r * cos(O)
        // My = o.y + r * sin(O)
        int Mx = getPlayerMousePosition().getCol();
        int My = getPlayerMousePosition().getRow();
        double r = Math.sqrt((Mx - getPosition().getCol()) * (Mx - getPosition().getCol())
                        + (My - getPosition().getRow()) * (My - getPosition().getRow()));
        double angle = ((Mx - getPosition().getCol()) / r);
        angle = Math.acos(angle);
        
        if (My < getPosition().getRow()) {
            angle = 360 - Math.toDegrees(angle);
        } else {
            angle = Math.toDegrees(angle);
        }
        
        PolarCoord pos = new PolarCoord(start.getCol(), start.getRow(), angle, 0);
        IMovement mov = new ProjectMovement(SPEED_PROJECT);
        new ProjectilePlayer(pos, mov, this);
    }
    
    public void kill() {
        ALL_PLAYER.remove(this);
        Nier.Game.getFenetre().removeObj(this);
    }
}
