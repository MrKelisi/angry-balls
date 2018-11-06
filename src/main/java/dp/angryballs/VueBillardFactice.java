package dp.angryballs;

import dp.angryballs.controleurs.ObserverBouton;
import dp.angryballs.vues.VueBillard;

public class VueBillardFactice implements VueBillard {

    @Override
    public double largeurBillard() {
    return 200;
    }

    @Override
    public double hauteurBillard() {
    return 100;
    }

    @Override
    public void miseAJour() {
    }

    @Override
    public void montrer() {
    }

    @Override
    public void addObserver(ObserverBouton o) {
    }
}
