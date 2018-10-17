package dp.angryballs;

import java.awt.Color;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

import dp.angryballs.modele.BilleNue;
import dp.angryballs.modele.Forme;
import dp.angryballs.modele.comportements.*;
import dp.angryballs.vues.CadreAngryBalls;
import mesmaths.geometrie.base.Vecteur;

/**
 * Gestion d'une liste de billes en mouvement ayant toutes un comportement différent
 * 
 * Idéal pour mettre en place le DP decorator
 * */
public class TestAngryBalls {
    public static void main(String[] args) {
        //------------------- création de la liste (pour l'instant vide) des billes -----------------------

        List<Forme> billes = new CopyOnWriteArrayList<>();

        //---------------- création de la vue responsable du dessin des billes -------------------------

        CadreAngryBalls cadre = new CadreAngryBalls("Angry balls",
                                                "Animation de billes ayant des comportements différents. Situation idéale pour mettre en place le DP Decorator",
                                                billes);

        cadre.montrer(); // on rend visible la vue

        //------------- remplissage de la liste avec 4 billes -------------------------------



        double xMax, yMax;
        double vMax = 0.1;
        xMax = cadre.largeurBillard();      // abscisse maximal
        yMax = cadre.hauteurBillard();      // ordonnée maximale

        double rayon = 0.05*Math.min(xMax, yMax); // rayon des billes : ici toutes les billes ont le même rayon, mais ce n'est pas obligatoire

        Vecteur p0, p1, p2, p3, p4,  v0, v1, v2, v3, v4;    // les positions des centres des billes et les vecteurs vitesse au démarrage.
                                                            // Elles vont être choisies aléatoirement

        //------------------- création des vecteurs position des billes ---------------------------------

        p0 = Vecteur.créationAléatoire(0, 0, xMax, yMax);
        p1 = Vecteur.créationAléatoire(0, 0, xMax, yMax);
        p2 = Vecteur.créationAléatoire(0, 0, xMax, yMax);
        p3 = Vecteur.créationAléatoire(0, 0, xMax, yMax);
        p4 = Vecteur.créationAléatoire(0, 0, xMax, yMax);

        //------------------- création des vecteurs vitesse des billes ---------------------------------

        v0 = Vecteur.créationAléatoire(-vMax, -vMax, vMax, vMax);
        v1 = Vecteur.créationAléatoire(-vMax, -vMax, vMax, 0);
        v2 = Vecteur.créationAléatoire(-vMax, -vMax, vMax, vMax);
        v3 = Vecteur.créationAléatoire(-vMax, -vMax, vMax, vMax);
        v4 = Vecteur.créationAléatoire(-vMax, -vMax, vMax, vMax);

        //--------------- ici commence la partie à changer ---------------------------------

        /*
        billes.add(new BilleMvtRURebond(p0, rayon, v0, Color.red));
        billes.add(new BilleMvtPesanteurFrottementRebond(p1, rayon, v1, new Vecteur(0,0.001), Color.yellow));
        billes.add(new BilleMvtNewtonFrottementRebond(p2, rayon, v2, Color.green));
        billes.add(new BilleMvtRUPasseMurailles(p3, rayon, v3, Color.cyan));
        billes.add(new BilleMvtNewtonArret(p4, rayon, v4,  Color.black));
        */

        billes.add(new Rebond(new RectiligneUniforme(new BilleNue(p0, rayon * 3, new Vecteur(0.01,0.01), Color.red))));


        //---------------------- ici finit la partie à changer -------------------------------------------------------------


        System.out.println("billes = " + billes);


        //-------------------- création de l'objet responsable de l'animation (c'est un thread séparé) -----------------------

        AnimationBilles animationBilles = new AnimationBilles(billes, cadre);

        //----------------------- mise en place des écouteurs de boutons qui permettent de contrôler (un peu...) l'application -----------------

        EcouteurBoutonLancer écouteurBoutonLancer = new EcouteurBoutonLancer(animationBilles);
        EcouteurBoutonArreter écouteurBoutonArrêter = new EcouteurBoutonArreter(animationBilles);
        EcouteurCreationBille ecouteurCreationBille = new EcouteurCreationBille(animationBilles, cadre.droite.getDecorators(), rayon);

        //------------------------- activation des écouteurs des boutons et ça tourne tout seul ------------------------------


        cadre.lancerBilles.addActionListener(écouteurBoutonLancer);             //TODO maladroit : à changer
        cadre.arrêterBilles.addActionListener(écouteurBoutonArrêter);           //TODO maladroit : à changer
        cadre.droite.getCreateButton().addActionListener(ecouteurCreationBille);
    }
}
