package dp.angryballs.vues.controles;

import java.awt.*;
import java.util.Observable;

public abstract class BoutonControle extends Observable {

    protected Button bouton;

    public BoutonControle(String nom) {
        bouton = new Button(nom);
    }

    public Button getButton() {
        return bouton;
    }

}
