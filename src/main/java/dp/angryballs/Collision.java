package dp.angryballs;

import dp.angryballs.modele.Bille;
import dp.angryballs.modele.VisiteurForme;

import java.util.ArrayList;
import java.util.List;

public abstract class Collision implements VisiteurForme {
    private static ArrayList<CollisionObserver> observers = new ArrayList<>();
    protected Bille bille;

    public Collision(Bille bille) {
        if(bille == null) {
            throw new NullPointerException("Bille null");
        }

        this.bille = bille;
    }

    public void tester(List<Bille> billes) {
        for(Bille bille : billes) {
            if(this.bille.getClef() == bille.getClef()) {
                continue;
            }

            bille.visite(this);
        }
    }

    public static void addObserver(CollisionObserver observer) {
        observers.add(observer);
    }

    public static void removeObserver(CollisionObserver observer) {
        observers.remove(observer);
    }

    /**
     * Fonction à appeler lors d'ue collision entre deux billes
     * @param other Autre bille affectée
     */
    public void notify(Bille other) {
        for(CollisionObserver observer : observers) {
            observer.collides(bille, other);
        }
    }
}
