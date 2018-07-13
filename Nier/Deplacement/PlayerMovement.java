package nier.deplacement;

/**
 * Mouvement des joueurs.
 */
public class PlayerMovement extends Movement {
    
    public static final int X_MOVEMENT = 10;
    public static final int Y_MOVEMENT = 10;
    
    private int resX;
    private int resY;
    
    // REQUÊTES
    
    public int getCol() {
        return resX;
    }
    
    public int getRow() {
        return resY;
    }
    
    public IMovement getOpposite() {
        return this;
    }
    
    // COMMANDE
    
    public void nextCoord(ICoord p, boolean z, 
            boolean q, boolean s, boolean d) {
        super.nextCoord(p);
        
        resX = p.getCol();
        resY = p.getRow();
                
        if (z) {
            pushY(p);
        }
        if (q) {
            pushX2(p);
        }
        if (s) {
            pushY2(p);
        }
        if (d) {
            pushX(p);
        }
    }
        
    // OUTILS
    
    /**
     * Incrémente la position en X du joueur.
     */
    private void pushX(ICoord x) {
       resX += X_MOVEMENT;
    }
    
    /**
     * Incrémente la position en Y du joueur.
     */
    private void pushY(ICoord p) {
        resY += Y_MOVEMENT;
    }
    
    /**
     * Décrémente la position en X du joueur.
     */
    private void pushX2(ICoord x) {
        resX -= X_MOVEMENT;
    }
    
    /**
     * Décrémente la position en Y du joueur.
     */
    private void pushY2(ICoord p) {
        resY -= Y_MOVEMENT;
    }
    
}
