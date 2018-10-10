package dp.angryballs.vues;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EcouteurBoutonAjouterDecorateur implements ActionListener {
    SelectionDecorateur source;
    ListeDecorateurSelectionnes target;

    public EcouteurBoutonAjouterDecorateur(SelectionDecorateur source, ListeDecorateurSelectionnes target) {
        if(source == null) {
            throw new NullPointerException("Source null");
        }

        if(target == null) {
            throw new NullPointerException("Target null");
        }

        this.source = source;
        this.target = target;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        target.ajoutDecorateur(source.getDecorateur());
    }
}
