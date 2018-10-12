package dp.angryballs.vues;

import dp.angryballs.modele.Bille;
import dp.angryballs.modele.Forme;
import mesmaths.geometrie.base.Vecteur;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class MouseAdapterBillePilotee extends MouseAdapter {

    private Vector<Forme> formes;
    private Forme formeAccrochee;

    public MouseAdapterBillePilotee(Vector<Forme> formes) {
        this.formes = formes;
        this.formeAccrochee = null;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mouseClicked(e);
        for(Forme f : formes) {
            double rayon = ((Bille) f).getRayon();
            if(f.getPosition().x - rayon < e.getPoint().x &&
                    f.getPosition().x + rayon > e.getPoint().x &&
                    f.getPosition().y - rayon < e.getPoint().y &&
                    f.getPosition().y + rayon > e.getPoint().y) {
                System.out.println(f.getClef());
                formeAccrochee = f;
                formeAccrochee.getAcceleration().set(Vecteur.VECTEURNUL);
                formeAccrochee.getVitesse().set(Vecteur.VECTEURNUL);
                break;
            }
        }
        //TODO: accrocher la bille
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        formeAccrochee = null;
        //TODO: donner une accélération à la bille
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        if(formeAccrochee != null)
            formeAccrochee.getPosition().set(new Vecteur(e.getX(), e.getY()));
        //TODO: déplacer la bille
    }

}
