package alptraum.level3;

import alptraum.Character;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by niervin on 11/27/2016.
 */
public class Ghost1 extends Character {
    Image[] ghostPic = {new Image("res/characters/Ghost/0.png"), new Image("res/characters/Ghost/7.png"), new Image("res/characters/Ghost/15.png"),
            new Image("res/characters/Ghost/9.png"), new Image("res/characters/Ghost/1.png"), new Image("res/characters/Ghost/28.png"),
            new Image("res/characters/Ghost/16.png"), new Image("res/characters/Ghost/26.png")};

    float ghostposX;
    float ghostposY;

    public Ghost1() throws SlickException{
        super("Rens Arrews");
        setMessages();
    }

    public Ghost1(float x, float y) throws SlickException{
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
        String[] dialogue = new String[10];
        dialogue[0] = "Oya you seem surprised my friend!";
        dialogue[1] = "This is Alptraum.";
        dialogue[2] = "This world is designed by you to help the residents of this town.";
        dialogue[3] = "The girl from earlier is deluding herself in believing";
        dialogue[4] =  "that she is an Angel or something.";
        dialogue[5] = "Try to talk to our winged friend over there.";
        dialogue[6] = "Perhaps you might help her.";

        dialogue[7] = "Your choices with our angel friend affects her mood.";
        dialogue[8] = "Oh don't worry. She won't bite you or anything. I hope.";

        super.setMessages(dialogue);
    }

    public String Interact(int i) {
        if (i < 10) {
            return messages[getMessages()];

        } else {
            return messages[0];
        }
    }
}
