package dp.angryballs.modele;

import mesmaths.geometrie.base.Vecteur;

import java.awt.*;

public interface Forme {
    int getClef();
    Vecteur getPosition();
    Vecteur getVitesse();
    Vecteur getAcceleration();
    double masse();
    void deplacer(double deltaT);
    Color getColor();
    void visite(VisiteurForme v);
}
