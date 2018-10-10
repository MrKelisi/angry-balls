package dp.angryballs.modele.comportements;

import dp.angryballs.modele.Bille;
import dp.angryballs.modele.DecorateurBille;
import mesmaths.geometrie.base.Vecteur;

import java.util.Random;

public class Colson extends DecorateurBille {

    public Colson(Bille billeDecoree) {
        super(billeDecoree);
    }

    @Override
    public void deplacer(double deltaT) {
        super.deplacer(deltaT);
        Random r = new Random();
        getPosition().ajoute(new Vecteur(r.nextDouble() * 4 - 2, r.nextDouble() * 4 - 2));
    }
}
