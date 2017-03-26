package alptraum.level3;

import alptraum.Character;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by niervin on 11/28/2016.
 */
public class Angel extends Character {
//    Image[] ghostPic = {new Image("res/characters/Ghost/0.png"), new Image("res/characters/Ghost/7.png"), new Image("res/characters/Ghost/15.png"),
//            new Image("res/characters/Ghost/9.png"), new Image("res/characters/Ghost/1.png"), new Image("res/characters/Ghost/28.png"),
//            new Image("res/characters/Ghost/16.png"), new Image("res/characters/Ghost/26.png")};

    Image angelPic = new Image("res/characters/Angel/9.png");
    float angelposX;
    float angelposY;

    public Angel() throws SlickException {
        super("Angel");
        setMessages();
    }

    public Angel(float x, float y) throws SlickException {
        super("Angel");
        angelposX = x;
        angelposY = y;
        setMessages();
    }

    //public void setImages(){ super.setImages( new int[]{0,2,3,5,1,2,4,3,2});}

    public Image getImage() {
        return angelPic;
    }

    public void setMessages() {
        String[] dialogue = new String[19];
        dialogue[0] = "Hello.";
        dialogue[1] = "It's been a while since I had visitors.";
        dialogue[2] = "That friend of yours over there is quite entertaining.";
        dialogue[3] = "Never had fun for such a while.";
        dialogue[4] = "Oh. He just came recently.";
        dialogue[5] = "Would you like to become my friend?";

        dialogue[6] = "Oh I see....";
        dialogue[7] = "Really? Is it fine of you to be my friend?";
        dialogue[8] = "I'm glad to have another friend to talk to :) ";
        dialogue[9] = "NO                                                YES";

        dialogue[10] = "Ummm.. Let's see.";
        dialogue[11] = "What are things that people do together?";
        dialogue[12] = "Hmmm..";
        dialogue[13] = "Perhaps, we could chat all day long.";
        dialogue[14] = "Hmmm.. let's do just that";

        dialogue[15] = "GO HOME? ";

        dialogue[16] = "Eh?! you're going!!";
        dialogue[17] = "Please don't... ";
        dialogue[18] = "I guess I don't have any choice do I.";

        super.setMessages(dialogue);
    }

    public String Interact(int i) {
        if (i < 19) {
            return messages[i];

        } else {

            return messages[0];
        }
    }
}
