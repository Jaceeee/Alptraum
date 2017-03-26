package alptraum;

import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.*;

//shell of a screen: Const, init,render,update, return ID
public class Dungeon extends BasicGameState{
    Animation hero,steady, movingUp, movingDown,movingRight, movingLeft;
    Image worldMap;
    boolean quit = false;// to quit
    int[] duration = {200,200,200}; // an animation is a series of frames(milliseconds)
    Image cave;
    Hero player;

    float heroPositionX = 0;// keep track of position of hero
    float heroPositionY = 0;
    float heroW = 25.0f;
    float heroL = 30.0f;
    float shiftX = heroPositionX + 320;
    float shiftY = heroPositionY + 130;

    public Dungeon(int state)throws SlickException{
        // /housekeeping stuff

    }
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        cave = new Image("res/background/cave.png");
        //obj = new Image("res/mushroom.png");
        worldMap = new Image("res/town.png");
        this.player = player;
        Image[] heroSteady = {new Image("res/characters/hero/0.png"),new Image("res/characters/hero/3.png"),new Image("res/characters/hero/4.png")};
        Image[] walkUp = {new Image("res/characters/hero/2.png"),new Image("res/characters/hero/11.png"),new Image("res/characters/hero/12.png")};
        Image[] walkLeft = {new Image("res/characters/hero/1.png"),new Image("res/characters/hero/9.png"),new Image("res/characters/hero/10.png")};
        Image[] walkRight = {new Image("res/characters/hero/R1.png"),new Image("res/characters/hero/R2.png"),new Image("res/characters/hero/R3.png")};
        Image[] walkDown = {new Image("res/characters/hero/0.png"),new Image("res/characters/hero/7.png"),new Image("res/characters/hero/8.png")};

        movingDown = new Animation(walkDown, 200);
        movingLeft = new Animation(walkLeft, 200);
        movingUp = new Animation(walkUp, 200);
        movingRight = new Animation(walkRight, 200);

        steady = new Animation(heroSteady,200);

        movingRight = new Animation(walkRight,duration,true);
        movingUp = new Animation(walkUp,duration,true);
        movingLeft = new Animation(walkLeft,duration,true);
        movingDown = new Animation(walkDown,duration,true);
        hero = steady;
        // TO   DO potato Image[] walkUp={new Image("res/buck")}//Image stuf nga sprites
        //housekeeping stuff
    }
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)  throws SlickException{
        //draw text, images and stuff
        //Graphics is a paintbrush that draws
        cave.draw(heroPositionX,heroPositionY,650,428);
        //obj.draw(objX, objY,0.5f);
        hero.draw(shiftX,shiftY,heroW,heroL);
        g.drawString("hero X position: "+heroPositionX+"\nhero Y position: "+heroPositionY,400,200);
    }
    public void update(GameContainer gc, StateBasedGame sbg, int delta)  throws SlickException{
        Input input = gc.getInput();

        if(input.isKeyDown(input.KEY_UP)){
            hero = movingUp;
            //shiftY -= 0.15f;
            heroPositionY += delta*.1f;
        }
        else if(input.isKeyDown(input.KEY_DOWN)){
            hero = movingDown;
            //shiftY += 0.15f;
            heroPositionY-=delta*.1f;
        }
        else if(input.isKeyDown(input.KEY_RIGHT)){
            hero = movingRight;
            shiftX += 0.15f;
            heroPositionX+=delta*.1f;
        }
        else if(input.isKeyDown(input.KEY_LEFT)){
            hero = movingLeft;
            shiftX += 0.15f;
        }
        //if(input.)

    }
    public int getID(){
        //which screen
        return 2;
    }
}

