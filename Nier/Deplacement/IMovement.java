package nier.deplacement;

/**
 * Objet gérant les mouvements des objets permettant ainsi de calculer
 * leur position futur.
 *
 * @author  Moraine Maxime
 */
public interface IMovement {
    
    // REQUÊTE
    
    /**
     * Renvoie la colonne calculé.
     */
    int getCol();
    
    /**
     * Renvoie la ligne calculé.
     */
    int getRow();
    
    /**
     * Renvoie le mouvement contraire du mouvement this.
     */
    IMovement getOpposite();
    
    // METHODE
    
    /**
    * Calcul la prochaine coordonnée (x,y) (selon la définition
    * dans les sous classes).
    */
    void nextCoord(ICoord p);
  
}
