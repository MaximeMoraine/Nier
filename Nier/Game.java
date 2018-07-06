package Nier;

import Nier.Graphique.Graphique;
import Nier.Objet.*;
import Nier.Deplacement.*;
import java.util.Timer;
import java.util.TimerTask;

import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Permet de lancer le jeu !
 *
 * @author Maxime Moraine
 */
public class Game {
    
    // CONSTANTE
    
        public static final int FPS = 30;// Regrouper dans un package "utils" clase constante
        public static final int MAX_CMPT_GENERATOR = 10;
        public static final int MAX_CMPT_PLAYER = 5;
        
        private int cmptGenerator = 0;
        private int cmptPlayer;
        private Set tableDesRotateGenerator = new HashSet();
        
        public static final Graphique FENETRE = new Graphique();
        
        private Player p1 = new Player(new Coord(100, 100),
                                       new PlayerMovement());;
                                       
        private final int SECONDE = 1000;
        
        
    // REQUETE
    
    public static Graphique getFenetre() {
        return FENETRE;
    }
    
    // MAIN
    
    /**
     * Initialise la partie puis la lance.
     */
    public void game() {

       FENETRE.setTitle("Nier Protomata");
       FENETRE.setSize(ICoord.LAST_COL + ICoord.ADD_SIZE,
                       ICoord.LAST_ROW + ICoord.ADD_SIZE);
       
       Timer timer = new Timer();
       TimerTask tache = new TimerTask() {
           @Override
                public void run() {
                    launchGame(FENETRE);
                }
            };
               
       // Initialisation des participants de la partie.
        IMovement m = new MvtLinear(0, 0);
        ICoord p = new Coord(500, 500);
        RotateGenerator pr = new RotateGenerator(p, m, 3);
        
        tableDesRotateGenerator.add(pr);
       
       long temps = SECONDE / FPS; //FPS image par seconde
       timer.schedule(tache, 0, temps);
    }
    
    // OUTILS
    
    /**
     * Contient le script du déroulement de la partie.
     */
    private void launchGame(Graphique fenetre) {
        
       // Gestion des tirs de projectile des ennemis
        if (cmptGenerator >= MAX_CMPT_GENERATOR) {
            cmptGenerator = 0;
            
            //Gestion des RotateGenerators
            Iterator i = tableDesRotateGenerator.iterator();
            while (i.hasNext()) {
                RotateGenerator that = (RotateGenerator) i.next();
                that.generate();
            }
        } else {
            ++cmptGenerator;
        }
      
        Iterator i;
        
       // Gestion des projectiles
            Set projectiles = IProjectile.allProjectiles();
            Set iHaveToKill = new HashSet();
            
            i = projectiles.iterator();
            while (i.hasNext()) {
                Projectile pp = (Projectile) i.next();
                pp.push();
                if (pp.getLife() == 0) {
                    iHaveToKill.add(pp);
                }
            }
            
            i = iHaveToKill.iterator();
            
            while (i.hasNext()) {
                Projectile pp = (Projectile) i.next();
                pp.kill();
            }
        
       // Gestion des déplacement ennemi
        
        i = tableDesRotateGenerator.iterator();
        
        while (i.hasNext()) {
            Generator that = (Generator) i.next();
            that.push();
        }
       
       // Gestion des déplacements du joueurs.
       
        Iterator pi = Nier.Objet.Player.getAllPlayer().iterator();
        
        while (pi.hasNext()) {
        
            Player p = (Player) pi.next();
            
            Set touche = FENETRE.getPan().getKeyboardEvent().getTouchePressed();
            touche = (Set) ((HashSet) touche).clone();
            
            i = touche.iterator();
            
            if (touche.size() > 0) {
                if (touche.size() == 1) {
                    switch ((char) i.next()) {
                        case ('z'):
                            p.push(true, false, false, false);
                            break;
                        case ('q'):
                            p.push(false, true, false, false);
                            break;
                        case ('s'):
                            p.push(false, false, true, false);
                            break;
                        default :
                            p.push(false, false, false, true);
                            break;
                    }
                        
                }
                else {
                    char key1 = (char) i.next();
                    char key2 = (char) i.next();
                    
                    switch (key1 + key2) {
                        case ('z' + 'd'):
                            p.push(true, false, false, true);
                            break;
                        case ('z' + 'q'):
                            p.push(true, true, false, false);
                            break;
                        case ('s' + 'd'):
                            p.push(false, false, true, true);
                            break;
                        default: // 'q' + 's'
                            p.push(false, true, true, false);
                            break;
                        }
                }
            }
            
            // Gestion de la souris du joueur
            
            if (FENETRE.getPan().getMouseEvent().mousePressed()) {
                if (cmptPlayer >= MAX_CMPT_PLAYER) {
                    cmptPlayer = 0;
                    p.setNewPlayerMousePosition(FENETRE.getPan().getMouseEvent().getMouseCoord());
                    p.fire();
                } else {
                    ++cmptPlayer;
                }
                
                if (p.getLife() <= 0) {
                    p.kill();
                }
            }
        }
        
       //Gestion de l'affichage
        FENETRE.print();
    }
         
}
