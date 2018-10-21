package dp.angryballs.modele;

import mesmaths.geometrie.base.Vecteur;

import java.awt.*;
import java.util.List;

public interface Bille {
    int getClef();
    Vecteur getPosition();
    Vecteur getVitesse();
    Vecteur getAcceleration();
    void setVitesse(Vecteur vitesse);
    double masse();
    void deplacer(double deltaT);
    Color getColor();
    Color getOutline();
    void setOutline(Color color);
    void visite(VisiteurForme v);
    void collisionContour(double abscisseCoinHautGauche, double ordonnéeCoinHautGauche, double largeur, double hauteur);
    void gestionAcceleration(List<Bille> billes);
    void gestionCollision(List<Bille> billes);
    void prendre(ObservableMouvement observableMouvement);
    void relacher();

    /**
     * Change la postition de la bille
     * @param position Position
     */
    void setPosition(Vecteur position);

    double getRayon();
    String toString();

    void setRayon(double rayon);
}
