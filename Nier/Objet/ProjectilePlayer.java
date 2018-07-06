package Nier.Objet;

import Nier.Deplacement.ICoord;
import Nier.Deplacement.PolarCoord;
import Nier.Deplacement.IMovement;

/**
 * Projectile rouge, détruisable par le joueur.
 */
public class ProjectilePlayer extends Projectile {
   
    // CONSTANTES
    
        public static final int FORM = Obj.PROJECT_PLAYER;
        public static final int WEIGHT = 14;
        public static final int HEIGHT = 14;
        
    // CONSTRUCTEUR
    
    public ProjectilePlayer(PolarCoord pos, IMovement mov, Actor creat) {
        super(pos, mov, 4, 4, creat);
    }
    
    // REQUETE

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
        
        return false;
    }
}
