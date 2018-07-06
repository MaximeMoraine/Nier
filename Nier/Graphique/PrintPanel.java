package Nier.Graphique;

import javax.swing.JPanel;
import java.awt.Graphics;
import Nier.Deplacement.ICoord;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import Nier.Objet.Obj;
import Nier.Objet.ProjectileRed;
import Nier.Objet.ProjectileMallow;

import Nier.Graphique.Controle.KeyboardEvent;
import Nier.Graphique.Controle.EventMouse;


/**
 * Hérite de la classe JPanel et affiche les objets envoyé.
 */
public class PrintPanel extends JPanel {
    // CONSTANTE
    
    public static final int CIRCLE = Obj.CIRCLE;
    public static final int GENERATOR = Obj.GENERATOR;
    public static final int PLAYER = Obj.PLAYER;
    public static final int PROJECT_PLAYER = Obj.PROJECT_PLAYER;
    
    // ATTRIBUT
    
    private static Set obj = new HashSet();
    private final KeyboardEvent ke;
    private final EventMouse e;
    
    private static Image redProject;
    private static Image mallowProject;
    private static Image generator;
    private static Image player;
    private static Image projectPlayer;
    
   
    // CONSTRUCTEUR
    
    public PrintPanel() {
        super();
        
        try {
            redProject = ImageIO.read(new File("Image/RedProject.gif"));
            mallowProject = ImageIO.read(new File("Image/MallowProject.gif"));
            generator = ImageIO.read(new File("Image/Generator.gif"));
            player = ImageIO.read(new File("Image/Player.gif"));
            projectPlayer = ImageIO.read(new File("Image/PlayerProject.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // EVENT
        
        ke = new KeyboardEvent();
        addKeyListener(ke);
        e = new EventMouse();
        addMouseMotionListener(e);
        addMouseListener(e);
          
    }
    
    // REQUÊTE
    
    /**
     * Renvoie la liste des objets sur lesquels on travaille.
     */
    public Set getObj() {
        return obj;
    }
    
    /**
     * Renvoie le KeyboardEvent associé à ce pan.
     */
    public KeyboardEvent getKeyboardEvent() {
        return ke;
    }
    
    public EventMouse getMouseEvent() {
        return e;
    }
    
    // METHODE
    
    /**
     * On ajoute o à la liste des objets.
     * 
     * @pre
     *      o != null
     * @post
     *      getObj() == (old getObj) + o
     */
    protected void addObj(Obj o) {
        if (o == null) {
            throw new AssertionError();
        }
        
        obj.add(o);
    }
    
    /**
     * On supprime o à la liste des objets.
     * 
     * @pre
     *      o != null
     * @post
     *      getObj() == (old getObj) - o
     */
    protected void removeObj(Obj o) {
        if (o == null) {
            throw new AssertionError();
        }
        
        obj.remove(o);
    }
    
    /**
     * affiche l'objet getObj().
     * 
     * @pre
     *      g != null
     * @post
     *      getObj() apparait sur la fenêtre.
     */
    protected void paintComponent(Graphics g) { 
        super.paintComponent(g);
        requestFocus();
        
        if (getObj().size() == 0) {
            return;
        }
        
        Iterator i = obj.iterator();
        
        while (i.hasNext()) {
            Obj curObj = (Obj) i.next();
            
            int form = curObj.getForm();
            ICoord p = curObj.getPosition();
            
            int x = p.getCol();
            int y = ICoord.LAST_ROW - p.getRow();

            switch (form) {
                case CIRCLE:               
                    if (curObj instanceof ProjectileRed) {
                        g.drawImage(redProject, x, y, this);
                        break;
                    }
                    if (curObj instanceof ProjectileMallow) {
                        g.drawImage(mallowProject, x, y, this);
                        break;
                    }
                    break;
                    
                case PROJECT_PLAYER:
                    g.drawImage(projectPlayer, x, y, this);
                    break;
                    
                case GENERATOR:
                    g.drawImage(generator, x, y, this);
                    break;
                
                //case PLAYER:
                default:
                    g.drawImage(player, x, y, this);
                    break;
                
            }
        }
    }
}
