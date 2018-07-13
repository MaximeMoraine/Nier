package nier.objet;

import nier.constante.Constante;
import java.util.List;
import java.util.ArrayList;
import nier.deplacement.IMovement;
import nier.deplacement.ICoord;
import nier.deplacement.Coord;
import nier.deplacement.PlayerMovement;
import nier.deplacement.PolarCoord;
import nier.deplacement.PolarMovement;
import nier.exception.OutOfAreaException;

/**
 * Classe héritiére de Actor, implémentant les joueurs.
 */
public class Player extends Actor {
    
    // Constante
    
        public static final int FORM = Constante.PLAYER;
        public static final int WEIGHT = 20;
        public static final int HEIGHT = 14;
        public static final int SPEED_PROJECT = 20;
        private static final List ALL_PLAYER = new ArrayList();
        
        
    // Attributs
    
        private final ICoord mousePosition = new Coord(0, 0);
        private double angle;

        
    // Constructeur
    
    public Player(ICoord pos, IMovement mov) {
        super(pos, mov, 3);
        ALL_PLAYER.add(this);
    }
    
    
    // Requêtes
    
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
    
    public double getAngle() {
        return angle;
    }
    
    public static List getAllPlayer() {
        return ALL_PLAYER;
    }
    
    
    // Methode
    
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
     * de getPlayerMousePosition().
     */
    public void fire() {
        ICoord start = new Coord(getPosition().getCol() + getWeight() / 2,
                               getPosition().getRow() - getHeight() / 2);
        // On refresh l'angle du joueur par rapport à la souris.
        refreshAngle();
        PolarCoord pos = new PolarCoord(start.getCol(),
                                        start.getRow(),
                                        getAngle(), 0);
        IMovement mov = new PolarMovement(SPEED_PROJECT);
        new ProjectilePlayer(pos, mov, this);
    }
    
    public void refreshAngle() {
        // Recherche de l'angle
        // Mx = o.x + r * cos(O)
        // My = o.y + r * sin(O)
        int mx = getPlayerMousePosition().getCol();
        int my = getPlayerMousePosition().getRow();
        double r = Math.sqrt((mx - getPosition().getCol())
                * (mx - getPosition().getCol())
                + (my - getPosition().getRow()) 
                * (my - getPosition().getRow()));
                
        angle = ((mx - getPosition().getCol()) / r);
        angle = Math.acos(angle);
        
        if (my < getPosition().getRow()) {
            angle = Constante.MAX_DEGREE - Math.toDegrees(angle);
        } else {
            angle = Math.toDegrees(angle);
        }
    }
    
    public void kill() {
        super.kill();
        ALL_PLAYER.remove(this);
    }
}
