package dp.angryballs.vues;

import java.awt.*;
import java.util.Vector;

import dp.angryballs.modele.Bille;


/**
 * responsable du dessin des billes
 * 
 *  ICI : IL N'Y A RIEN A CHANGER 
 *  
 * 
 * */
public class Billard extends Canvas {
    Vector<Bille> billes;

    public Billard(Vector<Bille> billes) {
        this.billes = billes;
    }

    /* (non-Javadoc)
     * @see java.awt.Canvas#paint(java.awt.Graphics)
     */
    @Override
    public void paint(Graphics graphics) {
        graphics.clearRect(0,0, getWidth(), getHeight());
        int i;

        for (i = 0; i < this.billes.size(); ++i) {
            this.billes.get(i).dessine(graphics);
        }
    }
}
