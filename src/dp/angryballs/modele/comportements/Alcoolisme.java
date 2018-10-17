package dp.angryballs.modele.comportements;

import dp.angryballs.modele.Bille;
import dp.angryballs.modele.DecorateurBille;
import dp.angryballs.modele.Forme;
import mesmaths.geometrie.base.Vecteur;

import java.util.List;
import java.util.Random;

public class Alcoolisme extends DecorateurBille {
    private double tempsTremblement;

    public Alcoolisme(Bille billeDecoree) {
        super(billeDecoree);
        tempsTremblement = 0;
    }

    @Override
    public void deplacer(double deltaT) {
        super.deplacer(deltaT);
        tempsTremblement -= deltaT;
    }

    @Override
    public void gestionAcceleration(List<Forme> billes) {
        if(tempsTremblement <= 0) {
            super.gestionAcceleration(billes);
            Vecteur vitesse = getVitesse();
            double angle = Math.random() * 1.6 - 0.8;
            double cos = Math.cos(angle);
            double sin = Math.sin(angle);

            setVitesse(new Vecteur(vitesse.x * cos - vitesse.y * sin, vitesse.x * sin + vitesse.y * cos));
            tempsTremblement = Math.random() * 250;
        }
    }
}
