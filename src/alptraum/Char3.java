package alptraum;

/**
 * Created by User on 11/11/2016.
 */
public class Char3 extends Character{

    public Char3() {
        super("Fat Fred");
        setImages();
        setMessages();
    }

    public void setImages() {
        super.setImages(new int[]{0,3,2,2,1,2,2,2,0,0,0,3,0,3});
    }//set images

    public void setMessages() {
        String[] dialogue = new String[13];//mu error if dili sakto size
        dialogue[0] = "OMNOMNOMNOMONOMONOMONOMNON";
        dialogue[1] = "My name is Schnitzel. Food is just so perfect. I love it.";
        dialogue[2] = "It makes me so sad to see people wasting food.";
        dialogue[3] = "I just want to eat food";
        dialogue[4] = "YOU BETTER NOT BE TOUCHING ANY OF MY FOOD";
        dialogue[5] = "Im sorry. That was rude of me. ";
        dialogue[6] = " I'm stressed right now. I prepared all these tables for a party";
        dialogue[6] = " However, my main dish is lacking an important ingredient.";
        dialogue[7] = " I need white flower essence. It can be found all around the village.";
        dialogue[8] = " I need 6 of them. Find them for me please?";//quest
        dialogue[9] = " Oh, looks like you found all of them";
        dialogue[10] = " Thank you. Thank you so much!";
        dialogue[11] = " You've been such a big help";
        dialogue[12] = " Now I can proceed with my party planning";
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
