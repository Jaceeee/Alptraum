package bossbattle1;

/**
 * Created by Juan Carlos on 11/13/2016.
 */
public abstract class Character {
    public int x;
    public int y;
    private int ctr;
    private String name;

    public Character(){
        this.name = name;
        x = y = ctr = 0;
    }

    public Boolean moveUp(){
        y = y - 1;
        return true;
    }

    public Boolean moveDown(){
        y = y + 1;
        return true;
    }
    public Boolean moveLeft(){
        x = x - 1;
        return true;
    }
    public Boolean moveRight(){
        x = x + 1;
        return true;
    }
}