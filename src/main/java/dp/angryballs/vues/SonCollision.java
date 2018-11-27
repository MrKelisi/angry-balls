package dp.angryballs.vues;

import dp.angryballs.CollisionObserver;
import dp.angryballs.modele.Bille;
import mesmaths.geometrie.base.Vecteur;

import javax.sound.sampled.*;
import java.net.URL;

/**
 * Classe responsable de jouer un son lors d'une collision
 */
public class SonCollision implements CollisionObserver {
    private static final float MASTER_GAIN_MAX = 6.0206f;
    private static final float MASTER_GAIN_MIN = -80.0f;

    private static final float BALANCE_MIN = -1.0f;
    private static final float BALANCE_MAX = 1.0f;

    private URL son;
    private VueBillard billard;

    public SonCollision(VueBillard vueBillard) {
        billard = vueBillard;
        try {
            son = getClass().getClassLoader().getResource("bille.wav");
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

                /* ========== Force de l'impact : volume de l'effet de collision =================================== */

                float        forceImpact = (float)(b1.getVitesse().norme() + b2.getVitesse().norme()) * 6 - 35;
                float        gainDecibel = Math.min(Math.max(forceImpact, MASTER_GAIN_MIN), MASTER_GAIN_MAX);
                FloatControl master_gain = (FloatControl) ligne.getControl(FloatControl.Type.MASTER_GAIN);

                master_gain.setValue(gainDecibel);


                /* ========== Position de l'impact : collision stéréo ============================================== */

                Vecteur positionImpact = b1.getPosition().somme(
                        b2.getPosition().difference(b1.getPosition())
                                .produit(b1.getRayon() / (b1.getRayon() + b2.getRayon()))
                );

                double       largeur      = billard.largeurBillard();
                float        position     = (float)(positionImpact.x / largeur) * 2 - 1;
                float        balanceValue = Math.min(Math.max(position, BALANCE_MIN), BALANCE_MAX);
                FloatControl balance      = (FloatControl) ligne.getControl(FloatControl.Type.BALANCE);

                balance.setValue(balanceValue);


                /* ========== Lecture du fichier audio ============================================================= */

                int    tailleFrame  = format.getFrameSize();
                int    m            = (int)(0.01*format.getFrameRate());  // nombre de frames en 1/100ème  seconde (frameRate = fréquence en Hertz)
                int    tailleTampon = m * tailleFrame;                    // nombre d'octets lus en une fois dans la boucle
                byte[] tampon       = new byte[tailleTampon];

                ligne.start();

                long l = audioInputStream.getFrameLength();  // taille du fichier audio exprimée en nombre de frames
                long q = l/m;                                // nbre de passages à faire
                long r = l%m;                                // nbre de frames qu'il restera à lire après la boucle
                int reste = (int)(r*tailleFrame);            // nbre d'octets restant à lire après la boucle

                for (long p = 0; p < q; ++p) {
                    audioInputStream.read(tampon);               // lit m frames sur le fichier audio
                    ligne.write(tampon, 0, tampon.length);  // écrit les m frames sur la ligne et donc les envoie sur un haut-parleur
                }
                audioInputStream.read(tampon, 0, reste);    // lit les r frames restant sur le fichier audio
                ligne.write(tampon, 0, reste);              // écrit les r frames restant sur la ligne et donc les envoie sur un haut-parleur

                //Attente de la lecture du son
                Thread.sleep((long) (audioInputStream.getFrameLength() * 1000 / format.getFrameRate()));

                ligne.close();
                audioInputStream.close();
            }
            catch (LineUnavailableException e) {

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

}
