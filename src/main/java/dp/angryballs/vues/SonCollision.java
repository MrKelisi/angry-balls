package dp.angryballs.vues;

import dp.angryballs.CollisionObserver;
import dp.angryballs.modele.Bille;
import mesmaths.geometrie.base.Vecteur;

import javax.sound.sampled.*;
import java.io.File;
import java.util.concurrent.CountDownLatch;

/**
 * Classe responsable de jouer un son lors d'une collision
 */
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
            e.printStackTrace();
        }
    }

    @Override
    public void collides(Bille b1, Bille b2) {
        new Thread(() -> {
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(son);
                AudioFormat format = audioInputStream.getFormat();

                SourceDataLine ligne = AudioSystem.getSourceDataLine(format);
                ligne.open(format);

                double forceImpact = b1.getVitesse().norme() + b2.getVitesse().norme();
                float gainDecibel = Math.min((float)(forceImpact*10 - 50), MASTER_GAIN_MAX);
                FloatControl master_gain = (FloatControl) ligne.getControl(FloatControl.Type.MASTER_GAIN);
                master_gain.setValue(gainDecibel);


                //TODO: pas supporté sur mon pc ?
                /*Vecteur positionImpact = b1.getPosition().somme(
                        b2.getPosition().difference(b1.getPosition())
                                .produit(b1.getRayon() / (b1.getRayon() + b2.getRayon()))
                );
                double fenetre = billard.largeurBillard();
                double pos_y = Math.min(positionImpact.x, fenetre);
                float bal = Math.min(Math.max((float)(pos_y / (fenetre/2) - 1), -1.0f), 1.0f);
                FloatControl balance = (FloatControl) ligne.getControl(FloatControl.Type.BALANCE);
                balance.setValue(bal);*/

                int tailleFrame = format.getFrameSize();

                int m = (int)(0.01*format.getFrameRate()); //nombre de frames en 1/100ème  seconde (frameRate = fréquence en Herz)

                int tailleTampon = m * tailleFrame; //nombre d'octets lus en une fois dans la boucle

                byte[] tampon = new byte[tailleTampon];

                ligne.start();


                long l = audioInputStream.getFrameLength(); // taille du fichier audio exprimée en nombre de frames

                long q, r;

// l = q*n + r  :  division euclidienne

                q = l/m; //nbre de passages à faire
                r = l%m; // nbre de frames qu'il restera à lire après la boucle
                int reste; // nbre d'octets restant à lire après la boucle

                reste = (int)(r*tailleFrame);
                long p;
                for ( p = 0 ; p < q; ++p)
                {
                    audioInputStream.read(tampon); // lit m frames sur le fichier audio
                    ligne.write(tampon, 0, tampon.length); // écrit les m frames sur la ligne et donc les envoie sur un haut-parleur
                }

                audioInputStream.read(tampon, 0, reste); // lit les r  frames restant sur le fichier audio
                ligne.write(tampon, 0, reste); // écrit les r frames restant sur la ligne et donc les envoie sur un haut-parleur


                Thread.sleep(1000); //TODO: valeur

                ligne.close();
                audioInputStream.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

}
