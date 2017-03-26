package alptraum; /**
 * Created by User on 10/19/2016.
 */

import org.lwjgl.input.Mouse; // java game
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.omg.PortableServer.POAManagerPackage.State;

public class Menu extends BasicGameState {
    public String title = "Welcome to Alptraum";
    public String mouse = "";
    Image back;
    public Menu(int state){
        //housekeeping stuff
    }
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
        //housekeeping stuff
        back = new Image("res/background/back.png");
    }
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)  throws SlickException{
        //draw text, images and stuff
        //Graphics is a paintbrush that draws
        back.draw(0,0,720,405);
        //g.drawString(title,200,50);
        g.drawString("Play",105,200);
        g.drawString("Exit",405,200);
        g.drawOval(185,16,200,100);//x,y,width,height
        g.drawRect(100,200,100,100);
        g.drawRect(400,200,100,100);
        Image logo = new Image("res/logo.png");
        logo.draw(150,16,0.6f);
        //g.drawString(mouse,220,200);

    }
    public void update(GameContainer gc, StateBasedGame sbg, int delta)  throws SlickException{
        int x = Mouse.getX();
        int y = Mouse.getY();
        mouse = "Mouse Position\n\t x: "+ x + "\t y:"+y;// gaming libraries coordinates start at the bottom left
        Input input = gc.getInput();

        if(x >100 && x <200 && y<160 && y>60){
            if(Mouse.isButtonDown(0)){
                sbg.enterState(1);
            }
        }
        if(x >400 && x <600 && y<160 && y>60){
            if(Mouse.isButtonDown(0)){
                System.exit(0);
            }
        }
    }
    public int getID(){
        return 0;
    }
}
