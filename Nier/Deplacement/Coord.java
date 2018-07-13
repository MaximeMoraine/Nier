package nier.deplacement;

import nier.exception.OutOfAreaException;

/**
 * Implèmente l'interface ICoord.
 * Coord ne travaille toujours qu'avec des coordonnées correcte.
 * @author Moraine Maxime
 */
public class Coord implements ICoord {

    // ATTRIBUTS
    
    private int col;
    private int row;
    
    // CONSTRUCTEUR
    
    // Constructeur pour les coordonnées cartésienne.
    public Coord(int x, int y) {
        //if (FIRST_COL - ADD_SIZE > x || x > LAST_COL + ADD_SIZE
        //    || FIRST_ROW - ADD_SIZE > y || y > LAST_ROW + ADD_SIZE) {
        //        throw new AssertionError();
        //}
        
        col = x;
        row = y;
    }
    
    
    // REQUÊTE
    
    public boolean equals(Object c) {
        return c != null
            && c.getClass() == getClass()
            && ((ICoord) c).getCol() == getCol()
            && ((ICoord) c).getRow() == getRow();
    }
    
    public int hashCode() {
        return getCol() + getRow();
    }
    
    public int getCol() {
        return col;
    }
    
    public int getRow() {
        return row;
    }
    
    /**
     * Indique si cette coordonnée est alignée avec k (horizontalement,
     *  verticalement ou en diagonale).
     * @pre <pre>
     *     k != null </pre>
     */
    public boolean isAlignedWith(ICoord c) {
        if (c == null) {
            throw new AssertionError();
        }
       
        int d1 = Math.abs(c.getCol() - getCol());
        int d2 = Math.abs(c.getRow() - getRow());
        
        return (d1 == 0 || d2 == 0 || d1 == d2);
    }
        
    public String toString() {
        return "(" + getCol() + "," + getRow() + ")";
    }
    
    
    // METHODE
    
    /**
     * @pre correctCol(x)
     * @post getCol() == x
     */
    public void setCol(int x) throws OutOfAreaException {
        if (!correctCol(x)) {
            throw new OutOfAreaException();
        }
        
        col = x;
    }
    
    public void setRow(int y) throws OutOfAreaException {
        if (!correctRow(y)) {
            throw new OutOfAreaException();
        }
        
        row = y;
    }
    
    
    // OUTILS
    
    private boolean correctCol(int x) {
        return FIRST_COL <= x && x <= LAST_COL;
    }
    
    private boolean correctRow(int y) {
        return FIRST_ROW <= y && y <= LAST_ROW;
    }
}
