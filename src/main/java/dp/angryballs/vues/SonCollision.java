package dp.angryballs.vues;

import dp.angryballs.CollisionObserver;
import dp.angryballs.modele.Bille;
import mesmaths.geometrie.base.Vecteur;

import javax.sound.sampled.*;
import java.io.File;

public class SonCollision implements CollisionObserver {

    private static final float MASTER_GAIN_MAX = 6.0206f;
    private File son;
    private VueBillard billard;

    public SonCollision(VueBillard billard) {
        this.billard = billard;
        try {
            son = new File("res/bille.wav");
        }
        catch(Exception e) {
            System.err.println("Le fichier audio n'a pas pu être chargé");
        }
    }

    @Override
    public void collides(Bille b1, Bille b2) {
        new Thread(() -> {
            try {
                Clip clip = AudioSystem.getClip();
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(son);
                clip.open(inputStream);

                double forceImpact = b1.getVitesse().norme() + b2.getVitesse().norme();
                float gainDecibel = Math.min((float)(forceImpact*10 - 50), MASTER_GAIN_MAX);
                FloatControl master_gain = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                master_gain.setValue(gainDecibel);


                Vecteur positionImpact = b1.getPosition().somme(
                        b2.getPosition().difference(b1.getPosition())
                                .produit(b1.getRayon() / (b1.getRayon() + b2.getRayon()))
                );
                double fenetre = billard.largeurBillard();
                double pos_y = Math.min(positionImpact.x, fenetre);
                float bal = Math.min(Math.max((float)(pos_y / (fenetre/2) - 1), -1.0f), 1.0f);
                FloatControl balance = (FloatControl) clip.getControl(FloatControl.Type.BALANCE);
                balance.setValue(bal);

                clip.addLineListener(lineEvent -> {
                    if(lineEvent.getType() != LineEvent.Type.STOP) {
                        return;
                    }
                    lineEvent.getLine().close();
                });

                clip.start();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

}
