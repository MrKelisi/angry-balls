package dp.angryballs;

import java.awt.*;
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
    private long targetFPS;

    private static final double COEFF = 0.5;

    /**
     * @param billes
     * @param vueBillard
     */
    public AnimationBilles(List<Bille> billes, VueBillard vueBillard) {
        this.billes = billes;
        this.vueBillard = vueBillard;
        targetFPS = 60;

        vueBillard.addObserver(this);
    }

    @Override
    public void run() {
        try {
            long lastUpdateTime = System.currentTimeMillis();
            long updateTime;
            long sleepTime;

            long frameTotalTime = 1000/targetFPS;
            while (!Thread.interrupted()) {
                updateTime = System.currentTimeMillis();
                double deltaT = updateTime - lastUpdateTime;
                lastUpdateTime = updateTime;

                for(Bille bille : billes) {
                    bille.deplacer(deltaT);                 // mise à jour position et vitesse de cette bille
                    bille.gestionAcceleration(billes);      // calcul de l'accélération subie par cette bille
                    bille.gestionCollision(billes);
                    bille.collisionContour( 0, 0, vueBillard.largeurBillard(), vueBillard.hauteurBillard());
                }

                vueBillard.miseAJour();
                Toolkit.getDefaultToolkit().sync();

                sleepTime = updateTime + frameTotalTime - System.currentTimeMillis();
                if(sleepTime > 0) {
                    Thread.sleep((int) sleepTime);
                }
            }
        }
        catch (InterruptedException e) {
            /* arrêt normal, il n'y a rien à faire dans ce cas */
        }
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
