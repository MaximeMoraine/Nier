package Nier.Objet;

import Nier.Deplacement.*;
import Nier.Exception.OutOfAreaException;

/**
 * Classe héritiére de Actor, implémentant
 * les Source (génère des projectiles).
 */
public abstract class Generator extends Actor {
    // CONSTANTE
    
        public static final int FORM = Obj.GENERATOR;
        public static final int WEIGHT = 30;
        public static final int HEIGHT = 30;
        public static final int SPEED_PROJECT = 2;
        
        public static final int MALLOW = 1;
        public static final int RED = 0;
        
    // ATTRIBUTS
    
        private final int interval;
        protected int cmpt = 0; // Compteur pour generate()
        protected int color = RED;

        
    // CONSTRUCTEUR
    
    /**
     * L'interval (inter) définit au bout de combien de tir, le génèrateur
     * changera de couleur.
     * 
     * @pre
     *      inter > 0
     */
    public Generator(ICoord pos, IMovement mov, int inter) {
        super(pos, mov, 4);
        
        if (inter <= 0) {
            throw new AssertionError();
        }
        
        interval = inter;
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
    
    public int getInterval() {
        return interval;
    }
    
    public int getCmpt() {
        return cmpt;
    }
    
    public int getColor() {
        return color;
    }
    
    // METHODE
    
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
              
}
