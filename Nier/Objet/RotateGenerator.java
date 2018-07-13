package nier.objet;

import nier.constante.Constante;
import nier.deplacement.IMovement;
import nier.deplacement.ICoord;
import nier.deplacement.PolarCoord;
import nier.deplacement.PolarMovement;
import java.util.ArrayList;
import java.util.List;

/**
 * Generateur neutre tirant autour de lui en incrémentant un certain angle.
 */ 
public class RotateGenerator extends Generator {
    
    // Constante
        
        public static final List ALL_ROTATE_GENERATOR = new ArrayList();
   
        
    // Attributs
    
        private double angle = 0;
        private int cmpt = 0; // Compteur pour generate()
        private int color = RED;
        private int incAngle;
        
    
    // Constructeur

    public RotateGenerator(ICoord pos, IMovement mov, int inter,
            int speedProject, int angle) {
        super(pos, mov, inter, speedProject);
        incAngle = angle;
        ALL_ROTATE_GENERATOR.add(this);
    }
    
    
    // Requete
    
    public static List getAllRotateGenerator() {
        return ALL_ROTATE_GENERATOR;
    }
    
    public int getColor() {
        return color;
    }
    
    public double getAngle() {
        return angle;
    }
    
    
    // Methode
    
    /**
     * Une fois le projectil généré, incrémente l'angle de tir.
     */
    public void generate() {

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
        
        angle = (angle + incAngle) % Constante.MAX_DEGREE;
    }
    
    public void kill() {
        super.kill();
        ALL_ROTATE_GENERATOR.remove(this);
    }
}
