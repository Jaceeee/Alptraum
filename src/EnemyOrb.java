import bossbattle1.Orb;
import org.newdawn.slick.SlickException;

import java.util.Random;

/**
 * Created by Juan Carlos on 11/30/2016.
 */
public class EnemyOrb extends Orb {
    private Random random;
    private boolean direction;
    private int hp;

    public EnemyOrb() {
        super();
    }

    public EnemyOrb(float x, float y, float xSpeed, float ySpeed) throws SlickException {
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.mvd = false;
        this.random = new Random();
        this.direction = false;
        this.hp = 50;
    }

    public void start() {
        mvd = true;
    }

    public void move() {
        if (mvd) {
            xSpeed = rand();
            x += xSpeed;
        }
        if (x < 100)
            direction = true;
        else if (x > 620)
            direction = false;
    }

    private float rand() {
        if (direction)
            return random.nextFloat() % 4;
        else
            return random.nextFloat() % 4 * -1;
    }

    public void subtractHP() {
        hp -= 5;
    }

    public int getHp() {
        return hp;
    }
}