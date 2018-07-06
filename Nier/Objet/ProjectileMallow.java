package Nier.Objet;

import Nier.Deplacement.ICoord;
import Nier.Deplacement.PolarCoord;
import Nier.Deplacement.IMovement;

import java.util.Set;
import java.util.HashSet;

/**
 * Projectile mauve, non détruisable par le joueur.
 */
public class ProjectileMallow extends EnnemyProject {
   
    // CONSTANTES
    
        private static final int FORM = Obj.CIRCLE;
        private static final int WEIGHT = 18;
        private static final int HEIGHT = 18;
        
    // CONSTRUCTEUR
    
    public ProjectileMallow(PolarCoord pos, IMovement mov, Actor creat) {
        super(pos, mov, 1, 1, creat);
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
