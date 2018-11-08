package dp.angryballs.vues;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.List;

import dp.angryballs.controleurs.*;
import dp.angryballs.modele.Bille;
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
    private Panel bas;
    private Bouton boutonLancer, boutonArreter;
    public PanneauAjoutBilles droite;

    EcouteurTerminaison ecouteurTerminaison;

    public CadreAngryBalls(String titre, List<Bille> billes) throws HeadlessException {
        super(titre);
        Outils.place(this, 0.33, 0.33, 0.5, 0.5);
        this.ecouteurTerminaison = new EcouteurTerminaison(this);

        this.bas = new Panel();
        this.bas.setBackground(Color.LIGHT_GRAY);
        this.add(this.bas,BorderLayout.SOUTH);

        this.billard = new Billard(billes);
        this.billard.setIgnoreRepaint(true);

        this.add(this.billard);

        droite = new PanneauAjoutBilles(this.billard);
        droite.setPreferredSize(new Dimension(280, getHeight()));
        this.add(this.droite, BorderLayout.EAST);


        boutonLancer = new BoutonLancer("Lancer les billes");
        bas.add(boutonLancer);

        boutonArreter = new BoutonArreter("Arrêter les billes");
        bas.add(boutonArreter);

        MouseAdapterBillePilotee handler = new MouseAdapterBillePilotee(billard.billes, this);
        billard.addMouseListener(handler);
        billard.addMouseMotionListener(handler);
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
        billard.paint(g);
        bufferStrategy.show();
        g.dispose();
    }

    @Override
    public void montrer() {
        this.setVisible(true);
        this.billard.createBufferStrategy(2);
    }

    @Override
    public void addObserver(ObserverBouton observeur) {
        boutonLancer.ajoutObserveur(observeur);
        boutonArreter.ajoutObserveur(observeur);
        droite.getBoutonCreer().ajoutObserveur(observeur);
    }

}