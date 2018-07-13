package nier.objet;

import nier.deplacement.IMovement;
import nier.deplacement.PolarCoord;
import nier.deplacement.ICoord;

import java.util.Iterator;

/**
 * Regroupe les classes concernant les projectiles ennemis
 * afin de factoriser le traitement des colisions avec le joueurs.
 */
public abstract class EnnemyProject extends Projectile {
    
    // Constructeur
    
    public EnnemyProject(PolarCoord pos, IMovement mov, int a,
            int b, Actor creat) {
                
        super(pos, mov, a, b, creat);
    }
    
    
    // Methode
    
    /**
     * étend push de projectile en rajoutant le traitement des colisions.
     */
    public void push() {
        super.push();
        
        //Gestion des colisions.
        if (getLife() > 0) {
        
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
                int pX2 = getPosition().getCol() + getWeight() - per8X;
                int pY = getPosition().getRow() - per8Y;
                int pY2 = getPosition().getRow() - getHeight() + per8Y;
                
                // Point cardinaux recevant les collisions sur le joueur
                int point1X = c.getCol() + moitX;
                int point2X = c.getCol();
                int point3X = c.getCol() + p.getWeight();
                int point1Y = c.getRow();
                int point2Y = c.getRow() - p.getHeight() + 1;
                int point3Y = c.getRow() - p.getHeight() + 1;

                
                // Si le joueur est dans la hit box du projectile.
                // 9 points cardinaux
                if (pX <= point1X && point1X <= pX2
                    &&  pY >= point1Y && point1Y >= pY2
                 ||
                        pX <= point2X && point2X <= pX2
                    &&  pY >= point1Y && point1Y >= pY2
                 ||
                        pX <= point3X && point3X <= pX2
                    &&  pY >= point1Y && point1Y >= pY2
                 ||
                        pX <= point1X && point1X <= pX2
                    &&  pY >= point2Y && point2Y >= pY2
                 ||
                        pX <= point2X && point2X <= pX2
                    &&  pY >= point2Y && point2Y >= pY2
                 ||
                        pX <= point3X && point3X <= pX2
                    &&  pY >= point2Y && point2Y >= pY2
                 ||
                        pX <= point1X && point1X <= pX2
                    &&  pY >= point3Y && point3Y >= pY2
                 ||
                        pX <= point2X && point2X <= pX2
                    &&  pY >= point3Y && point3Y >= pY2
                 ||
                        pX <= point3X && point3X <= pX2
                    &&  pY >= point3Y && point3Y >= pY2                 
                    ) {
                    
                    this.setLife(0);
                    p.setLife(p.getLife() - this.getDammage());
                }
            }
        }
    }

}