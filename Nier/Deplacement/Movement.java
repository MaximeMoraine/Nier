package nier.deplacement;

/**
 * Implèmente en partie l'interface IMovement
 * 
 * Movement calcul la prochaine coordonnée selon une
 * certaine équation et se moque éperdument de savoir
 * si les coordonnée résultantes de l'équation sont
 * correcte.
 */
public abstract class Movement implements IMovement {
        
    // COMMANDE
    
    public void nextCoord(ICoord p) {
        if (p == null) {
            throw new AssertionError();
        }
    }
    
}
