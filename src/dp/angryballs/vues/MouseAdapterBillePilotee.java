package dp.angryballs.vues;

import dp.angryballs.modele.Bille;
import dp.angryballs.modele.Forme;
import dp.angryballs.modele.ObservableMouvement;
import dp.angryballs.modele.ObserveurMouvement;
import mesmaths.geometrie.base.Vecteur;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

public class MouseAdapterBillePilotee extends MouseAdapter implements ObservableMouvement{
    private Vector<Forme> formes;
    private Forme formeAccrochee;
    private Point source;
    private long momentLancer;
    private ArrayList<ObserveurMouvement> observeurs;

    public MouseAdapterBillePilotee(Vector<Forme> formes) {
        this.formes = formes;
        this.formeAccrochee = null;
        observeurs = new ArrayList<>();
        source = new Point(0,0);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mouseClicked(e);
        for(Forme f : formes) {
            double rayon = ((Bille) f).getRayon(); //TODO: c'est moche
            if(f.getPosition().x - rayon < e.getPoint().x &&
                    f.getPosition().x + rayon > e.getPoint().x &&
                    f.getPosition().y - rayon < e.getPoint().y &&
                    f.getPosition().y + rayon > e.getPoint().y) {

                f.prendre(this);

                formeAccrochee = f;
                break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);


        if(formeAccrochee == null) {
            return;
        }

        formeAccrochee.relacher();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);

        if(formeAccrochee != null) {
            Vecteur mouvement = new Vecteur(e.getX() - source.getX(), e.getY() - source.getY());

            for(ObserveurMouvement observeur : observeurs) {
                observeur.onMove(mouvement);
            }
        }

        source = e.getPoint();
    }

    @Override
    public void ajoutObserveur(ObserveurMouvement observeur) {
        observeurs.add(observeur);
    }

    @Override
    public void supprimerObserveur(ObserveurMouvement observeur) {
        observeurs.remove(observeur);
    }
}
