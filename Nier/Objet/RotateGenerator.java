package Nier.Objet;

import Nier.Deplacement.*;

public class RotateGenerator extends Generator {
    
    // CONSTANTE
        
        public static final int DEGRE_0 = 0;
        public static final int DEGRE_45 = 45;
        public static final int DEGRE_90 = 90;
        public static final int DEGRE_135 = 135;
        public static final int DEGRE_180 = 180;
        public static final int DEGRE_225 = 225;
        public static final int DEGRE_270 = 270;
        public static final int DEGRE_360 = 360;
   
    // ATTRIBUT
        private double angle = 0;

    public RotateGenerator(ICoord pos, IMovement mov, int inter) {
        super(pos, mov, inter);
    }
    
    /**
     * Une fois le projectil généré, incrémente l'angle.
     */
    public void generate() {

        PolarCoord pos = new PolarCoord(getPosition().getCol() + getWeight() / 2,
                                        getPosition().getRow() - getHeight() / 2,
                                    angle, 0);
           
        IMovement mov = new ProjectMovement(4);
        
        if (color == Generator.RED) {
            new ProjectileRed(pos, mov, this);
        }
        else if (color == Generator.MALLOW) {
            new ProjectileMallow(pos, mov, this);
        }
        
        angle = (angle + DEGRE_45) % DEGRE_360;
        cmpt = (cmpt + 1) % getInterval();;
        if (cmpt == 0) {
            if (color == Generator.MALLOW) {
                color = Generator.RED;
            }
            else {
                color = Generator.MALLOW;
            }
        }
        
    }
}
