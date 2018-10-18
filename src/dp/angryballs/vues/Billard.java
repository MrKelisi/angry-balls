package dp.angryballs.vues;

import java.awt.*;
import java.util.List;

import dp.angryballs.modele.Bille;
import dp.angryballs.modele.Forme;
import dp.angryballs.modele.VisiteurForme;


/**
 * responsable du dessin des billes
 */
public class Billard extends Canvas implements VisiteurForme {
    List<Forme> formes;
    Graphics g;

    public Billard(List<Forme> formes) {
        this.formes = formes;
    }

    /* (non-Javadoc)
     * @see java.awt.Canvas#paint(java.awt.Graphics)
     */
    @Override
    public void paint(Graphics graphics) {
        g = graphics;
        graphics.clearRect(0,0, getWidth(), getHeight());
        int i;

        for(Forme forme : formes) {
            forme.visite(this);
        }
    }

    @Override
    public void visite(Bille bille) {
        int width, height;
        int xMin, yMin;

        xMin = (int)Math.round(bille.getPosition().x - bille.getRayon());
        yMin = (int)Math.round(bille.getPosition().y - bille.getRayon());

        width = height = 2 * (int) Math.round(bille.getRayon());

        Graphics2D g2 = null;
        Stroke s = null;
        if(g instanceof Graphics2D) {
            g2 = (Graphics2D) g;
            s = g2.getStroke();
        }

        g.setColor(bille.getColor());
        g.fillOval( xMin, yMin, width, height);
        g.setColor(bille.getOutline());

        if(g2 != null) {
            g2.setStroke(new BasicStroke(5));
        }
        g.drawOval(xMin, yMin, width, height);

        if(g2 != null) {
            g2.setStroke(s);
        }
    }
}
