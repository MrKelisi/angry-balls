package dp.angryballs.vues;

import dp.angryballs.CollisionObserver;
import dp.angryballs.modele.Bille;
import mesmaths.geometrie.base.Vecteur;

import javax.sound.sampled.*;
import java.io.File;
import java.util.EmptyStackException;
import java.util.Stack;
import java.util.concurrent.CountDownLatch;

public class SonCollision implements CollisionObserver {

    private static final int MAX_SPARE_CLIPS = 5;
    private File son;
    private Stack<Clip> availableClips;
    private VueBillard billard;

    public SonCollision(VueBillard billard) {
        availableClips = new Stack<>();
        try {
            this.billard = billard;
            son = new File("res/bille.wav");
        }
        catch(Exception e) {
            System.err.println("Le fichier audio n'a pas pu être chargé");
        }
    }

    @Override
    public void collides(Bille b1, Bille b2) {
        new Thread(new Runnable() { // the wrapper thread is unnecessary, unless it blocks on the Clip finishing, see comments
            public void run() {
                try {
                    CountDownLatch countDownLatch = new CountDownLatch(1);
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(son);
                    clip.open(inputStream);

                    FloatControl master_gain = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                    FloatControl balance = (FloatControl) clip.getControl(FloatControl.Type.BALANCE);


                    double forceImpact = Math.max(b1.getVitesse().norme(), b2.getVitesse().norme());
                    float gainDecibel = (float) Math.min(forceImpact*8 - 30, 6.0206);   // Limite (-80, 6.0206) : POURQUOI ??
                    master_gain.setValue(gainDecibel);


                    Vecteur positionImpact = b1.getPosition().somme(
                            b2.getPosition().difference(b1.getPosition())
                                    .produit(b1.getRayon() / (b1.getRayon() + b2.getRayon()))
                    );
                    double fenetre = billard.largeurBillard();
                    double pos_y = Math.min(positionImpact.x, fenetre);
                    float bal = (float) (pos_y / (fenetre/2) - 1);
                    balance.setValue(bal);

                    clip.addLineListener(lineEvent -> {
                        if(lineEvent.getType() != LineEvent.Type.STOP) {
                            return;
                        }

                        countDownLatch.countDown();
                    });
                    clip.start();

                    countDownLatch.await();

                    clip.close();
                    inputStream.close();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }

}
