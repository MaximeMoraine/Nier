package nier.objet;

import nier.deplacement.ICoord;
import nier.deplacement.IMovement;
import java.util.List;
import java.util.ArrayList;

/**
 * Implèmente l'interface IActor.
 */
public abstract class Actor extends Obj implements IActor {
    
    // Constante
    
    public static final List ALL_ACTOR = new ArrayList();
    
    
    // Attributs
    
        private final int maxLife;
        private int life;
        private ICoord position;
        private IMovement movement;
        
        
    // Constructeur
    
    protected Actor(ICoord pos, IMovement mov, int hp) {
        if (pos == null || mov == null
            || hp <= 0) {
            throw new AssertionError();
        }
        
        maxLife = hp;
        life = hp;
        position = pos;
        movement = mov;
        ALL_ACTOR.add(this);
    }
    
    
    // Requêtes
    
    public int getMaxLife() {
        return maxLife;
    }
    
    public int getLife() {
        return life;
    }
    
    public ICoord getPosition() {
        return position;
    }
    
    public IMovement getMovement() {
        return movement;
    }
    
    public boolean isAlive() {
        return getLife() > 0;
    }
    
    public static List getAllActor() {
        return ALL_ACTOR;
    }
    
    
    // Methode
    
    public void setLife(int l) {
        if (life < 0) {
            throw new AssertionError();
        }
        
        life = l;
    }
    
    public void setMovement(IMovement m) {
        if (m == null) {
            throw new AssertionError();
        }
        
        movement = m;
    }
    
    public void setPosition(ICoord pos) {
        if (pos == null) {
            throw new AssertionError();
        }
        
        position = pos;
    }
    
    public void kill() {
        ALL_ACTOR.remove(this);
        nier.Game.FENETRE.removeObj(this);
    }
}
