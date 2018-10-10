package dp.angryballs.vues;

import java.awt.*;
import java.util.Vector;

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
    TextField presentation;
    Billard billard;
    public Button lancerBilles, arrêterBilles; //TODO "AAAAAHHHHH"
    Panel bas;
    public PanneauAjoutBilles droite;

    EcouteurTerminaison ecouteurTerminaison;

    public CadreAngryBalls(String titre, String message, Vector<Bille> billes) throws HeadlessException {
        super(titre);
        Outils.place(this, 0.33, 0.33, 0.5, 0.5);
        this.ecouteurTerminaison = new EcouteurTerminaison(this);

        this.bas = new Panel();
        this.bas.setBackground(Color.LIGHT_GRAY);
        this.add(this.bas,BorderLayout.SOUTH);

        droite = new PanneauAjoutBilles();
        droite.setPreferredSize(new Dimension(300, getHeight()));
        this.add(this.droite, BorderLayout.EAST);


        this.billard = new Billard(billes);
        this.add(this.billard);

        this.lancerBilles = new Button("lancer les billes");
        this.bas.add(this.lancerBilles);

        this.arrêterBilles = new Button("arrêter les billes");
        this.bas.add(this.arrêterBilles);
    }

    public double largeurBillard() {
        return this.billard.getWidth();
    }

    public double hauteurBillard() {
        return this.billard.getHeight();
    }

    @Override
    public void miseAJour() {
        this.billard.repaint();
    }

    /* (non-Javadoc)
     * @see exodecorateur.vues.VueBillard#montrer()
     */
    @Override
    public void montrer() {
        this.setVisible(true);
    }
}