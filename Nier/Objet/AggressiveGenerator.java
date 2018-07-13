package nier.objet;

import nier.constante.Constante;
import nier.deplacement.IMovement;
import nier.deplacement.PolarCoord;
import nier.deplacement.PolarMovement;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;


/**
 * Generateur tirant uniquement vers le joueur et se rapproche de lui
 * en permanence.
 */
public class AggressiveGenerator extends Generator {
   
    // Constante
        
        public static final List ALL_AGGRESSIVE_GENERATOR = new ArrayList();
   
        
    // Attributs
    
        private int cmpt = 0; // Compteur pour generate()
        private int color = RED;
        
    
    // Constructeur
   
    public AggressiveGenerator(PolarCoord pos, PolarMovement mov, int inter,
            int speedProject) {
        super(pos, mov, inter, speedProject);
        ALL_AGGRESSIVE_GENERATOR.add(this);
    }
    
    
    // Requete
    
    public static List getAllAggressiveGenerator() {
        return ALL_AGGRESSIVE_GENERATOR;
    }
    
    
    // Methode
    
    /**
     * Tir un projectile en direction du joueur1.
     */
    public void generate() {
        Iterator i = Player.getAllPlayer().iterator();
        
        if (i.hasNext()) {
            Player p = (Player) i.next();
           
            // Recherche de l'angle
            // Mx = o.x + r * cos(O)
            // My = o.y + r * sin(O)
            int mx = p.getPosition().getCol();
            int my = p.getPosition().getRow();
            double r = Math.sqrt((mx - getPosition().getCol())
                    * (mx - getPosition().getCol())
                    + (my - getPosition().getRow()) 
                    * (my - getPosition().getRow()));
                    
            double angle = ((mx - getPosition().getCol()) / r);
            angle = Math.acos(angle);
            
            if (my < getPosition().getRow()) {
                angle = Constante.MAX_DEGREE - Math.toDegrees(angle);
            } else {
                angle = Math.toDegrees(angle);
            }
        
            
            PolarCoord pos = new PolarCoord(
                                    getPosition().getCol() + getWeight() / 2,
                                    getPosition().getRow() - getHeight() / 2,
                                    angle, 0);
               
            IMovement mov = new PolarMovement(getSpeedProject());
            
            // Un intervalle null signifie que des rouges
            // Un intervalle égal à -1 signifie que des bleues.
            if (getInterval() == RED) {
                new ProjectileRed(pos, mov, this);
            } else {
                if (getInterval() == MALLOW) {
                    new ProjectileMallow(pos, mov, this);
                } else {
                    if (color == Generator.RED) {
                        new ProjectileRed(pos, mov, this);
                    } else if (color == Generator.MALLOW) {
                        new ProjectileMallow(pos, mov, this);
                    }
                    
                    cmpt = (cmpt + 1) % getInterval();
                    if (cmpt == 0) {
                        if (color == Generator.MALLOW) {
                            color = Generator.RED;
                        } else {
                            color = Generator.MALLOW;
                        }
                    }
                    
                }
            }
        }
    }
    
    public void kill() {
        super.kill();
        ALL_AGGRESSIVE_GENERATOR.remove(this);
    }
    
    /**
     * étend push de maniére à mettre à jour le mouvement de this
     * en fonction de la position du joueur1.
     */
    public void push() {
        super.push();
        Iterator i = Player.getAllPlayer().iterator();
        
        if (i.hasNext()) {
            Player p = (Player) i.next();
           
            // Recherche de l'angle
            // Mx = o.x + r * cos(O)
            // My = o.y + r * sin(O)
            int mx = p.getPosition().getCol();
            int my = p.getPosition().getRow();
            double r = Math.sqrt((mx - getPosition().getCol())
                    * (mx - getPosition().getCol())
                    + (my - getPosition().getRow()) 
                    * (my - getPosition().getRow()));
                    
            double angle = ((mx - getPosition().getCol()) / r);
            angle = Math.acos(angle);
            
            if (my < getPosition().getRow()) {
                angle = Constante.MAX_DEGREE - Math.toDegrees(angle);
            } else {
                angle = Math.toDegrees(angle);
            }
        
            
            setPosition(new PolarCoord(getPosition().getCol(),
                                       getPosition().getRow(),
                                       angle, 0));
        }   
    }
        
}
