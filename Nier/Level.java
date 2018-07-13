package nier;

import nier.objet.Player;
import nier.deplacement.ICoord;
import nier.deplacement.Coord;
import nier.objet.RotateGenerator;
import nier.objet.FourGunGenerator;
import nier.objet.AggressiveGenerator;
import nier.deplacement.MvtLinear;
import nier.deplacement.PlayerMovement;
import nier.deplacement.IMovement;
import nier.deplacement.PolarMovement;
import nier.deplacement.MvtSine;
import nier.deplacement.PolarCoord;
import nier.musique.Sound;

/**
 * Classe lancant les niveaux de jeu.
 */
public abstract class Level {
    
    // Attributs
    
        private static int wave = 1;
        private static final Sound CITY_RUIN =
                new Sound("sound/CityRuin.wav");
        private static final Sound MEMORIES =
                new Sound("sound/MemoriesOfDust.wav");
        private static Player player;
                
    // Methode
    
    public static void setWave() {
        switch (wave) {
            case 1:
                level1();
                break;
            case 2:
                level2();
                break;
            case 3:
                level3();
                break;
            case 4:
                level4();
                break;
            case 5:
                level5();
                break;
            case 6:
                level6();
                break;
            case 7:
                level7();
                break;
            default:
                level8();
                break;
        }
        wave += 1;
    }
    
    /**
     * Remet le compteur de vague à 1.
     */
    public static void restart() {
        wave = 1;
    }
        
    
    // Outils   
        
    private static void level1() {
        player = new Player(new Coord(0, 0),
                        new PlayerMovement());
        MEMORIES.stop();
        CITY_RUIN.play();
        CITY_RUIN.loop();
        
        // Joueur
        ICoord pos = new Coord(ICoord.LAST_COL / 2,
                               (int) (ICoord.LAST_ROW * 0.10));

        player.setPosition(pos);
        
        
        // Ennemi
        pos = new Coord(ICoord.LAST_COL / 2, ICoord.LAST_ROW / 2);
        IMovement mov = new MvtLinear(0, 0);
        new RotateGenerator(pos, mov, 2, 6, 30);
        
    }
    
    private static void level2() {
        // Joueur
        ICoord pos = new Coord(ICoord.LAST_COL / 2,
                               (int) (ICoord.LAST_ROW * 0.10));
        player.setPosition(pos);
        
        
        // Ennemi
        pos = new Coord(ICoord.LAST_COL / 2, ICoord.LAST_ROW / 2);
        new AggressiveGenerator(new PolarCoord(800, 500, 0, 0),
                                new PolarMovement(6),
                                0, 7);
        
    }
    
    private static void level3() {
        // Joueur
        ICoord pos = new Coord(ICoord.LAST_COL / 2,
                               (int) (ICoord.LAST_ROW * 0.10));
        player.setPosition(pos);
        
        
        // Ennemi
        new AggressiveGenerator(new PolarCoord(ICoord.LAST_COL / 2,
                                               ICoord.LAST_ROW / 2,
                                               0, 0),
                                new PolarMovement(8), 1, 10);
        new AggressiveGenerator(new PolarCoord((int) (ICoord.LAST_COL * 0.95),
                                               ICoord.LAST_ROW / 2,
                                               -1, 0),
                                new PolarMovement(8), -1, 10);
        new AggressiveGenerator(new PolarCoord((int) (ICoord.LAST_COL * 0.05),
                                               ICoord.LAST_ROW / 2,
                                               0, 0),
                                new PolarMovement(8), 0, 10);       
    }
    
    private static void level4() { 
        // Joueur
        ICoord pos = new Coord(740, 340);
        IMovement mov;
        player.setPosition(pos);
        
        // Ennemi
        pos = new Coord(50, 500);
        mov = new MvtLinear(0, 0);
        new RotateGenerator(pos, mov, 2, 9, 30);
        pos = new Coord(1450, 500);
        mov = new MvtLinear(0, 0);
        new RotateGenerator(pos, mov, 2, 9, 30);
        PolarCoord p = new PolarCoord(50, 450, 0, 0);
        PolarMovement m = new PolarMovement(7);
        new AggressiveGenerator(p, m, 0, 9);
        p = new PolarCoord(1450, 450, 0, 0);
        m = new PolarMovement(7);
        new AggressiveGenerator(p, m, 0, 9);
    }
    
    private static void level5() {
        // Joueur
        ICoord pos = new Coord(ICoord.LAST_COL / 2, ICoord.LAST_ROW / 2);
        player.setPosition(pos);
        
        // Ennemi
        new RotateGenerator(new Coord((int) (ICoord.LAST_COL * 0.05),
                                            ICoord.LAST_ROW / 4),
                            new MvtSine(5, 6, 20), -1, 8, 10);
        new RotateGenerator(new Coord((int) (ICoord.LAST_COL * 0.95),
                                    ICoord.LAST_ROW * 3 / 4),
                    new MvtSine(-5, 6, 20), 0, 8, 10);
        new AggressiveGenerator(new PolarCoord((int) (ICoord.LAST_COL * 0.05),
                                                ICoord.LAST_ROW / 2, 0, 0),
                                new PolarMovement(8), 0, 10);
        new AggressiveGenerator(new PolarCoord((int) (ICoord.LAST_COL * 0.95),
                                                ICoord.LAST_ROW / 2, 0, 0),
                                new PolarMovement(8), -1, 10);
                           
   }
   
   private static void level6() {
       CITY_RUIN.stop();
       MEMORIES.loop();
       MEMORIES.play();
       
       player.setPosition(new Coord(ICoord.LAST_COL / 2,
                                    (int) (ICoord.LAST_ROW * 0.05)));
       new FourGunGenerator(new Coord(ICoord.LAST_COL / 2,
                                      ICoord.LAST_ROW / 2),
                            new MvtLinear(0, 0), 0, 8, 10);
    }
    
    private static void level7() {
      player.setPosition(new Coord(ICoord.LAST_COL / 2,
                                   ICoord.LAST_ROW / 2));
                                   
      new FourGunGenerator(new Coord((int) (ICoord.LAST_COL * 0.95),
                                     (int) (ICoord.LAST_ROW * 0.95)),
                           new MvtLinear(-1, -1), -1, 8, 10);
                           
      new FourGunGenerator(new Coord((int) (ICoord.LAST_COL * 0.05),
                                     (int) (ICoord.LAST_ROW * 0.05)),
                           new MvtLinear(1, 1), 0, 8, 10);
    }
    
    private static void level8() {
      player.setPosition(new Coord(ICoord.LAST_COL / 2,
                                   ICoord.LAST_ROW / 2));
                                   
      new FourGunGenerator(new Coord((int) (ICoord.LAST_COL * 0.95),
                                     (int) (ICoord.LAST_ROW * 0.95)),
                           new MvtLinear(-2, -2), -1, 8, 10);
                           
      new FourGunGenerator(new Coord((int) (ICoord.LAST_COL * 0.05),
                                     (int) (ICoord.LAST_ROW * 0.05)),
                           new MvtLinear(2, 2), 0, 8, 10);
                           
      new FourGunGenerator(new Coord((int) (ICoord.LAST_COL * 0.05),
                                     (int) (ICoord.LAST_ROW * 0.95)),
                           new MvtLinear(0, 0), 1, 8, 10);
                           
      new FourGunGenerator(new Coord((int) (ICoord.LAST_COL * 0.95),
                                     (int) (ICoord.LAST_ROW * 0.05)),
                           new MvtLinear(0, 0), 1, 8, 10);
    }
    
}
