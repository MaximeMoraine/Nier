package nier.objet;

import nier.deplacement.ICoord;
import nier.deplacement.IMovement;

/**
 * Interface définissant les propriétés des acteurs du jeu.
 *
 * @author Maxime Moraine
 * 
 * @inv
 *      0 <= getLife() <= getMaxLife()
 *      getPosition() != null
 *      isAlive() <==> (getLife() > 0)
 *      getMovement() != null
 *      
 * @cons
 *      $ARG$
 *          ICoord pos, IMovement mov, int hp
 *      $PRE$
 *          pos != null
 *          mov != null
 *          hp > 0
 *      $POST$
 *          getMaxLife() == hp
 *          getLife() == hp
 *          getPosition() == pos
 *          getMovement() == mov
 */

public interface IActor {
    
    // Requetes
    
    /**
     * Renvoie les hp max de l'acteur.
     */
    int getMaxLife();
    
    /**
     * Renvoie les hp actuel de l'acteur.
     */
    int getLife();
    
    /**
     * Renvoie la position de l'acteur.
     */
    ICoord getPosition();
    
    /**
     * Renvoie le mouvement de l'acteur.
     */
    IMovement getMovement();
    
    /**
     * Renvoie si l'acteur est en vie ou non.
     */
    boolean isAlive();
    
    
    // Methode
    
     /**
     * @pre
     *      l >= 0
     * @post
     *      getLife() == l
     */
    void setLife(int l);
    
    /**
     * @pre
     *      m != null
     * @post
     *      getMovement() == m
     */
    void setMovement(IMovement m);
    
    /**
     * @pre
     *      pos != null
     * @post
     *      getPosition() == pos
     */
    void setPosition(ICoord pos);
}
