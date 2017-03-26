package alptraum;

/**
 * Created by User on 11/11/2016.
 */
public class Char1 extends Character{
    Hero player;
    public Char1() {
        super("Schnitzel");
        setImages();
        setMessages();
    }
    public Char1(Hero player) {
        super("Schnitzel");
        this.player = player;
        setImages();
        setMessages();
    }

    public void setImages() {
        super.setImages(new int[]{0,3,2,2,1,2,0,5,3});
    }

    public void setMessages() {
        String[] dialogue = new String[9];
        dialogue[0] = "Hi my name is Angel. Nice to meet you";
        dialogue[1] = "I havent seen you from around here. Are you new?";
        dialogue[2] = "I knew it. I actually everyone and everything.";
        dialogue[3] = "It's no big deal since I'm magic.";
        dialogue[4] = "Some people say Im crazy. But I'm not! ";
        dialogue[5] = "Can you help me find my wings?";
        dialogue[6] = "Thank you for helping me finding my wings";
        dialogue[7] = "You truly are a wonderful person";
        dialogue[8] = "Have a nice day";
        super.setMessages(dialogue);
    }

    public String Interact(int i){
        if(i<6) {
            return messages[ctrMessages];
        }
        else{
            return messages[0];
        }
    }

    public int getImage(int i){
        if(i<5) {
            return images[ctrMessages];
        }
        else{
            return images[i];
        }
    }
}
