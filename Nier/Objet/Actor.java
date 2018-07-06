package Nier.Objet;

import Nier.Deplacement.ICoord;
import Nier.Deplacement.IMovement;

/**
 * Implèmente l'interface IActor.
 */
public abstract class Actor extends Obj implements IActor {
    // ATTRIBUTS
        private final int maxLife;
        private int life;
        private final ICoord position;
        private IMovement movement;
        private int speed;
        
    // CONSTRUCTEUR
    
    protected Actor(ICoord pos, IMovement mov, int hp) {
        if (pos == null || mov == null
            || hp <= 0) {
            throw new AssertionError();
        }
        
        maxLife = hp;
        life = hp;
        position = pos;
        movement = mov;
    }
    
    // REQUÊTES
    
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
    
    // METHODE
    
    /**
     * @pre
     *      l >= 0
     * @post
     *      getLife() == l
     */
    public void setLife(int l) {
        if (life < 0) {
            throw new AssertionError();
        }
        
        life = l;
    }
    
    public void setMovement(IMovement m) {
        if (m == null || !isAlive()) {
            throw new AssertionError();
        }
        
        movement = m;
    }
}
