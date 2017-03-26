package alptraum;/*
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.*;

//shell of a screen: Const, init,render,update, return ID
public class House2 extends BasicGameState{
    Animation hero,steady, movingUp, movingDown,movingRight, movingLeft;
    Image house1;
    int[] duration = {200,200,200}; // an animation is a series of frames(milliseconds)
    int[] duration2 = {1000,100,100};
    float heroPositionX = 0;// keep track of position of hero
    float heroPositionY = 0;
    float heroW = 25.0f;
    float heroL = 30.0f;
    float shiftX = heroPositionX + 298.49f;
    float shiftY = heroPositionY + 283.1f;

    Hero player;

    Rectangle rHero;

    boolean moreLeft, moreRight,moreUp,moreDown;

    Rectangle longtable,pot1,container1,container2,transportRealWorld;
    public House2(int state,Hero player)throws SlickException{
        this.player = player;
    }
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        //player.setName("Mike Gwapo Kaayu");

        house1 = new Image("res/background/house2.png");
        rHero = new Rectangle(shiftX,shiftY,heroW,heroL);

        Image[] heroSteady = {new Image("res/characters/hero/0.png"),new Image("res/characters/hero/3.png"),new Image("res/characters/hero/4.png")};
        Image[] walkUp = {new Image("res/characters/hero/2.png"),new Image("res/characters/hero/11.png"),new Image("res/characters/hero/12.png")};
        Image[] walkLeft = {new Image("res/characters/hero/1.png"),new Image("res/characters/hero/9.png"),new Image("res/characters/hero/10.png")};
        Image[] walkRight = {new Image("res/characters/hero/R1.png"),new Image("res/characters/hero/R2.png"),new Image("res/characters/hero/R3.png")};
        Image[] walkDown = {new Image("res/characters/hero/0.png"),new Image("res/characters/hero/7.png"),new Image("res/characters/hero/8.png")};

        movingRight = new Animation(walkRight,duration,true);
        movingUp = new Animation(walkUp,duration,true);
        movingLeft = new Animation(walkLeft,duration,true);
        movingDown = new Animation(walkDown,duration,true);
        steady = new Animation(heroSteady,duration2, true);
        hero = steady;

        boolean moreLeft= moreRight=moreUp,moreDown;
        transportRealWorld = new Rectangle(305f,329f,94f,5f);
        pot1= new Rectangle(570f,162f,23f,20f);
        container1 = new Rectangle(570f,190f,23f,20f);
        container2 = new Rectangle(570f,219f,23f,20f);
        //pot1= new Rectangle(572f,217.19f,32f,24f);
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)  throws SlickException{
        house1.draw(-5,-5,640,360);
        hero.draw(shiftX,shiftY,heroW,heroL);
        g.drawString("hero X position: "+shiftX+"\nhero Y position: "+shiftY,400,200);
        g.drawString("HERO NAME: "+player.getName()+" ",100,100);
        rHero = new Rectangle(shiftX,shiftY,heroW,heroL);
        g.draw(rHero);
        g.draw(transportRealWorld);
        g.draw(container1);
        g.draw(container2);
        g.draw(pot1);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta)  throws SlickException{
        Input input = gc.getInput();


        if(input.isKeyDown(input.KEY_UP)){
            hero = movingUp;
            shiftY -= 0.1f;
            if(shiftY<137.39f){
                shiftY += 0.1f;
            }
        }
        else if(input.isKeyDown(input.KEY_DOWN)){
            hero = movingDown;
            shiftY += 0.1f;
            if(!moreDown){
                shiftY -= 0.1f;
            }
        }
        else if(input.isKeyDown(input.KEY_RIGHT)){
            hero = movingRight;
            shiftX += 0.1f;
            if(shiftX>579.18f || !moreRight){
                shiftX -= 0.1f;
            }
        }
        else if(input.isKeyDown(input.KEY_LEFT)){
            hero = movingLeft;
            shiftX -= 0.1f;
            if(shiftX<22.68f|| !moreLeft){
                shiftX += 0.1f;
            }
        }
        if(!(input.isKeyDown(input.KEY_UP)) &&!( input.isKeyDown(input.KEY_DOWN)) &&!(input.isKeyDown(input.KEY_LEFT))&&!(input.isKeyDown(input.KEY_RIGHT))){
            hero = steady;
        }
        if(rHero.intersects(transportRealWorld)){
            sbg.enterState(2);
        }
*//*        if(rHero.intersects(backWallLeft) || rHero.intersects(backWallRight)){
            if(moreLeft == moreRight == moreUp == moreDown == true) {
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
        }*//*
        else{
            moreLeft = moreRight = moreUp = moreDown = true;
        }

        if(rHero.intersects(transportRealWorld)){
            sbg.enterState(2);
            shiftY -= 10f;
        }


    }
    public int getID(){
        //which screen
        return 4;
    }
}*/import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.*;
import java.awt.Font;

//shell of a screen: Const, init,render,update, return ID
public class House2 extends BasicGameState{
    Animation hero,steadyDown,steadyLeft,steadyRight,steadyUp, movingUp, movingDown,movingRight, movingLeft;
    boolean interaction,moreLeft, moreRight,moreUp,moreDown;
    Item ipot1,icontainer1,icontainer2;
    Image house1,char2,textbox;

    Hero player;
    Char2 bFury;
    Font font;
    TrueTypeFont ttf;

    int[] duration = {200,200,200}; // an animation is a series of frames(milliseconds)
    int[] duration2 = {1000,100,100};

    float heroPositionX = 0;// keep track of position of hero
    float heroPositionY = 0;
    float heroW = 30.0f;
    float heroL = 45.0f;
    float shiftX = heroPositionX + 383.49f;
    float shiftY = heroPositionY + 310.1f;

    float char2PositionX = 315;
    float char2PositionY = 190;

    int direction;

    Rectangle rHero,wall1,wall2,rChar2, pot1,container1,container2,transportRealWorld;
    public House2(int state,Hero player)throws SlickException{
        this.player = player;
    }
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        rHero = new Rectangle(shiftX,shiftY,heroW,heroL);
        bFury = new Char2();
        player.setName("Mike in House 2");

        interaction = false;
        house1 = new Image("res/background/house2.png");
        char2 = new Image("res/characters/char2/9.png");


        textbox = new Image("res/etc/textbox4.png");
        font = new Font("Century Gothic",Font.BOLD,20);
        ttf = new TrueTypeFont(font, true);

        Image[] iSteadyDown = {new Image("res/characters/hero/0.png"),new Image("res/characters/hero/3.png"),new Image("res/characters/hero/4.png")};
        Image[] iSteadyUp = {new Image("res/characters/hero/2.png"),new Image("res/characters/hero/2.png"),new Image("res/characters/hero/2.png")};
        Image[] iSteadyLeft = {new Image("res/characters/hero/1.png"),new Image("res/characters/hero/5.png"),new Image("res/characters/hero/6.png")};
        Image[] iSteadyRight = {new Image("res/characters/hero/R1.png"),new Image("res/characters/hero/R1.png"),new Image("res/characters/hero/R1.png")};

        Image[] heroSteady = {new Image("res/characters/hero/0.png"),new Image("res/characters/hero/3.png"),new Image("res/characters/hero/4.png")};
        Image[] walkUp = {new Image("res/characters/hero/2.png"),new Image("res/characters/hero/11.png"),new Image("res/characters/hero/12.png")};
        Image[] walkLeft = {new Image("res/characters/hero/1.png"),new Image("res/characters/hero/9.png"),new Image("res/characters/hero/10.png")};
        Image[] walkRight = {new Image("res/characters/hero/R1.png"),new Image("res/characters/hero/R2.png"),new Image("res/characters/hero/R3.png")};
        Image[] walkDown = {new Image("res/characters/hero/0.png"),new Image("res/characters/hero/7.png"),new Image("res/characters/hero/8.png")};

        movingRight = new Animation(walkRight,duration,true);
        movingUp = new Animation(walkUp,duration,true);
        movingLeft = new Animation(walkLeft,duration,true);
        movingDown = new Animation(walkDown,duration,true);
        steadyRight = new Animation(iSteadyRight,duration2,true);
        steadyUp = new Animation(iSteadyUp,duration2,true);
        steadyLeft = new Animation(iSteadyLeft,duration2,true);
        steadyDown = new Animation(iSteadyDown,duration2,true);

        hero = steadyDown;

        boolean moreLeft= moreRight=moreUp =moreDown;
        transportRealWorld = new Rectangle(349f,368f,94f,5f);
        pot1= new Rectangle(643f,188f,23f,20f);
        container1 = new Rectangle(643f,213f,23f,20f);
        container2 = new Rectangle(643f,249f,23f,20f);
        wall1 = new Rectangle(31f,342f,320f,20f);
        wall2 = new Rectangle(441f,342f,315f,20f);
        rChar2 = new Rectangle(char2PositionX,char2PositionY,heroW,heroL/2);
    }
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)  throws SlickException{
        house1.draw(-5,-5,720,410);
        char2.draw(char2PositionX,char2PositionY,heroW,heroL+6);
        hero.draw(shiftX,shiftY,heroW,heroL);

        //g.drawString("hero X position: "+shiftX+"\nhero Y position: "+shiftY,400,200);
        //g.drawString("HERO NAME: "+player.getName()+" ",100,100);
        g.drawString("QUEST COUNTER: "+player.getCurrentQuest() +" ",300,300);
        rHero = new Rectangle(shiftX,shiftY,heroW,heroL);
        /*g.draw(rHero);
        g.draw(rChar2);

        g.draw(wall1);
        g.draw(wall2);
        g.draw(transportRealWorld);
        g.draw(container1);
        g.draw(container2);
        g.draw(pot1);*/
        if(interaction){
            textbox.draw(0,295,720,100);
            ttf.drawString(28.0f,310.83f,bFury.Interact(2),Color.black);
            Image temp;

            if(bFury.getImage(bFury.images[bFury.ctrMessages]) == 0) {
                temp = new Image("res/characters/char2/0.png");
                temp.draw(556f,150f,120f,152f);
            }
            else if(bFury.getImage(bFury.images[bFury.ctrMessages]) == 1){
                temp = new Image("res/characters/char2/1.png");
                temp.draw(535f,150f,140f,152f);
            } else if(bFury.getImage(bFury.images[bFury.ctrMessages]) == 2){
                temp = new Image("res/characters/char2/2.png");
                temp.draw(556f,150f,120f,152f);
            }else if(bFury.getImage(bFury.images[bFury.ctrMessages]) == 3){
                temp = new Image("res/characters/char2/3.png");
                temp.draw(535f,150f,140f,152f);
            }else if(bFury.getImage(bFury.images[bFury.ctrMessages]) == 4){
                temp = new Image("res/characters/char2/4.png");
                temp.draw(556f,150f,120f,152f);
            }else if(bFury.getImage(bFury.images[bFury.ctrMessages]) == 5){
                temp = new Image("res/characters/char2/5.png");
                temp.draw(556f,150f,120f,152f);
            }else if(bFury.getImage(bFury.images[bFury.ctrMessages]) == 6){
                temp = new Image("res/characters/char2/6.png");
                temp.draw(556f,150f,120f,152f);
            }
        }
    }
    public void update(GameContainer gc, StateBasedGame sbg, int delta)  throws SlickException {
        Input input = gc.getInput();

        if (!interaction) {
            if (input.isKeyDown(input.KEY_UP)) {
                direction = 1;
                hero = movingUp;
                shiftY -= 0.15f;
                if (shiftY < 149.39f || !moreUp) {
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
                if (shiftX > 681.46f || !moreRight) {
                    shiftX -= 0.15f;
                }
            } else if (input.isKeyDown(input.KEY_LEFT)) {
                direction = 4;
                hero = movingLeft;
                shiftX -= 0.15f;
                if (shiftX < 31.5f || !moreLeft) {
                    shiftX += 0.15f;
                }
            }
        }
        if (input.isKeyPressed(input.KEY_I)) {
            if (interaction) {
                if (rHero.intersects(rChar2)) {//interaction intitially false
                    if (bFury.ctrMessages >= 8 && player.subquests[0]== false) {//stop
                        if (bFury.ctrMessages == 8 && player.getCurrentQuest() == 4) {//setting quest
                            player.setQuest();
                            player.subquests[0]= false;
                        }
                        interaction = false;
                    } else if (bFury.ctrMessages >= bFury.messages.length - 1) {
                        interaction = false;
                    } else {
                        if (player.getCurrentQuest() > 1){
                            bFury.ctrMessages++;
                        }else{
                            interaction = false;
                        }
                        if(bFury.ctrMessages == bFury.messages.length - 1){
                            player.setQuest();
                            System.out.print(bFury.ctrMessages+"\n");
                        }
                    }
                } else {
                    interaction = false;
                }
            } else {
                if (collidesanything())
                    interaction = true;
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


        if (rHero.intersects(transportRealWorld)) {
            sbg.enterState(2);
        }

        if (collidesanything()) {
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

        if (rHero.intersects(transportRealWorld)) {
            sbg.enterState(2);
            shiftY -= 10f;
        }
    }
    public boolean collidesanything() {
        if (rHero.intersects(rChar2) || rHero.intersects(wall1) || rHero.intersects(wall2) || rHero.intersects(pot1) || rHero.intersects(container2) || rHero.intersects(container1)){
            return true;
        }else{
            return false;
        }
    }

    public int getID(){
        return 4;
    }
}



