package dp.angryballs;

import java.util.List;

import dp.angryballs.controleurs.*;
import dp.angryballs.modele.Bille;
import dp.angryballs.vues.*;

/**
 * responsable de l'animation des billes, c-à-d responsable du mouvement de la liste des billes. met perpétuellement à jour les billes. 
 * gère le délai entre 2 mises à jour (deltaT) et prévient la vue responsable du dessin des billes qu'il faut mettre à jour la scène
 * 
 * ICI : IL N'Y A RIEN A CHANGER
 * */
public class AnimationBilles implements Runnable, ObserverBouton {
    List<Bille> billes;   // la liste de toutes les billes en mouvement
    VueBillard vueBillard;    // la vue responsable du dessin des billes
    private Thread thread;    // pour lancer et arrêter les billes
    private double deltaT;

    private static final double COEFF = 0.5;

    /**
     * @param billes
     * @param vueBillard
     */
    public AnimationBilles(List<Bille> billes, VueBillard vueBillard) {
        this.billes = billes;
        this.vueBillard = vueBillard;
        deltaT = 10;

        vueBillard.addObserver(this);
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {                         // gestion du mouvement
                for(Bille bille : billes) {
                    bille.deplacer(deltaT);                 // mise à jour position et vitesse de cette bille
                    bille.gestionAcceleration(billes);      // calcul de l'accélération subie par cette bille
                    bille.gestionCollision(billes);
                    bille.collisionContour( 0, 0, vueBillard.largeurBillard(), vueBillard.hauteurBillard());
                }

                vueBillard.miseAJour();                                // on prévient la vue qu'il faut redessiner les billes
                Thread.sleep((int) deltaT);                          // deltaT peut être considéré comme le délai entre 2 flashes d'un stroboscope qui éclairerait la scène
            }
        }
        catch (InterruptedException e) {
            /* arrêt normal, il n'y a rien à faire dans ce cas */
        }
    }

    /**
     * calcule le maximum de de la norme carrée (pour faire moins de calcul) des vecteurs vitesse de la liste de billes
     *
     * */
    static double maxVitessesCarrées(List<Bille> billes)
    {
        double vitesse2Max = 0;

        int i;
        double vitesse2Courante;

        for (i = 0; i < billes.size(); ++i) {
            if ((vitesse2Courante = billes.get(i).getVitesse().normeCarrée()) > vitesse2Max) {
                vitesse2Max = vitesse2Courante;
            }
        }

        return vitesse2Max;
    }

    /**
     * calcule le minimum  des rayons de a liste des billes
     *
     *
     * */
    static double minRayons(List<Bille> billes) {
        double rayonMin, rayonCourant;
        rayonMin = Double.MAX_VALUE;

        int i;
        for (i = 0; i < billes.size(); i++) {
            if ((rayonCourant = billes.get(i).getRayon()) < rayonMin) {
                rayonMin = rayonCourant;
            }
        }

        return rayonMin;
    }


    public void lancerAnimation() {
        if (this.thread == null) {
            this.thread = new Thread(this);
            thread.start();
        }
    }

    public void arrêterAnimation() {
        if (thread != null) {
            this.thread.interrupt();
            this.thread = null;
        }
    }

    public void ajouterBille(Bille bille) {
        billes.add(bille);
    }

    @Override
    public void buttonPressed(ObservableBouton observable, Object arg) {
        if(observable instanceof BoutonLancer)
            lancerAnimation();
        else if(observable instanceof BoutonArreter)
            arrêterAnimation();
        else if(observable instanceof BoutonCreer && arg instanceof Bille)
            ajouterBille((Bille) arg);
    }
}
