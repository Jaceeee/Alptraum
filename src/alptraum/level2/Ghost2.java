package alptraum.level2;

/**
 * Created by niervin on 11/28/2016.
 */

import alptraum.Character;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Ghost2 extends Character {
    Image[] ghostPic = {new Image("res/characters/Ghost/0.png"), new Image("res/characters/Ghost/7.png"), new Image("res/characters/Ghost/15.png"),
            new Image("res/characters/Ghost/9.png"), new Image("res/characters/Ghost/1.png"), new Image("res/characters/Ghost/28.png"),
            new Image("res/characters/Ghost/16.png"), new Image("res/characters/Ghost/26.png")};

    float ghostposX;
    float ghostposY;

    public Ghost2() throws SlickException{
        super("Rens Arrews");
        setMessages();
    }

    public Ghost2(float x, float y) throws SlickException{
        super("Rens Arrews");
        ghostposX = x;
        ghostposY = y;
        setMessages();
    }

    //public void setImages(){ super.setImages( new int[]{0,2,3,5,1,2,4,3,2});}

    public Image getImage(int i){
        return ghostPic[i];
    }

    public void setMessages(){
        String[] dialogue = new String[12];
        dialogue[0] = "Ok! We're in the future.";
        dialogue[1] = "or so you thought.";
        dialogue[2] = "This is the teleportation room.";
        dialogue[3] = "Whenever you take a step on a teleportation circle";
        dialogue[4] = "You'd get transported to a different location.";
        dialogue[5] = "Oh no worries.";
        dialogue[6] = "There aren't traps in here.";
        dialogue[7] = "Well, good luck!";

        dialogue[8] = "The second and the final room";
        dialogue[9] = "Pretty short eh?";
        dialogue[10] = "However, the catch is that this room has its controls in reverse";
        dialogue[11] = "This level should be pretty easy if you get used to it.";

        super.setMessages(dialogue);
    }

    public String Interact(int i) {
        if (i < 12) {
            return messages[getMessages()];

        } else {
            return messages[0];
        }
    }

}
