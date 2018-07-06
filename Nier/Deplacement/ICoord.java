package Nier.Deplacement;

import Nier.Exception.OutOfAreaException;

/**
 * Type des coordonnées à associer à un autre Objet.
 * 
 * @author Moraine Maxime
 * @version 18/05/2018
 * 
 * @inv
 *     equals(c) <==>
 *         c != null
 *         && c.getClass() == getClass()
 *         && c.getCol() == getCol()
 *         && c.getRow() == getRow()
 *     FIRST_COL <= getCol() <= LAST_COL
 *     FIRST_ROW <= getRow() <= LAST_ROW
 *     Soit d1 ::= abs(c.getCol() - getCol())
 *          d2 ::= abs(c.getRow() - getRow())
 *     isAlignedWith(k) <==> d1 == 0 || d2 == 0 || d1 == d2
 *     toString().equals(String.valueOf(getCol()) + String.valueOf(getRow()))
 *     
 * @cons
 *      $ARG$ int x, int y
 *      $PRE$ FIRST_COL <= x <= LAST_COL
 *            FIRST_ROW <= y <= LAST_ROW
 *      $POST$
 *          getCol() == x
 *          getRow() == y
 */
public interface ICoord {
    
    // CONSTANTES
    
    /**
     * Valeur minimale des colonnes.
     */
    int FIRST_COL = 0;
    
    /**
     * Valeur maximale des colonnes.
     */
    int LAST_COL = 1500;
    
    /**
     * Valeur minimale des lignes.
     */
    int FIRST_ROW = 0;
    
    /**
     * Valeur maximale des lignes.
     */
    int LAST_ROW = 700;
    
    /**
     * Agrandissement de la fenetre.
     */
    int ADD_SIZE = 50;

    // REQUETES
    
    /**
     * Indique si cette coordonnée est similaire à k.
     */
    boolean equals(Object k);
    
    /**
     * La colonne.
     */
    int getCol();
    
    /**
     * La ligne.
     */
    int getRow();
    
    /**
     * Une représentation textuelle de cette coordonnée.
     */
    String toString();  
    
    // METHODE
    
    /**
     * @pre correctRow(y)
     * @post getRow() == y
     */
    void setRow(int y) throws OutOfAreaException;
    
    /**
     * @pre correctCol(x)
     * @post getCol() == x
     */
    void setCol(int x) throws OutOfAreaException;
}
