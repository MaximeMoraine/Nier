package nier.objet;

import nier.constante.Constante;
import nier.deplacement.ICoord;
import nier.deplacement.IMovement;

/**
 * Classe héritiére de Actor, implémentant les joueurs.
 */
public class Enemy extends Actor {
    
    // Constante
    
        public static final int FORM = Constante.ENEMY;
        public static final int WEIGHT = 40;
        public static final int HEIGHT = 40;

    public Enemy(ICoord pos, IMovement mov) {
        super(pos, mov, 4);
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
    
    
    // Methode
    
    public void kill() {
        super.kill();
    }
}
