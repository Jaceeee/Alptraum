package alptraum;

import org.newdawn.slick.SlickException;

/**
 * Created by niervin on 11/27/2016.
 */

//TODO need changes for dog
public class Dog extends Character {
    public float dogX;
    public float dogY;

    public Dog() throws SlickException{
        super("Annoying Dog");
        setMessages();
    }

    public Dog(float x, float y) throws SlickException{
        super("Annoying Dog");
        setMessages();
        dogX = x;
        dogY = y;
    }

    public void setMessages() {
        String dialogue[] = new String[2];
        dialogue[0] = "Woof Wooff";
        dialogue[1] = "I'm a dog.. I mean.. Woof Wooof Wooff";
    }

    public String Interact(int i){
        if(i < 2){
            return messages[getMessages()];
        } else {
            return messages[0];
        }
    }
}
