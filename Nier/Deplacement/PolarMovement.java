package nier.deplacement;

/**
 * Calcul la prochaine position selon un mouvement dédié au projectile.
 * Le projectile va utiliser les coordonnées polaire.
 * 
 * @cons
 *      $ARG$ int speed
 *      $POST$
 *          getSpeed() == speed
 */
public class PolarMovement extends Movement {
    
    // ATTRIBUTS
    
        private final int speed;
        private int resX;
        private int resY;
        
    // CONSTRUCTEUR
    
    public PolarMovement(int speed1) {
        speed = speed1;
    }
    
    // REQUÊTES
    
    public int getSpeed() {
        return speed;
    }
    
    public int getCol() {
        return resX;
    }
    
    public int getRow() {
        return resY;
    }
    
    public IMovement getOpposite() {
        return this;
    }
    
    // METHODE
    
    /**
     * @arg PolarCoord p
     * @pre p != null
     * @post
     *      on va avancer de getSpeed() sur l'axe de p.getAngle().
     */
    public void nextCoord(ICoord po) {
       super.nextCoord(po);
       PolarCoord p = (PolarCoord) po;
        
       p.setR(p.getR() + getSpeed());
       resX = p.searchCol();
       resY = p.searchRow();
    }
}
