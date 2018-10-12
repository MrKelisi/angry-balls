package dp.angryballs.modele.comportements;

import dp.angryballs.modele.Bille;
import dp.angryballs.modele.DecorateurBille;
import mesmaths.geometrie.base.Vecteur;

import java.util.Random;

public class Alcoolisme extends DecorateurBille {
    private double tempsAvantPause;
    private double tempsPause;

    public Alcoolisme(Bille billeDecoree) {
        super(billeDecoree);
    }

    @Override
    public void deplacer(double deltaT) {
        Random r = new Random();
        Vecteur tremblement;

        if(tempsAvantPause > 0) {
            super.deplacer(deltaT);
            tremblement = new Vecteur(r.nextDouble() * 4 - 2, r.nextDouble() * 4 - 2);
            tempsAvantPause -= deltaT;
        }
        else if(tempsPause > 0) { //Bon, on va faire la pause
            tempsPause -= deltaT;
            tremblement = new Vecteur(0,0);
        }
        else {
            tempsAvantPause = r.nextDouble() * 30000 + 30000;
            tempsPause = r.nextDouble() * 10000 + 5000;
            tremblement = new Vecteur(r.nextDouble() * 2 - 1, r.nextDouble() * 2 - 1);
        }

        getPosition().ajoute(tremblement);
    }

    @Override
    public Vecteur getVitesse() {
        return super.getVitesse().produit(0.1);
    }


}
