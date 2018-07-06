package Nier.Objet;

import Nier.Deplacement.ICoord;
import Nier.Deplacement.IMovement;

/**
 * Classe héritiére de Actor, implémentant les joueurs.
 */
public class Enemy extends Actor {
    // CONSTANTE
    
        public static final int FORM = Obj.ENEMY;
        public static final int WEIGHT = 40;
        public static final int HEIGHT = 40;

    public Enemy(ICoord pos, IMovement mov) {
        super(pos, mov, 4);
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
}
