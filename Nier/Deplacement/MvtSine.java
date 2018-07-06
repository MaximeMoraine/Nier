package Nier.Deplacement;

/**
 * Calcul la prochaine position selon un mouvement sinusoïdale.
 * 
 * @cons
 *      $ARG$ int a, int b, int c
 *      $PRE$
 *          c != 0
 *      $POST$
 *          getCoefA() == a
 *          getCoefB() == b
 *          getCoefC() == c
 */
public class MvtSine extends Movement {

    // ATTRIBUTS
    
    private final int coefA;
    private int coefB;
    private final int coefC;
    private int reperC;
    private int resX;
    private int resY;
    
    // CONSTRUCTEUR
    
    public MvtSine(int a, int b, int c) {
        if (c == 0) {
            throw new AssertionError();
        }
        
        coefA = a; // coordonnée avancé en x
        coefB = b; // coordonnée avancé en y
        coefC = c; // au bout de combien de pulsation on doit redescendre
        
    }
    
    // REQUÊTES
    
    public int getCoefA() {
        return coefA;
    }
    
    public int getCoefB() {
        return coefB;
    }
    
    public int getCoefC() {
        return coefC;
    }
    
    public int getCol() {
        return resX;
    }
    
    public int getRow() {
        return resY;
    }
    
    public IMovement getOpposite() {
        return new MvtSine(-getCoefA(), -getCoefB(), getCoefC());
    }
    
    // COMMANDE
    
    /**
     * @arg ICoord p
     * @pre p!= null
     * @post
     *      getCol() ==
     *      getRow() ==
     */
    public void nextCoord(ICoord p) {
        super.nextCoord(p);
        
        resX = p.getCol() + getCoefA();
        resY = p.getRow() + getCoefB();
        ++reperC;
        
        if (reperC >= getCoefC()) {
            reperC = 0;
            coefB = -coefB;
        }
           
    }
    
}
