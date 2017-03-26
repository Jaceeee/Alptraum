package bossbattle1;

/**
 * Created by Juan Carlos on 11/30/2016.
 */
public abstract class Orb extends Thread {
    protected float x;
    protected float y;
    protected float xSpeed;
    protected float ySpeed;
    protected boolean mvd;

    public Orb() {
        super();
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public boolean isMvd() {
        return mvd;
    }
}
