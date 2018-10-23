package dp.angryballs.vues.controles;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoutonControleArreter extends BoutonControle {

    public BoutonControleArreter(String nom) {
        super(nom);

        bouton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setChanged();
                notifyObservers();
            }
        });
    }
}
