package dp.angryballs.vues;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.List;

import dp.angryballs.AnimationBilles;
import dp.angryballs.modele.Bille;
import dp.angryballs.vues.boutons.*;
import outilsvues.EcouteurTerminaison;

import outilsvues.Outils;

/**
 * Vue dessinant les billes et contenant les 3 boutons de contrôle (arrêt du programme, lancer les billes, arréter les billes) 
 * 
 *  ICI : IL N'Y A RIEN A CHANGER 
 *  
 * 
 * */
public class CadreAngryBalls extends Frame implements VueBillard {
    private Billard billard;
    private AnimationBilles animationBilles;

    public CadreAngryBalls(String titre, List<Bille> billes) throws HeadlessException {
        super(titre);
        Outils.place(this, 0.33, 0.33, 0.5, 0.5);
        new EcouteurTerminaison(this);

        Panel bas = new Panel();
        bas.setBackground(Color.LIGHT_GRAY);
        this.add(bas, BorderLayout.SOUTH);

        this.billard = new Billard(billes);
        this.billard.setIgnoreRepaint(true);

        this.add(this.billard);

        PanneauAjoutBilles droite = new PanneauAjoutBilles(this.billard);
        droite.setPreferredSize(new Dimension(280, getHeight()));
        this.add(droite, BorderLayout.EAST);


        Bouton boutonLancer = new BoutonLancer("Lancer les billes");
        bas.add(boutonLancer);

        Bouton boutonArreter = new BoutonArreter("Arrêter les billes");
        bas.add(boutonArreter);

        MouseAdapterBillePilotee handler = new MouseAdapterBillePilotee(billard.billes, this);
        billard.addMouseListener(handler);
        billard.addMouseMotionListener(handler);

        animationBilles = new AnimationBilles(billes, this);

        boutonLancer.ajoutObserveur((ObservableBouton observable, Object arg) -> animationBilles.lancerAnimation());

        boutonArreter.ajoutObserveur((ObservableBouton observable, Object arg) -> animationBilles.arrêterAnimation());

        droite.getBoutonCreer().ajoutObserveur((ObservableBouton observable, Object arg) -> {
            if(!(arg instanceof Bille)) {
                return;
            }
            billes.add((Bille) arg);
        });
    }

    public double largeurBillard() {
        return this.billard.getWidth();
    }

    public double hauteurBillard() {
        return this.billard.getHeight();
    }

    @Override
    public void miseAJour() {
        BufferStrategy bufferStrategy = billard.getBufferStrategy();
        Graphics g = bufferStrategy.getDrawGraphics();
        billard.render(g);
        g.dispose();
        bufferStrategy.show();
    }

    @Override
    public void montrer() {
        this.setVisible(true);

        this.billard.createBufferStrategy(2);
        this.billard.setIgnoreRepaint(true);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        miseAJour();
    }
}