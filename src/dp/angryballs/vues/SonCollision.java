package dp.angryballs.vues;

import dp.angryballs.CollisionObserver;
import dp.angryballs.modele.Bille;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.FileInputStream;
import java.io.InputStream;

public class SonCollision implements CollisionObserver {
    private InputStream son;
    private AudioStream as;

    public SonCollision() {

    }

    @Override
    public void collides(Bille b1, Bille b2) {
        try {
            if(son != null) {
                if(son.available() > 0) {
                    return;
                }
                else {
                    as.close();
                    son.close();
                }
            }

            son = new FileInputStream("res/collision.wav");
            as = new AudioStream(son);

            AudioPlayer.player.start(as);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
