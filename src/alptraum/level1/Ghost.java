package alptraum.level1;

/**
 * Created by niervin on 11/26/2016.
 */
import alptraum.Character;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Ghost extends Character {
    Image[] ghostPic = {new Image("res/characters/Ghost/0.png"), new Image("res/characters/Ghost/7.png"), new Image("res/characters/Ghost/15.png"),
    new Image("res/characters/Ghost/9.png"), new Image("res/characters/Ghost/1.png"), new Image("res/characters/Ghost/28.png")};

    float ghostposX;
    float ghostposY;

    public Ghost() throws SlickException{
        super("Rens Arrews");
        setMessages();
    }

    public Ghost(float x, float y) throws SlickException{
        super("Rens Arrews");
        ghostposX = x;
        ghostposY = y;
        setMessages();
    }

    public void setImages(){ super.setImages( new int[]{0,2,3,5,1,2,4,3,2});}

    public Image getImage(int i){
        return ghostPic[i];
    }

    public void setMessages(){
        String[] dialogue = new String[42];
        dialogue[0] = "Hiya!";
        dialogue[1] = "Looks like this is a dangerous level to overcome.";
        dialogue[2] = "But don't worry!";
        dialogue[3] = "As long as you avoid the moving swords, you'll be fineeee.";
        dialogue[4] = "Ngehehehehehe!!";

        dialogue[5] = "Can dogs see ghosts?";
        dialogue[6] = "A dog just barked at me when I took a piss.";
        dialogue[7] = "Boy, was it scary.";
        dialogue[8] = "If I saw that dog again, I might hide behind a tree.";
        dialogue[9] = "Ngek";

        dialogue[10] = "If you have almost run out of HP.";
        dialogue[11] = "try to take that cake over there.";
        dialogue[12] = "Perhaps it may restore your health a bit.";

        dialogue[13] = "Oopppsss...";
        dialogue[14] = "This is awkward.";
        dialogue[15] = "Ummm.. I'm sorry. I should just..";

        dialogue[16] = "To make up for last time";
        dialogue[17] = "DO NOT PRESS I ON THAT ROCK!";
        dialogue[18] = "Well I tried it before you came here.";
        dialogue[19] = "Boy, that was surprising!";
        dialogue[20] = "Anyways, these flying pieces of meat are as dangerous";
        dialogue[21] = "as the swords from before. Also beware of CAKES!";
        dialogue[22] = "They deal serious damage.";
        dialogue[23] = "Though it only deals 1 damage. -_-";
        dialogue[24] = "Enough chitchat. Let's go shall we.";

        dialogue[25] = "Ughhh.. Wish there was a computer here";
        dialogue[26] = "Oh. Please don't mind me.";
        dialogue[27] = "I was just talking to myself";

        dialogue[28] = "Ughhh.. I seriously don't know how to cross this one.";
        dialogue[29] = "Surely you could come across unscathed right?";

        dialogue[30] = "Ummmm... what was it again.. ughh";
        dialogue[31] = "Something about s.s.ssw.swi.";

        dialogue[32] = "A FREAKEN DOG!!! @#%@s";
        dialogue[33] = "@$@# okay calm down...";
        dialogue[34] = "PLEASE DON'T DO THAT AGAIN!!";

        dialogue[35] = "Hey, time for you to fight this guy.";
        dialogue[36] = "He might just look like some weird orb shape...";
        dialogue[37] = "DON'T UNDERESTIMATE HIM!";
        dialogue[38] = "Move around using the arrow keys.";
        dialogue[39] = "That's up down left right for you.";
        dialogue[40] = "Shoot awesome bolts using the mouse left click.";
        dialogue[41] = "Good luck, brave soul!";

        super.setMessages(dialogue);
    }
    public String Interact(int i) {
        if (i < 35) {
            return messages[getMessages()];

        } else {
            return messages[9];
        }
    }
}
