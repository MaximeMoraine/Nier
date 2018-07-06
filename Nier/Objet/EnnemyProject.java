package Nier.Objet;

import Nier.Deplacement.*;
import Nier.Deplacement.ICoord;

import java.util.Iterator;

/**
 * Décrivez votre classe abstraite EnnemyProject ici.
 *
 * @author  (votre nom)
 * @version (un numéro de version ou une date)
 */
public abstract class EnnemyProject extends Projectile {
    
    // CONSTRUCTEUR
    
    public EnnemyProject(PolarCoord pos, IMovement mov, int a, int b, Actor creat) {
        super(pos, mov, a, b, creat);
    }
    
    // METHODE
    
    /**
     * étend push de projectile en rajoutant le traitement des colisions.
     */
    public void push() {
        super.push();
        
        //Gestion des colisions.
        if (getLife() > 0 ) {
        
            Iterator i = Player.getAllPlayer().iterator();
            while (i.hasNext()) {
                Player p = (Player) i.next();
                ICoord c = p.getPosition();
                
                int moitX = p.getWeight() / 2;
                int moitY = p.getHeight() / 2;
                int per8X = getWeight() / 8;
                int per8Y = getHeight() / 8;
                
                // Carré de colision avec le projectile
                int pX = getPosition().getCol() + per8X;
                int p_X = getPosition().getCol() + getWeight() - per8X;
                int pY = getPosition().getRow() - per8Y;
                int p_Y = getPosition().getRow() - getHeight() + per8Y;
                
                // Point cardinaux recevant les collisions sur le joueur
                int point1X = c.getCol();
                int point2X = c.getCol() + moitX;
                int point3X = c.getCol() + p.getWeight();
                int point1Y = c.getRow();
                int point2Y = c.getRow() - moitX;
                int point3Y = c.getRow() - p.getHeight();

                
                // Si le joueur est dans la hit box du projectile.
                // 9 points cardinaux
                if (    pX <= point1X && point1X <= p_X
                    &&  pY >= point1Y && point1Y >= p_Y
                 ||
                        pX <= point2X && point2X <= p_X
                    &&  pY >= point1Y && point1Y >= p_Y
                 ||
                        pX <= point3X && point3X <= p_X
                    &&  pY >= point1Y && point1Y >= p_Y
                 ||
                        pX <= point1X && point1X <= p_X
                    &&  pY >= point2Y && point2Y >= p_Y
                 ||
                        pX <= point2X && point2X <= p_X
                    &&  pY >= point2Y && point2Y >= p_Y
                 ||
                        pX <= point3X && point3X <= p_X
                    &&  pY >= point2Y && point2Y >= p_Y
                 ||
                        pX <= point1X && point1X <= p_X
                    &&  pY >= point3Y && point3Y >= p_Y
                 ||
                        pX <= point2X && point2X <= p_X
                    &&  pY >= point3Y && point3Y >= p_Y
                 ||
                        pX <= point3X && point3X <= p_X
                    &&  pY >= point3Y && point3Y >= p_Y                 
                    ) {
                    
                    this.life = 0;
                    p.setLife(p.getLife() - this.getDammage());
                }
            }
        }
    }

}