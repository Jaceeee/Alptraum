package alptraum;/*

*/
/**
 * Created by User on 11/4/2016.
 *//*

public abstract class Character {
    public float reputation;
    public String[] messages;
    public int x;
    public int y;
    int ctr;
    String name;

    public Character(){
    }

    public Character(String name, String[] messages){
        this.name = name;
        this.messages = messages;
        reputation = 0.0f;
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
*/
/**
 * Created by User on 11/4/2016.
 */
public abstract class Character {
    public float reputation;
    protected String[] messages;
    protected int[] images;
    public int x;
    public int y;
    int ctrMessages;

    public void setMessages(String[] messages) {
        this.messages = messages;
    }

    public void setImages(int[] images) {
        this.images = images;
    }

    public int getMessages(){ return ctrMessages; }
    public void addMessage(){ ctrMessages++; }
    public void setCtrMessages(int i){ ctrMessages = i; }

    int ctrImage;
    String name;

    public Character(){ }

    public Character(String name){
        this.name = name;
        this.messages = null;
        this.images = null;
        reputation = 0.0f;
        x = y = ctrImage = ctrMessages = 0;
    }

    public Character(String name, String[] messages){
        this.name = name;
        this.messages = messages;
        reputation = 0.0f;
        x = y = ctrImage = ctrMessages = 0;
    }

    public Character(String name, String[] messages,int[]images){
        this.name = name;
        this.messages = messages;
        this.images= images;
        reputation = 0.0f;
        x = y = ctrImage = ctrMessages = 0;
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

