package dp.angryballs.vues;

import dp.angryballs.modele.Bille;
import dp.angryballs.modele.Forme;
import dp.angryballs.modele.ObservableMouvement;
import dp.angryballs.modele.ObserveurMouvement;
import mesmaths.geometrie.base.Vecteur;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class MouseAdapterBillePilotee extends MouseAdapter implements ObservableMouvement{
    private static final Point POSITION_CURSEUR = new Point(100, 100);

    private List<Forme> formes;
    private Forme formeAccrochee;
    private ArrayList<ObserveurMouvement> observeurs;
    private Window window;
    private Cursor defaultCursor;
    private Cursor blankCursor;
    boolean ignoreMouseMove;
    private Point source;
    private Robot r;

    public MouseAdapterBillePilotee(List<Forme> formes, Window window) {
        this.formes = formes;
        this.formeAccrochee = null;
        observeurs = new ArrayList<>();
        this.window = window;

        defaultCursor = window.getCursor();
        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                cursorImg, new Point(0, 0), "blank cursor");

        ignoreMouseMove = false;
        source = null;

        try {
            r = new Robot();
        }
        catch (AWTException e) {
            e.printStackTrace();
        }
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
                window.setCursor(blankCursor);
                formeAccrochee = f;

                Color color = formeAccrochee.getColor();
                Color outline = new Color(255 - color.getRed(), 255 - color.getGreen(), 255 - color.getBlue());
                formeAccrochee.setOutline(outline);
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

        window.setCursor(defaultCursor);
        formeAccrochee.relacher();
        formeAccrochee.setOutline(formeAccrochee.getColor());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(ignoreMouseMove) {
            ignoreMouseMove = false;
        }
        else {
            super.mouseDragged(e);

            if (formeAccrochee != null && source != null) {
                Vecteur mouvement = new Vecteur(e.getX() - source.getX(), e.getY() - source.getY());

                for (ObserveurMouvement observeur : observeurs) {
                    observeur.onMove(mouvement);
                }
            }

            ignoreMouseMove = true;
            r.mouseMove(POSITION_CURSEUR.x, POSITION_CURSEUR.y);
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
