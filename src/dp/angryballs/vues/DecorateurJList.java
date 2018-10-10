package dp.angryballs.vues;

import dp.angryballs.modele.DecorateurBille;

import javax.swing.*;
import java.util.ArrayList;

public class DecorateurJList extends JList<String> implements ListeDecorateurSelectionnes {
    private ArrayList<Class<? extends DecorateurBille>> decorateurs;

    DecorateurJList() {
        super(new DefaultListModel<>());
        decorateurs = new ArrayList<>();
    }

    @Override
    public void ajoutDecorateur(Class<? extends DecorateurBille> decorateur) {
        ((DefaultListModel<String>) getModel()).addElement(decorateur.getSimpleName());
        decorateurs.add(decorateur);
    }

    @Override
    public ArrayList<Class<? extends DecorateurBille>> getDecorateurs() {
        return decorateurs;
    }
}
