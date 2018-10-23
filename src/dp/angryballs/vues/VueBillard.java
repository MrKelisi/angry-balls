package dp.angryballs.vues;


import java.util.Observer;

/**
 * contrat respecté par toute vue capable de dessiner la liste des billes
 * 
 * Comme ça si vous n'aimez pas mes composants awt vous pouvez les changer sans changer le reste de l'appli
 * 
 *  ICI : IL N'Y A RIEN A CHANGER 
 *  
 * 
 * */
public interface VueBillard
{
    public double largeurBillard();

    public double hauteurBillard();

    public void miseAJour();

    public void montrer();

    public void addObserver(Observer o);
}
