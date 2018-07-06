package Nier.Deplacement;

import Nier.Exception.OutOfAreaException;

/**
 * Classe gérant les coordonnées polair.
 * 
 * @cons
 *      $ARG$
 *          Coord start_
 *          double r_
 *          int angle_
 *      $PRE$
 *          start_ != null
 *          angle >= 0
 *      $POST
 *          getStart() == start_
 *          getR() == r_
 *          getAngle() == angle_
 */
public class PolarCoord extends Coord {
    
    // ATTRIBUTS
        
    private int r;
    private double angle;
    
    private int row;
    private int col;
    private int[] firstCoord = new int[2];
    
    // CONSTRUCTEUR
    
                      //start
    public PolarCoord(int x, int y, double angle_, int r_) {
        super(x, y);
        
        r = r_;
        angle = angle_;
        firstCoord[0] = getCol();
        firstCoord[1] =getRow();
    }
    
    // Requete
    
    public int getR() {
        return r;
    }
    
    public double getAngle() {
        return angle;
    }
    
    public String toString() {
        return "pol: (" + getAngle() + "," + getR() + ")"
                + "cart: (" + getCol() + "," + getRow() + ")";
    }
    
    // METHODE
    
    /**
     * Calcul la colonne associé au coordonnée polair.
     */
    public int searchCol() {
        return firstCoord[0] + (int) Math.ceil(getR() * Math.cos(Math.toRadians(getAngle())));
    }
    
    /**
     * Calcul la ligne associée au coordonnée polair.
     */
    public int searchRow() {
        return firstCoord[1] + (int) Math.ceil(getR() * Math.sin(Math.toRadians(getAngle())));
    }
    
    public void setR(int r_) {
        r = r_;
    }
    
}