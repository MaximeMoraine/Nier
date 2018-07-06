package Nier.Objet;

import Nier.Deplacement.ICoord;



/**
 * Permet de récupérer des informations sur les instances pour l'interface
 * graphique.
 */
public abstract class Obj {
    
    // CONSTANTES
    
    public static final int CIRCLE = 0;
    public static final int GENERATOR = 1;
    public static final int ENEMY = 2;
    public static final int PLAYER = 3;
    public static final int PROJECT_PLAYER = 4;
    
    // CONSTRUCTEUR
    
    public Obj() {
        Nier.Game.getFenetre().addObj(this);
    }
    
    // REQUÊTES
    
    /**
     * Renvoie la forme de l'objet.
     */
    public abstract int getForm();
    
    /**
     * Renvoie l'objet contenant la position de this.
     */
    public abstract ICoord getPosition();
    
    /**
     * Renvoie la largeur de l'objet en pixel.
     */
    public abstract int getWeight();
    
    /**
     * Renvoie la hauteur de l'objet en pixel.
     */
    public abstract int getHeight();

}
