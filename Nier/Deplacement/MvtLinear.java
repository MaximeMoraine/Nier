package nier.deplacement;

/**
 * Calcul la prochaine position selon un mouvement linéaire.
 * 
 * @cons
 *      $ARG$ int x, int y
 *      $POST$
 *          getCoefX() == x
 *          getCoefY() == y
 */
public class MvtLinear extends Movement {
    
    // ATTRIBUTS
    
        private final int coefX;
        private final int coefY;
        private int resX;
        private int resY;
        
    // CONSTRUCTEUR
    
    public MvtLinear(int x, int y) {
        coefX = x;
        coefY = y;
    }
    
    // REQUÊTES
    
    public int getCoefX() {
        return coefX;
    }
    
    public int getCoefY() {
        return coefY;
    }
    
    public int getCol() {
        return resX;
    }
    
    public int getRow() {
        return resY;
    }
    
    public IMovement getOpposite() {
        return new MvtLinear(-getCoefX(), -getCoefY());
    }
    
    // COMMANDE
    
    /**
     * @arg ICoord p
     * @pre p != null
     * @post
     *      getCol() == p.getCol() + getCoefX()
     *      getRow() == p.getRow() + getCoefY()
     */
    public void nextCoord(ICoord p) {
        super.nextCoord(p);
        
        resX = p.getCol() + getCoefX();
        resY = p.getRow() + getCoefY();
    }
}
