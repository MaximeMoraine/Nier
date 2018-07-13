package nier;

import nier.constante.Constante;
import nier.graphique.Graphique;
import nier.objet.Generator;
import nier.objet.Player;
import nier.objet.Projectile;
import nier.objet.Actor;
import nier.objet.Obj;
import nier.objet.RotateGenerator;
import nier.objet.AggressiveGenerator;
import nier.deplacement.ICoord;
import java.util.Timer;
import java.util.TimerTask;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;

/**
 * Permet de lancer le jeu !
 *
 * @author Maxime Moraine
 */
public class Game {
    
    // Constantes
    
        public static final int MAX_CMPT_GENERATOR = 5;
        public static final int MAX_CMPT_PLAYER = 3;
        public static final Graphique
                FENETRE = new Graphique();                                    
        
        private static int cmptGenerator = 0;
        private static int cmptPlayer = 0;
        private static boolean firstTimePress = false;
        private static Timer timer = new Timer();
        private static TimerTask tache = new TimerTask() {
           @Override
                public void run() {
                    launchGame(FENETRE);
                }
            };
         
        
    // Main
    
    public static void main(String[] args) {
        startMenu();
    }
    
    
    // Menu
    
    
    public static void startMenu() {
       FENETRE.startMenu();
    }
    
    // Code
    
    /**
     * Initialise la partie puis la lance.
     */
    public static void game() {
       Level.setWave();
       long temps = Constante.SECONDE / Constante.FPS; //FPS image par seconde
       timer.schedule(tache, 0, temps);
    }
    
    /**
     * Stop la partie.
     */
    public static void stop() {
        timer = new Timer();
    }
    
    // Outils
    
    /**
     * Contient le script du déroulement de la partie.
     */
    private static void launchGame(Graphique fenetre) {
        Iterator i;
        List iHaveToKill = new ArrayList();
        
        // GESTION DES DEPLACEMENTS
       // Gestion des déplacement ennemi
        
        i = Generator.getAllGenerator().iterator();
        
        while (i.hasNext()) {
            Generator that = (Generator) i.next();
            if (!that.isAlive()) {
                iHaveToKill.add(that);
            } else {
                that.push();
            }
        }     

        
       // Gestion des déplacements du joueurs.
       
        // Si aucun joueur n'existe on propose de relancer une partie.
        if (nier.objet.Player.getAllPlayer().size() == 0) {
            FENETRE.setButton();
        }
        
        Iterator pi = nier.objet.Player.getAllPlayer().iterator();
        
        while (pi.hasNext()) {
           
            Player p = (Player) pi.next();
            
            if (!p.isAlive()) {
                iHaveToKill.add(p);
            } else {
            
                Set touche = FENETRE.getPan().
                        getKeyboardEvent().getTouchePressed();
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
                            
                    } else {
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
            }
        }
        
        
       // Gestion des deplacements des projectiles
        List projectiles = Projectile.getAllProjectiles();
            
        i = projectiles.iterator();
        while (i.hasNext()) {
            Projectile pp = (Projectile) i.next();
            if (!pp.isAlive()) {
                iHaveToKill.add(pp);
            } else {
                pp.push();
            }

        }
       
        // FIN DES DEPLACEMENT
        // Suppression des cadavres ...
        
        i = iHaveToKill.iterator();
        
        while (i.hasNext()) {
            Obj pp = (Obj) i.next();
            pp.kill();
        } 
        
        // DEBUT DES TIRES
        
       // Gestion des tirs de projectile des ennemis
        //GENERATOR
        if (cmptGenerator >= MAX_CMPT_GENERATOR) {
            cmptGenerator = 0;
            
            //Gestion des RotateGenerator
            i = RotateGenerator.getAllRotateGenerator().iterator();
            while (i.hasNext()) {
                RotateGenerator that = (RotateGenerator) i.next();
                that.generate();
            }
            
            //Gestion des AggressiveGenerator
             i = AggressiveGenerator.getAllAggressiveGenerator().iterator();
            while (i.hasNext()) {
                AggressiveGenerator that = (AggressiveGenerator) i.next();
                that.generate();
            }           
        } else {
            ++cmptGenerator;
        }
        
          
        // Gestion de la souris du joueur
        
        i = Player.getAllPlayer().iterator();
        
        while (i.hasNext()) {
            Player p = (Player) i.next();
            if (FENETRE.getPan().getKeyboardEvent().getShiftPressed()) {
                if (firstTimePress) {
                    cmptPlayer = MAX_CMPT_PLAYER;
                }
                if (cmptPlayer >= MAX_CMPT_PLAYER) {
                    cmptPlayer = 0;
                    p.setNewPlayerMousePosition(FENETRE.
                            getPan().getMouseEvent().getMouseCoord());
                    p.fire();
                    firstTimePress = false;
                } else {
                    ++cmptPlayer;
                }
            } else {
                firstTimePress = true;
            }    
        }
        
        //Si il ne reste plus que le joueur, lancement de la vague suivante.
        if (Actor.getAllActor().size() == Player.getAllPlayer().size()
            && Player.getAllPlayer().size() != 0) {
            // On supprime tout
            i = FENETRE.getPan().getObj().iterator();
            iHaveToKill = new ArrayList();
            while (i.hasNext()) {
                Obj that = (Obj) i.next();
                if (!(that instanceof Player)) {
                    iHaveToKill.add(that);
                }
            }
            i = iHaveToKill.iterator();
            while (i.hasNext()) {
                Obj that = (Obj) i.next();
                that.kill();
            }
            
            // On indique le numéro de la vague suivante
            
            // On lance la vague suivante
            
            //Gestion de l'affichage
            FENETRE.print();
            System.out.println("omedeto");
            System.out.println("Lancement de la vague suivante");
            for (int a = 3; a >= 1; --a) {
                try {
                    Thread.sleep(Constante.SECONDE);
                    System.out.println(a);
                } catch (InterruptedException e) {
                    //rien
                }
            }
            
            Level.setWave();
        }
        
        
       //Gestion de l'affichage
        FENETRE.print();
    }
         
}
