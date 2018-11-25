package dp.angryballs.modele;

import mesmaths.geometrie.base.Vecteur;

import java.awt.Color; //TODO: utiliser une classe couleur ne venant pas d'AWT
import java.util.List;

public interface Bille {
    /**
     * @return Identifiant de la bille
     */
    int getClef();

    /**
     * @return Masse de la bille
     */
    double masse();

    /**
     * @return Rayon de la bille
     */
    double getRayon();

    /**
     * @return Position de la bille
     */
    Vecteur getPosition();

    /**
     * @return Vitesse de la bille
     */
    Vecteur getVitesse();

    /**
     * @return Accélération de la bille
     */
    Vecteur getAcceleration();

    /**
     * @return Couleur de remplissage de la bille
     */
    Color getColor();

    /**
     * @return Couleur du contour de la bille
     */
    Color getOutline();

    /**
     * Change la taille de la bille
     * @param rayon Nouveau rayon
     */
    void setRayon(double rayon);

    /**
     * Déplace la bille
     * @param position Nouvelle position
     */
    void setPosition(Vecteur position);

    /**
     * Change la vitesse
     * @param vitesse Nouvelle vitesse
     */
    void setVitesse(Vecteur vitesse);

    /**
     * Change la couleur de contour
     * @param color Nouvelle couleur
     */
    void setOutline(Color color);

    void visite(VisiteurForme v);

    /**
     * Effectue le déplacement de la bille
     * @param deltaT Temps écoulé depuis la dernière mise à jour
     */
    void deplacer(double deltaT);

    /**
     * Gère l'accélération de la bille
     * @param billes Liste des billes du jeu
     */
    void gestionAcceleration(List<Bille> billes);

    /**
     * Gère les collisions de la bille
     * @param billes Liste des billes du jeu
     */
    void gestionCollision(List<Bille> billes);

    /**
     * Gère la collision avec les bords du plateau
     * @param abscisseCoinHautGauche Position de l'origine
     * @param ordonneeCoinHautGauche Position de l'origine
     * @param largeur Largeur du plateau
     * @param hauteur Hauteur du plateau
     */
    void collisionContour(double abscisseCoinHautGauche, double ordonneeCoinHautGauche, double largeur, double hauteur);

    /**
     * Méthode appelée lorsque la bille est attrapée
     * @param observableMouvement Observable du mouvement de la souris
     */
    void prendre(ObservableMouvement observableMouvement);

    /**
     * Appelé lorsque la bille est relachée
     */
    void relacher();

    String toString();
}
