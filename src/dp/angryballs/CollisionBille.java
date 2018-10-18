package dp.angryballs;

import dp.angryballs.modele.Bille;
import dp.angryballs.modele.OutilsBille;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class CollisionBille extends Collision {
    private static InputStream son;

    public CollisionBille(Bille bille) {
        super(bille);

        try {
            son = new FileInputStream("res/bille.wav");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void visite(Bille other) {
        if(OutilsBille.gestionCollisionBilleBille((Bille) forme, other)) {
            try {
                AudioStream as = new AudioStream(son);
                AudioPlayer.player.start(as);
            } catch(Exception e){}
        }
    }
}
