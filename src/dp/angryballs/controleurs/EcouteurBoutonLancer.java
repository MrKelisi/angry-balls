package dp.angryballs.controleurs;

import java.awt.event.ActionEvent;

public class EcouteurBoutonLancer extends EcouteurBouton {

    @Override
    public void actionPerformed(ActionEvent arg0) {

        for(ObserverBouton observeur : observeurs) {
            observeur.buttonPressed(this, null);
        }
    }
}
