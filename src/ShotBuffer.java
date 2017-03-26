import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by Juan Carlos on 11/24/2016.
 */
public class ShotBuffer extends Image {
    private static final int MAXSHOTS = 5;
    private static final String res = "res/launchedShot1.png";
    private PlayerOrb shots;

    public ShotBuffer() throws SlickException {
        super(res);
        shots = new PlayerOrb(0, 0, 1, 1);
    }

    public void launch(float targetX, float targetY,int delta) {
        shots.launch(targetX, targetY,delta);
    }

    public float getX() {
        return shots.getX();
    }

    public float getY() {
        return shots.getY();
    }

    public void setX(float x) {
        shots.setX(x);
    }

    public void setY(float y) {
        shots.setY(y);
    }

    public void run() {
        shots.run();
    }

    public boolean isMoving() {
        return shots.isMoving();
    }

    public void halt() {
        shots.halt();
    }

    public void setX(int x) {
        shots.setX(x);
    }

    public void setY(int y) {
        shots.setY(y);
    }
}
