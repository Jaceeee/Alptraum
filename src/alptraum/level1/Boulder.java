package alptraum.level1;

/**
 * Created by niervin on 11/11/2016.
 */
import org.newdawn.slick.geom.Rectangle;

public class Boulder extends Rectangle{
    boolean up;
    boolean down;
    boolean left;
    boolean right;
    Rectangle player;

    public Boulder(Rectangle player){
        super(0,0,35f,40f);
        this.player = player;
        up = down = left = right = false;
    }

    void check(){


    }

}
