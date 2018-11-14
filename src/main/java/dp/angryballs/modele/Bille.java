package dp.angryballs.modele;

import mesmaths.geometrie.base.Vecteur;

import java.awt.*;
import java.util.List;

public interface Bille {
    int getClef();
    double masse();
    double getRayon();
    Vecteur getPosition();
    Vecteur getVitesse();
    Vecteur getAcceleration();
    Color getColor();
    Color getOutline();

    void setRayon(double rayon);
    void setPosition(Vecteur position);
    void setVitesse(Vecteur vitesse);
    void setOutline(Color color);

    void visite(VisiteurForme v);
    void deplacer(double deltaT);

    void gestionAcceleration(List<Bille> billes);
    void gestionCollision(List<Bille> billes);
    void collisionContour(double abscisseCoinHautGauche, double ordonneeCoinHautGauche, double largeur, double hauteur);

    void prendre(ObservableMouvement observableMouvement);
    void relacher();

    String toString();
}
