package Nier.Objet;

import Nier.Deplacement.PolarCoord;
import Nier.Deplacement.IMovement;

import java.util.Set;
import java.util.HashSet;

import java.util.Iterator;

/**
 * Projectile rouge, détruisable par le joueur.
 */
public class ProjectileRed extends EnnemyProject {
   
    // CONSTANTES
    
        private static final int FORM = Obj.CIRCLE;
        private static final int WEIGHT = 22;
        private static final int HEIGHT = 22;
        
        public static Set ALL_PROJECTILE_RED = new HashSet();
        
    // CONSTRUCTEUR
    
    public ProjectileRed(PolarCoord pos, IMovement mov, Actor creat) {
        super(pos, mov, 1, 1, creat);
        
        ALL_PROJECTILE_RED.add(this);
    }
    
    // REQUETE
    
    public Set getAllProjectileRed() {
        return ALL_PROJECTILE_RED;
    }

    public int getForm() {
        return FORM;
    }
    
    public int getWeight() {
        return WEIGHT;
    }
    
    public int getHeight() {
        return HEIGHT;
    }
    
    public boolean canBeDestroyed() {
        if (!isAlive()) {
            throw new AssertionError();
        }
        
        return true;
    }
        
}
