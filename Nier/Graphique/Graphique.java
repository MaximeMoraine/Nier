package Nier.Graphique;

import javax.swing.JFrame;
import java.awt.Graphics;
import Nier.Objet.Obj;

/**
 * Gère l'interface du jeu.
 * Permet d'afficher des objets dans une fenêtre
 *
 * @author Maxime Moraine
 */
public class Graphique {
    // ATTRIBUT
    
    private final JFrame fenetre;
    private final Graphics graph;
    private final PrintPanel pan;
    
    // CONSTRUCTEUR
    
    public Graphique() {
        fenetre = new JFrame();
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setResizable(false);
        
        pan = new PrintPanel();
                
        //On prévient notre JFrame que notre JPanel sera son content pane
        fenetre.setContentPane(pan);
        fenetre.setVisible(true);
        graph = pan.getGraphics();
    }
    
    // REQUETE
    
    public PrintPanel getPan() {
        return pan;
    }
    // METHODE
    
    /**
     * Change le titre de la fenêtre.
     * 
     * @pre 
     *       n != null
     * @post
     *       titre == n
     */
    public void setTitle(String n) {
        if (n == null) {
            throw new AssertionError();
        }
       
        fenetre.setTitle(n);
    }
    
    /**
     * Change la taille de la fenetre.
     * 
     * @pre
     *      x >= 0
     *      y >= 0
     * @post
     *      largeur = x (en px)
     *      hauteur = y (en px)
     */
    public void setSize(int x, int y) {
        if (x < 0 || y < 0) {
            throw new AssertionError();
        }

        fenetre.setSize(x, y);        
    }
    
    /**
     * affiche l'objet envoyé en paramètre à sa position courante
     * selon sa propre définition.
     * 
     * @pre
     *      obj != null
     */
    public void print() {
        
        pan.repaint();
    }
    
    /**
     * Ajoute l'objet obj à la liste des objets à afficher.
     * 
     *  @pre
     *      obj != null
     */
    public void addObj(Obj obj) {
        if (obj == null) {
            throw new AssertionError();
        }
        
        pan.addObj(obj); 
    }
    
    /**
     * Supprime l'objet obj à la liste des objets à afficher.
     * 
     *  @pre
     *      obj != null
     */
    public void removeObj(Obj obj) {
        if (obj == null) {
            throw new AssertionError();
        }
        
        pan.removeObj(obj); 
    }
}
