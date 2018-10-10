package dp.angryballs;

import java.util.Vector;

import dp.angryballs.modele.Bille;
import dp.angryballs.modele.Forme;
import dp.angryballs.modele.VisiteurForme;
import dp.angryballs.vues.VueBillard;

/**
 * responsable de l'animation des billes, c-à-d responsable du mouvement de la liste des billes. met perpétuellement à jour les billes. 
 * gère le délai entre 2 mises à jour (deltaT) et prévient la vue responsable du dessin des billes qu'il faut mettre à jour la scène
 * 
 * ICI : IL N'Y A RIEN A CHANGER
 * */
public class AnimationBilles implements Runnable, VisiteurForme {
    Vector<Forme> formes;   // la liste de toutes les billes en mouvement
    VueBillard vueBillard;    // la vue responsable du dessin des billes
    private Thread thread;    // pour lancer et arrêter les billes
    private double deltaT;

    private static final double COEFF = 0.5;

    /**
     * @param formes
     * @param vueBillard
     */
    public AnimationBilles(Vector<Forme> formes, VueBillard vueBillard) {
        this.formes = formes;
        this.vueBillard = vueBillard;
        deltaT = 10;
    }

    public void ajouterForme(Forme forme) {
        formes.add(forme);
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {                         // gestion du mouvement
                for(Forme forme : formes) {
                    forme.visite(this);
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
    static double maxVitessesCarrées(Vector<Bille> billes)
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
    static double minRayons(Vector<Bille> billes) {
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

    @Override
    public void visite(Bille bille) {
        bille.deplacer(deltaT);                 // mise à jour position et vitesse de cette bille
        bille.gestionAcceleration(formes);      // calcul de l'accélération subie par cette bille
        bille.gestionCollisionBille(formes);
        bille.collisionContour( 0, 0, vueBillard.largeurBillard(), vueBillard.hauteurBillard());

    }
}
