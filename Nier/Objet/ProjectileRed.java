package nier.objet;

import nier.deplacement.PolarCoord;
import nier.deplacement.IMovement;
import nier.constante.Constante;
import java.util.List;
import java.util.ArrayList;


/**
 * Projectile rouge, détruisable par le joueur.
 */
public class ProjectileRed extends EnnemyProject {
   
    // Constante
    
        private static final int FORM = Constante.CIRCLE;
        private static final int WEIGHT = 22;
        private static final int HEIGHT = 22;
        public static final List ALL_PROJECTILE_RED = new ArrayList();
        
        
    // Constructeur
    
    public ProjectileRed(PolarCoord pos, IMovement mov, Actor creat) {
        super(pos, mov, 1, 1, creat);
        
        ALL_PROJECTILE_RED.add(this);
    }
    
    
    // Requêtes
    
    public static List getAllProjectileRed() {
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
    
    
    // Methode
    
    /**
     * étend kill de Projectile afin de supprimer se projectile de
     * ALL_PROJECTILE_RED.
     */
    public void kill() {
        super.kill();
        ALL_PROJECTILE_RED.remove(this);
    }
}
