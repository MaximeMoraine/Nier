package nier.graphique;

import javax.swing.JPanel;
import java.awt.Graphics;
import nier.deplacement.ICoord;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import nier.constante.Constante;
import nier.objet.Obj;
import nier.objet.ProjectileRed;
import nier.objet.ProjectileMallow;

import nier.graphique.controle.KeyboardEvent;
import nier.graphique.controle.EventMouse;

/**
 * Hérite de la classe JPanel et affiche les objets envoyé.
 */
public class PrintPanel extends JPanel {
    
    // ATTRIBUT
    
    private final List obj = new ArrayList();
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
            redProject = ImageIO.read(new File("image/RedProject.gif"));
            mallowProject = ImageIO.read(new File("image/MallowProject.gif"));
            generator = ImageIO.read(new File("image/Generator.gif"));
            player = ImageIO.read(new File("image/Player.gif"));
            projectPlayer = ImageIO.read(new File("image/PlayerProject.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // EVENT
        
        ke = new KeyboardEvent();
        addKeyListener(ke);
        e = new EventMouse();
        addMouseMotionListener(e);
          
    }
    
    
    // REQUÊTE
    
    /**
     * Renvoie la liste des objets sur lesquels on travaille.
     */
    public List getObj() {
        return obj;
    }
    
    /**
     * Renvoie le KeyboardEvent associé à ce pan.
     */
    public KeyboardEvent getKeyboardEvent() {
        return ke;
    }
    
    /**
     * Renvoie le EventMouse associé à ce pan.
     */
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
     * Supprime tous individu de la fenêtre.
     */
    public void removeAll() {
        Iterator i = obj.iterator();
        List iHaveToKill = new ArrayList();
        
        while (i.hasNext()) {
            Obj that = (Obj) i.next();
            iHaveToKill.add(that);
        }
        
        i = iHaveToKill.iterator();
        
        while (i.hasNext()) {
            Obj that = (Obj) i.next();
            that.kill();
        }
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
                case Constante.CIRCLE:               
                    if (curObj instanceof ProjectileRed) {
                        g.drawImage(redProject, x, y, this);
                        break;
                    }
                    if (curObj instanceof ProjectileMallow) {
                        g.drawImage(mallowProject, x, y, this);
                        break;
                    }
                    break;
                    
                case Constante.PROJECT_PLAYER:
                    g.drawImage(projectPlayer, x, y, this);
                    break;
                    
                case Constante.GENERATOR:
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
