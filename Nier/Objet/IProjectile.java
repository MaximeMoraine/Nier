package Nier.Objet;

import Nier.Deplacement.ICoord;
import Nier.Deplacement.IMovement;
import java.util.HashSet;
import java.util.Set;

/**
 * Permet la création de projectile permettant ainsi d'attaquer
 * le camp adverse.
 *
 * @author  Moraine Maxime
 * @version 17/05/2018
 * 
 * @inv
 *      0 <= getLife() <= getMaxLife()
 *      getPosition() != null
 *      isAlive() <==> (life() > 0)
 *      getMovement() != null
 *      getDammage() > 0
 *      
 * @cons
 *      $ARGS$ ICoord pos, IMovement mov, int moveSpeed, int dmg,
 *             int hp, Actor creat
 *      $PRE$
 *          pos != null
 *          mov != null
 *          dmg > 0
 *          hp > 0
 *          creat != null
 *      $POST$
 *          getMaxLife() == hp
 *          getLife() == hp
 *          getDammage() == dmg
 *          getPosition() == pos
 *          getMovement() == mov
 *          getCreator() == creat
 */

public interface IProjectile {
    // VARIABLE STATIC
    
    Set ALL_PROJECTILES = new HashSet();
    
    // Requetes
    
    /**
     * Renvoie les hp max du projectile.
     */
    int getMaxLife();
    
    /**
     * Renvoie les hp actuel du projectile.
     */
    int getLife();
    
    /**
     * Renvoie les dommages du projectile.
     */
    int getDammage();
    
    /**
     * Renvoie la position du projectile.
     */
    ICoord getPosition();
    
    /**
     * Renvoie le mouvement du projectile.
     */
    IMovement getMovement();
    
    /**
     * Renvoie si le projectile est en vie ou non.
     */
    boolean isAlive();
    
    /**
     * Renvoie l'ensemble des projectiles créés et encore effectif
     * sous forme de liste.
     */
    static Set allProjectiles() {
        return ALL_PROJECTILES;
    }
    
    // Commandes
    
    /**
     * Change la position du projectile selon celle envoyé en paramètre.
     * 
     * @arg int x, int y
     * @pre x et y sont des coordonnées valide selon la
     *      spécification de ICoord sinon, renvoie une
     *      AssertionError.
     *      && isAlive()
     * @post
     *      getPosition().getCol() == x
     *      getPosition().getRow() == y
     *      
     *      getMaxLife() == old getMaxLife()
     *      getLife() == old getLife()
     *      getDammage() == old getDammage()
     *      getMovement() == old getMovement()
     */
    void setPosition(int x, int y);
        
    /**
     * Change le mouvement du projectile selon celui envoyé en paramètre.
     * 
     * @arg IMovement m
     * @pre
     *      m != null
     *      isAlive()
     * @post
     *      getMovement() == m
     *      
     *      getMaxLife() == oldgetMaxLife()
     *      getLife() == old getLife()
     *      getDammage() == old getDammage()
     *      getPosition() == old getPosition()
     */
    void setMovement(IMovement m);
    
    /**
     * Tue le projectile en question en le supprimant de la
     * liste des projectiles existant, il perdra ainsi tous référencement.
     */
    void kill();
    
    /**
     * Change les coordonnées du projectile selon ca méthode de déplacement
     * getMovement() et ces coordonnées actuels getPosition().
     * Si les futures coordonnées sont refuser, le projectile meurt.
     * 
     * @pre
     *      isAlive()
     * @post
     *      getPosition().getCol() == getMovement().getCol()
     *      getPostion().getRow() == getMovement().getRow()
     *      ( après avoir appelé getMovement().newtCoord(getPosition()) )
     *      
     *      Si il y a une OutOfAreaException, le projectile meurt.
     */
    void push();
    
}
