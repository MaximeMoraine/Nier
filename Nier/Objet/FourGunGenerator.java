package nier.objet;

import nier.deplacement.PolarMovement;
import nier.deplacement.PolarCoord;
import nier.deplacement.ICoord;
import nier.deplacement.IMovement;

/**
 * Rotate generator possédant 4 canons
 */
public class FourGunGenerator extends RotateGenerator {

    // Constructeur
    
    public FourGunGenerator(ICoord pos, IMovement mov, int inter,
            int speedProject, int angle) {
        super(pos, mov, inter, speedProject, angle);
    }
    
    
    // Methode
    
    public void generate() {
        super.generate();
        
        int addDegree = nier.constante.Constante.DEGRE_90;
        
        if (getColor() == Generator.MALLOW) {
            new ProjectileRed(new PolarCoord(
                                getPosition().getCol() + getWeight() / 2,
                                getPosition().getRow() - getHeight() / 2,
                                getAngle() + addDegree, 0),
                                new PolarMovement(getSpeedProject()),
                                this);
            new ProjectileMallow(new PolarCoord(
                                getPosition().getCol() + getWeight() / 2,
                                getPosition().getRow() - getHeight() / 2,
                                getAngle() + addDegree * 2, 0),
                                new PolarMovement(getSpeedProject()),
                                this);
            new ProjectileRed(new PolarCoord(
                                getPosition().getCol() + getWeight() / 2,
                                getPosition().getRow() - getHeight() / 2,
                                getAngle() + addDegree * 3, 0),
                                new PolarMovement(getSpeedProject()),
                                this);
        } else {
            new ProjectileMallow(new PolarCoord(
                                getPosition().getCol() + getWeight() / 2,
                                getPosition().getRow() - getHeight() / 2,
                                getAngle() + addDegree, 0),
                                new PolarMovement(getSpeedProject()),
                                this);
            new ProjectileRed(new PolarCoord(
                                getPosition().getCol() + getWeight() / 2,
                                getPosition().getRow() - getHeight() / 2,
                                getAngle() + addDegree * 2, 0),
                                new PolarMovement(getSpeedProject()),
                                this);
            new ProjectileMallow(new PolarCoord(
                                getPosition().getCol() + getWeight() / 2,
                                getPosition().getRow() - getHeight() / 2,
                                getAngle() + addDegree * 3, 0),
                                new PolarMovement(getSpeedProject()),
                                this);
        }
    }
            
}
