package Nier.Objet;

import Nier.Deplacement.ICoord;
import Nier.Deplacement.Coord;
import Nier.Deplacement.PolarCoord;
import Nier.Deplacement.IMovement;

import Nier.Exception.OutOfAreaException;

public abstract class Projectile extends Obj implements IProjectile {
    
    // CONSTANTE
        public static final int NBR_COULEUR_PROJECT = 2;
    // Attributs
        private final int maxLife;
        protected int life;
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
        
        pos = new PolarCoord(pos.getCol() - getWeight() / 2,
                        pos.getRow() + getHeight() / 2,
                        pos.getAngle(), pos.getR());

        position = pos;
        movement = mov;
        IProjectile.allProjectiles().add(this);
        creator = creat;
    }
    
    // Requête
    
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
   
    // Commande
    
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
        IProjectile.allProjectiles().remove(this);
        Nier.Game.getFenetre().removeObj(this);
    }
    
    /**
     * Renvoie true si le projectile peut être détruit par le joueur,
     * false sinon.
     * 
     * @pre
     *      isAlive()
     */
    public abstract boolean canBeDestroyed();
}
