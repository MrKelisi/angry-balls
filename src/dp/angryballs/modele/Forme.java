package dp.angryballs.modele;

import mesmaths.geometrie.base.Vecteur;

import java.awt.*;
import java.util.Vector;

public interface Forme {
    int getClef();
    Vecteur getPosition();
    Vecteur getVitesse();
    Vecteur getAcceleration();
    void setVitesse(Vecteur vitesse);
    double masse();
    void deplacer(double deltaT);
    Color getColor();
    void visite(VisiteurForme v);
    void collisionContour(double abscisseCoinHautGauche, double ordonnéeCoinHautGauche, double largeur, double hauteur);
    void gestionAcceleration(Vector<Forme> billes);
    void gestionCollision(Vector<Forme> billes);
    void prendre(ObservableMouvement observableMouvement);
    void relacher();

    /**
     * Change la postition de la forme
     * @param position Position
     */
    void setPosition(Vecteur position);
}
