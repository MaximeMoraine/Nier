package nier.objet;

import nier.deplacement.IMovement;
import nier.deplacement.ICoord;
import nier.exception.OutOfAreaException;
import nier.constante.Constante;
import java.util.List;
import java.util.ArrayList;

/**
 * Classe héritiére de Actor, implémentant
 * les générateur (génère des projectiles).
 */
public abstract class Generator extends Actor {
    
    // Constante
    
        public static final int FORM = Constante.GENERATOR;
        public static final int WEIGHT = 30;
        public static final int HEIGHT = 30;
        public static final int MALLOW = -1;
        public static final int RED = 0;
        public static final List ALL_GENERATOR = new ArrayList();
        
        
    // Attributs
    
        private final int interval;
        private final int speedProject;
        
        
    // Constructeur
    
    /**
     * L'interval (inter) définit au bout de combien de tir, le génèrateur
     * changera de couleur.
     * 
     * inter == 0 -> Red
     * inter == -1 -> Mallow
     * else swith between Red and Mallow
     */
    public Generator(ICoord pos, IMovement mov, int inter, int speedProject1) {
        super(pos, mov, 4);
        
        speedProject = speedProject1;
        interval = inter;
        ALL_GENERATOR.add(this);
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
    
    public int getInterval() {
        return interval;
    }
    
    public static List getAllGenerator() {
        return ALL_GENERATOR;
    }
    
    public int getSpeedProject() {
        return speedProject;
    }
    
    
    // Methode
    
    /**
     * Génére des projectiles et change de couleur selon l'interval
     * définit lors de l'appel au constructeur.
     */
    protected abstract void generate();
    
    public void push() {
        if (!isAlive()) {
            throw new AssertionError();
        }
        
        getMovement().nextCoord(getPosition());
        try {
            getPosition().setCol(getMovement().getCol());
            getPosition().setRow(getMovement().getRow());
        } catch (OutOfAreaException e) {
            setMovement(getMovement().getOpposite());
        }
    }
    
    public void kill() {
        super.kill();
        ALL_GENERATOR.remove(this);
    }
              
}
