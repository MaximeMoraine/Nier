package nier.objet;

import nier.constante.Constante;
import nier.deplacement.PolarCoord;
import nier.deplacement.IMovement;

/**
 * Projectile mauve, non détruisable par le joueur.
 */
public class ProjectileMallow extends EnnemyProject {
   
    // Constantes
    
        private static final int FORM = Constante.CIRCLE;
        private static final int WEIGHT = 18;
        private static final int HEIGHT = 18;
        
        
    // Constructeur
    
    public ProjectileMallow(PolarCoord pos, IMovement mov, Actor creat) {
        super(pos, mov, 1, 1, creat);
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
       
    public boolean canBeDestroyed() {
        if (!isAlive()) {
            throw new AssertionError();
        }
        
        return false;
    }
}
