package alptraum;//mike's
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.*;

//shell of a screen: Const, init,render,update, return ID
public class Play extends BasicGameState{
    Animation hero,steadyDown,steadyLeft,steadyRight,steadyUp, movingUp, movingDown,movingRight, movingLeft;
    Image worldMap;
    boolean quit,isPrologue;// to quit
    int[] duration = {200,200,200}; // an animation is a series of frames(milliseconds)
    int[] duration2 = {2000,100,100}; // an animation is a series of frames(milliseconds)
    Image cave;
    int direction;
    float heroPositionX = 0;// keep track of position of hero
    float heroPositionY = 0;
    float heroW = 30.0f;
    float heroL = 45.0f;
    float shiftX = heroPositionX+360;
    float shiftY = heroPositionY+202;
    Hero player;
    Rectangle rHero;
    Rectangle backWallLeft = new Rectangle(0,330.71f,338,100);
    Rectangle backWallRight = new Rectangle(384.1f,330.71f,200,100);
    Rectangle transport = new Rectangle(333.7f,368f,50,50);

    boolean moreLeft, moreRight,moreUp,moreDown;

    Image prologue[];
    int prologuectr;
    public Play(int state,Hero player)throws SlickException{
        this.player = player;
    }
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        quit =  false;
        isPrologue = true;
        prologuectr = 0;
        player.setName("Mike Gwapo");
        moreLeft = moreRight = moreUp = moreDown = true;

        cave = new Image("res/background/cave.png");
        rHero = new Rectangle(shiftX,shiftY,heroW,heroL);

        Image[] heroSteady = {new Image("res/characters/hero/0.png"),new Image("res/characters/hero/3.png"),new Image("res/characters/hero/4.png")};
        Image[] walkUp = {new Image("res/characters/hero/2.png"),new Image("res/characters/hero/11.png"),new Image("res/characters/hero/12.png")};
        Image[] walkLeft = {new Image("res/characters/hero/1.png"),new Image("res/characters/hero/9.png"),new Image("res/characters/hero/10.png")};
        Image[] walkRight = {new Image("res/characters/hero/R1.png"),new Image("res/characters/hero/R2.png"),new Image("res/characters/hero/R3.png")};
        Image[] walkDown = {new Image("res/characters/hero/0.png"),new Image("res/characters/hero/7.png"),new Image("res/characters/hero/8.png")};

        Image[] iSteadyDown = {new Image("res/characters/hero/0.png"),new Image("res/characters/hero/3.png"),new Image("res/characters/hero/4.png")};
        Image[] iSteadyUp = {new Image("res/characters/hero/2.png"),new Image("res/characters/hero/2.png"),new Image("res/characters/hero/2.png")};
        Image[] iSteadyLeft = {new Image("res/characters/hero/1.png"),new Image("res/characters/hero/5.png"),new Image("res/characters/hero/6.png")};
        Image[] iSteadyRight = {new Image("res/characters/hero/R1.png"),new Image("res/characters/hero/R1.png"),new Image("res/characters/hero/R1.png")};

        prologue = new Image[]{new Image("res/prologue/1.png"),new Image("res/prologue/2.png"),new Image("res/prologue/3.png"),
                new Image("res/prologue/4.png"),new Image("res/prologue/5.png"),new Image("res/prologue/6.png"),
                new Image("res/prologue/7.png"),new Image("res/prologue/8.png"),new Image("res/prologue/9.png"),
                new Image("res/prologue/10.png"),new Image("res/prologue/11.png"),new Image("res/prologue/12.png"),
                new Image("res/prologue/13.png"),new Image("res/prologue/14.png"),new Image("res/prologue/15.png"),
                new Image("res/prologue/16.png"),new Image("res/prologue/17.png"),new Image("res/prologue/18.png"),
                new Image("res/prologue/18.png"),new Image("res/prologue/19.png")};

        steadyRight = new Animation(iSteadyRight,duration2,true);
        steadyUp = new Animation(iSteadyUp,duration2,true);
        steadyLeft = new Animation(iSteadyLeft,duration2,true);
        steadyDown = new Animation(iSteadyDown,duration2,true);

        hero = steadyDown;

        movingDown = new Animation(walkDown, 200);
        movingLeft = new Animation(walkLeft, 200);
        movingUp = new Animation(walkUp, 200);
        movingRight = new Animation(walkRight, 200);

        boolean moreLeft, moreRight,moreUp,moreDown;
    }
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)  throws SlickException{
        if(isPrologue){
            g.drawImage(prologue[prologuectr],0,0);
        }
        else {
            cave.draw(heroPositionX, heroPositionY, 720, 490);
            hero.draw(shiftX, shiftY, heroW, heroL);
            g.drawString("hero X position: " + shiftX + "\nhero Y position: " + shiftY, 400, 200);
            g.drawString("HERO NAME: " + player.getName() + " ", 100, 100);
            rHero = new Rectangle(shiftX, shiftY, heroW, heroL);
            g.draw(rHero);
            g.draw(backWallLeft);
            g.draw(backWallRight);
            g.draw(transport);
        }
    }
    public void update(GameContainer gc, StateBasedGame sbg, int delta)  throws SlickException{
        Input input = gc.getInput();
        if(!isPrologue) {
            if (input.isKeyDown(input.KEY_UP)) {
                direction = 1;
                hero = movingUp;
                shiftY -= 0.15f;
                if (shiftY < 61.9f) {
                    shiftY += 0.15f;
                }
            } else if (input.isKeyDown(input.KEY_DOWN)) {
                direction = 3;
                hero = movingDown;
                shiftY += 0.15f;
                if (!moreDown) {
                    shiftY -= 0.15f;
                }
            } else if (input.isKeyDown(input.KEY_RIGHT)) {
                direction = 2;
                hero = movingRight;
                shiftX += 0.15f;
                if (shiftX > 551.6f || !moreRight) {
                    shiftX -= 0.15f;
                }
            } else if (input.isKeyDown(input.KEY_LEFT)) {
                direction = 4;
                hero = movingLeft;
                shiftX -= 0.15f;
                if (shiftX < 142.28f || !moreLeft) {
                    shiftX += 0.15f;
                }
            }

            if (!(input.isKeyDown(input.KEY_UP)) && !(input.isKeyDown(input.KEY_DOWN)) && !(input.isKeyDown(input.KEY_LEFT)) && !(input.isKeyDown(input.KEY_RIGHT))) {
                if (direction == 1) {
                    hero = steadyUp;
                } else if (direction == 2) {
                    hero = steadyRight;
                } else if (direction == 3) {
                    hero = steadyDown;
                } else if (direction == 4) {
                    hero = steadyLeft;
                }
            }

            if (rHero.intersects(backWallLeft) || rHero.intersects(backWallRight)) {
                if (moreLeft == moreRight == moreUp == moreDown == true) {
                    if (input.isKeyDown(input.KEY_UP)) {
                        moreUp = false;
                    } else if (input.isKeyDown(input.KEY_DOWN)) {
                        moreDown = false;
                    } else if (input.isKeyDown(input.KEY_RIGHT)) {
                        moreRight = false;
                    } else if (input.isKeyDown(input.KEY_LEFT)) {
                        moreLeft = false;
                    }
                }
            } else {
                moreLeft = moreRight = moreUp = moreDown = true;
            }

            if (rHero.intersects(transport)) {
                sbg.enterState(2);
            }
        }else{
            if (input.isKeyPressed(input.KEY_I)) {
                if(prologuectr == prologue.length-1){
                    isPrologue = false;
                }
                else {
                    prologuectr++;
                }
            }
        }
    }
    public int getID(){
        return 1;
    }
}


