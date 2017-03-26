package alptraum;

import java.lang.*;

public class Char2 extends Character {

    public Char2() {
        super("Brendon Fury");
        setImages();
        setMessages();
    }

    public void setImages() {
        super.setImages(new int[]{0,3,1,5,4,5,1,4,2,0,3});
    }

    public void setMessages() {
        String[] dialogue = new String[11];
        dialogue[0] = "Hey there, partner!";
        dialogue[1] = " My name's Brendon Fury, how's it goin?";
        dialogue[2] = " Im the resident musician in this town";
        dialogue[3] = " Do you want to here a sample?";
        dialogue[4] = " ... ";
        dialogue[5] = " ...";
        dialogue[6] = " Ive lost one of my instruments!";
        dialogue[7] = " Im sure it's in one of the bushes";
        dialogue[8] = " Please help me find it...";
        dialogue[9] = " Oh you found it!";
        dialogue[10] = " Thank you so much!!!";
        super.setMessages(dialogue);
    }

    public String Interact(int i){
        if(i<6) {
            return messages[ctrMessages];
        }
        else{
            return messages[i];
        }
    }

    public void incCtrMessages(){
        ctrMessages++;
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
