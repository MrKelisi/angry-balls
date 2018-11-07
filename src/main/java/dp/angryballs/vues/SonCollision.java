package dp.angryballs.vues;

import dp.angryballs.CollisionObserver;
import dp.angryballs.modele.Bille;
import mesmaths.geometrie.base.Vecteur;

import javax.sound.sampled.*;
import java.io.File;

public class SonCollision implements CollisionObserver {
    private static File son;

    public SonCollision() {

        try {
            son = new File("res/bille.wav");
        } catch(Exception e) {
            System.err.println("Le fichier audio n'a pas pu être chargé");
        }
    }

    @Override
    public void collides(Bille b1, Bille b2) {
        try {

            AudioInputStream ais = AudioSystem.getAudioInputStream(son);
            Clip clip = AudioSystem.getClip();
            clip.open(ais);

            FloatControl master_gain = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            FloatControl balance = (FloatControl) clip.getControl(FloatControl.Type.BALANCE);


            double forceImpact = Math.max(b1.getVitesse().norme(), b2.getVitesse().norme());
            float gainDecibel = (float) Math.min(forceImpact*8 - 30, 6.0206);   // Limite (-80, 6.0206) : POURQUOI ??
            master_gain.setValue(gainDecibel);


            Vecteur positionImpact = b1.getPosition().somme(
                    b2.getPosition().difference(b1.getPosition())
                            .produit(b1.getRayon() / (b1.getRayon() + b2.getRayon()))
            );
            int fenetre = 1000;
            double pos_y = Math.min(positionImpact.x, fenetre);
            float bal = (float)pos_y / (float)(fenetre/2) - 1;
            balance.setValue(bal);

            LineListener listener = lineEvent -> {
                if (lineEvent.getType() != LineEvent.Type.STOP) {
                    return;
                }

                clip.close();
            };

            clip.addLineListener(listener);
            clip.start();

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
