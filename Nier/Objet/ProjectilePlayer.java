package nier.objet;

import nier.constante.Constante;
import nier.deplacement.ICoord;
import nier.deplacement.PolarCoord;
import nier.deplacement.IMovement;

import java.util.Iterator;

/**
 * Projectile du joueurs.
 */
public class ProjectilePlayer extends Projectile {
   
    // Constantes
    
        public static final int FORM = Constante.PROJECT_PLAYER;
        public static final int WEIGHT = 14;
        public static final int HEIGHT = 14;
        
        
    // Constructeur
    
    public ProjectilePlayer(PolarCoord pos, IMovement mov, Actor creat) {
        super(pos, mov, 1, 1, creat);
    }
    
    
    // Requête

    public int getForm() {
        return FORM;
    }
    
    public int getWeight() {
        return WEIGHT;
    }
    
    public int getHeight() {
        return HEIGHT;
    }
    
    public boolean canBeDestroyed() {
        if (!isAlive()) {
            throw new AssertionError();
        }
        
        return false;
    }
    
    
    // Methode
    
    /**
     * étend push de projectile en rajoutant le traitement des colisions
     * associé aux projectiles du joueur.
     */
    public void push() {
        super.push();
        
        //Gestion des colisions.
        
        if (getLife() > 0) {
            Iterator i = Generator.getAllGenerator().iterator();
            
            while (i.hasNext()) {
                Generator g = (Generator) i.next();
                ICoord c = g.getPosition();
                
                int moitX = g.getWeight() / 2;
                int moitY = g.getHeight() / 2;
                int per8X = getWeight() / 8;
                int per8Y = getHeight() / 8;
                
                // Carré de colision avec le projectile
                int pX = getPosition().getCol() + per8X;
                int pX2 = getPosition().getCol() + getWeight() - per8X;
                int pY = getPosition().getRow() - per8Y;
                int pY2 = getPosition().getRow() - getHeight() + per8Y;
                
                // Point cardinaux recevant les collisions sur le generateur
                int point1X = c.getCol();
                int point2X = c.getCol() + moitX;
                int point3X = c.getCol() + g.getWeight();
                int point1Y = c.getRow();
                int point2Y = c.getRow() - moitY;
                int point3Y = c.getRow() - g.getHeight();

                
                // Si le generateur est dans la hit box du projectile.
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
                    g.setLife(g.getLife() - this.getDammage());
                }
            }
            
            i = ProjectileRed.getAllProjectileRed().iterator();
            
            while (i.hasNext()) {
                ProjectileRed p = (ProjectileRed) i.next();
                ICoord c = p.getPosition();
                
                int per8Xp = p.getWeight() / 8;
                int moitXp = p.getWeight() / 2;
                int per8Yp = p.getHeight() / 8;
                int moitYp = p.getHeight() / 2;
                int per8X = getWeight() / 8;
                int per8Y = getHeight() / 8;
                
                // Carré de colision avec le projectile rouge
                int pX = getPosition().getCol() + per8X;
                int pX2 = getPosition().getCol() + getWeight() - per8X;
                int pY = getPosition().getRow() - per8Y;
                int pY2 = getPosition().getRow() - getHeight() + per8Y;
                
                // Point cardinaux recevant les collisions sur le projectile
                // du joueur
                int point1X = c.getCol() + per8Xp;
                int point2X = c.getCol() + moitXp;
                int point3X = c.getCol() + p.getWeight() - per8Xp;
                int point1Y = c.getRow() - per8Yp;
                int point2Y = c.getRow() - moitYp;
                int point3Y = c.getRow() - p.getHeight() + per8Yp;

                
                // Si le generateur est dans la hit box du projectile.
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
