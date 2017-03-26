package alptraum;/*

*/
/**
 * Created by User on 11/4/2016.
 *//*
*/
public class Item {
    int questNo;
    protected String[] messages;
    int ctrMessages;

    public Item() {
        setMessages();
    }

    public Item(String a) {
        messages = new String[5];
        messages[0] = a;
        ctrMessages = 0;
    }

    public Item(String[] b, int i) {
        messages = b;
        questNo = i;
        ctrMessages = 1;
        System.out.println(ctrMessages + " mao ni si ctrmessages");
    }

    public void setMessages() {
        String[] dialogue = new String[1];
        dialogue[0] = " This looks nice.";
        messages = dialogue;
    }

    public String Interact(int i) {
        if (i > 0) {
            if(ctrMessages < messages.length) {
                return messages[ctrMessages];
            } else{
                return messages[0];
            }
        } else {
            //System.out.println("negative messages");
            return messages[0];
        }
    }

    public void addCtrMessage(){
        System.out.println("YOOOOOOOOOOOO!!!!");
        ctrMessages++;
        System.out.println(ctrMessages + " mao ni si ctrMessages");
    }

    public void setCtrMessage(int i){ ctrMessages = i; }

    public int getCtrMessages(){ return ctrMessages; }
}
