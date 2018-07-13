package nier.objet;

import nier.deplacement.ICoord;
import nier.deplacement.PolarCoord;
import nier.deplacement.IMovement;
import java.util.List;
import java.util.ArrayList;

import nier.exception.OutOfAreaException;

public abstract class Projectile extends Obj implements IProjectile {
    
    // Constante
    
    public static final List ALL_PROJECTILES = new ArrayList();
 
    
    // Attributs
    
    private final int maxLife;
    private int life;
    private int dammage;
    private final ICoord position;
    private IMovement movement;
    private Actor creator;
        
        
    // Constructeur
    
    protected Projectile(PolarCoord pos, IMovement mov,
            int dmg, int hp, Actor creat) {
        if (pos == null || mov == null || creat == null
                || dmg <= 0 || hp <= 0) {
            throw new AssertionError();
        }
        
        maxLife = hp;
        life = hp;
        dammage = dmg;
        
        // On centre le projectile.
        pos = new PolarCoord(pos.getCol() - getWeight() / 2,
                        pos.getRow() + getHeight() / 2,
                        pos.getAngle(), pos.getR());

        position = pos;
        movement = mov;
        ALL_PROJECTILES.add(this);
        creator = creat;
    }
    
    
    // Requête
    
    /**
     * Renvoie l'ensemble des projectiles créés et encore effectif
     * sous forme de liste.
     */
    public static List getAllProjectiles() {
        return ALL_PROJECTILES;
    }
    
    public int getMaxLife() {
        return maxLife;
    }
    
    public int getLife() {
        return life;
    }
    
    public int getDammage() {
        return dammage;
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
    
    public Actor getCreator() {
        return creator;
    }
   
    
    // Methode
    
    public void setPosition(int x, int y) {
        if (!isAlive()) {
            throw new AssertionError();
        }
        
        try {
            getPosition().setCol(x);
            getPosition().setRow(y);
        } catch (OutOfAreaException e) {
            throw new AssertionError();
        }
    }
    
    public void setMovement(IMovement m) {
        if (m == null || !isAlive()) {
            throw new AssertionError();
        }
        
        movement = m;
    }
    
    public void setLife(int p) {
        life = p;
    }
        
    public void push() {
        if (!isAlive()) {
            throw new AssertionError();
        }

        getMovement().nextCoord(getPosition());
        try {
            getPosition().setCol(getMovement().getCol());
            getPosition().setRow(getMovement().getRow());
        } catch (OutOfAreaException e) {
            life = 0; // On tue le projectile car il est
                        // apparament sorti de la carte.
        }
    }
    
    public void kill() {
        ALL_PROJECTILES.remove(this);
        nier.Game.FENETRE.removeObj(this);
    }
    
    public abstract boolean canBeDestroyed();
}
