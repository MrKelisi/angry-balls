package dp.angryballs.vues;

import dp.angryballs.modele.Bille;
import dp.angryballs.modele.Forme;
import mesmaths.geometrie.base.Vecteur;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.util.Vector;

public class MouseAdapterBillePilotee extends MouseAdapter {
    private Vector<Forme> formes;
    private Forme formeAccrochee;
    private Vecteur source;
    private long momentLancer;

    public MouseAdapterBillePilotee(Vector<Forme> formes) {
        this.formes = formes;
        this.formeAccrochee = null;
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

                formeAccrochee = f;
                source = formeAccrochee.getPosition().copie();
                momentLancer = System.currentTimeMillis();

                formeAccrochee.getAcceleration().set(Vecteur.VECTEURNUL);
                formeAccrochee.getVitesse().set(Vecteur.VECTEURNUL);
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

        float tempsLancer = System.currentTimeMillis() - momentLancer;
        Vecteur vitesse = formeAccrochee.getPosition().difference(source).produit(1.0 / tempsLancer);
        formeAccrochee.setVitesse(vitesse);

        formeAccrochee = null;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        if(formeAccrochee != null) {
            formeAccrochee.getPosition().set(new Vecteur(e.getX(), e.getY()));
        }
    }

}
