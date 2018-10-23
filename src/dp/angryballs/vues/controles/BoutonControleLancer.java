package dp.angryballs.vues.controles;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoutonControleLancer extends BoutonControle {

    public BoutonControleLancer(String nom) {
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
