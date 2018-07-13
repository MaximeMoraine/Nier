package nier.objet;

import nier.deplacement.ICoord;



/**
 * Permet de récupérer les informations de base sur les instances.
 */
public abstract class Obj {
    
    // Constructeur
    
    public Obj() {
        nier.Game.FENETRE.addObj(this);
    }
    
    
    // Requêtes
    
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
    
    /**
     * Suppression des cadavres...
     */
    public abstract void kill();

}
