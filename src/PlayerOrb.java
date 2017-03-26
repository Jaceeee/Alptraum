import org.newdawn.slick.SlickException;
import bossbattle1.Orb;

import static java.lang.Math.abs;

/**
 * Created by Juan Carlos on 11/22/2016.
 */
public class PlayerOrb extends Orb {
    private float x;
    private float y;
    private float xSpeed;
    private float ySpeed;
    private boolean mvd;

    public PlayerOrb() {
        super();
        x = 0;
        y = 0;
    }

    public PlayerOrb(float x, float y, float xSpeed, float ySpeed) throws SlickException {
        super();
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        mvd = false;
    }

    public void launch(float targetX, float targetY, int delta) {
        mvd = true;
        System.out.println("Mouse x: " + targetX + " y: " + targetY);
        System.out.println("bossbattle1.Orb x: " + x + " y: " + y);
        float yLength = targetY - y, xLength = targetX - x;
        System.out.println(yLength + " " + xLength);


        if (abs(yLength) <= 20) {
            ySpeed = 405 / (delta * 500);
        } else if (abs(yLength) > 20) {
            ySpeed = (targetY * -1) / (delta * 500);
//                xSpeed = xLength / (delta * 100)/*((delta * (targetX % x) / x * 1000))*/;
        }
//        }

//        if (y != 0) {
        if (abs(xLength) <= 20) {
            xSpeed = (targetX < x) ? -720 / (1000 * abs(xLength)) : 720 / (1000 * abs(xLength));
        } else if (abs(xLength) > 20) {
            xSpeed = (targetX < x) ? -720 / (500 * abs(xLength)) : 720 / (500 * abs(xLength));
        }
        System.out.println("ySpeed = " + ySpeed + " Xspeed = " + xSpeed);
    }

    public void halt() {
        mvd = false;
        xSpeed = 0;
        ySpeed = 0;
    }

    public boolean isMoving() {
        return mvd;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void run() {
        x += xSpeed;
        y += ySpeed;
    }
}
