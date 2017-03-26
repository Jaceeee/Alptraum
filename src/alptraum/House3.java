package alptraum;/*

import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.*;

//shell of a screen: Const, init,render,update, return ID
public class House3 extends BasicGameState{
    Animation hero,steady, movingUp, movingDown,movingRight, movingLeft;
    Image house3;
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
    public House3(int state,Hero player)throws SlickException{
        this.player = player;
    }
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {


        house3 = new Image("res/background/house3.png");
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
        house3.draw(heroPositionX,heroPositionY-250,2f);
        hero.draw(shiftX,shiftY,heroW,heroL);
        g.drawString("hero X position: "+shiftX+"\nhero Y position: "+shiftY,400,200);
        g.drawString("HERO NAME: "+player.getName()+" ",100,100);
        rHero = new Rectangle(shiftX,shiftY,heroW,heroL);
        g.draw(rHero);
        transportRealWorld.setLocation(heroPositionX,heroPositionY+10);
        g.draw(transportRealWorld);
        g.draw(container1);
        g.draw(container2);
        g.draw(pot1);
    }
    public void update(GameContainer gc, StateBasedGame sbg, int delta)  throws SlickException{
        Input input = gc.getInput();

        if(input.isKeyDown(input.KEY_UP)){
            hero = movingUp;
            heroPositionY += 0.1f;
            if(heroPositionY > 484.12){
                heroPositionY -= 0.1f;
            }
        }
        else if(input.isKeyDown(input.KEY_DOWN)){
            hero = movingDown;
            heroPositionY -= 0.1f;
            if(heroPositionY < -358.8){
                heroPositionY += 0.1f;
            }
        }
        else if(input.isKeyDown(input.KEY_RIGHT)){
            hero = movingRight;
            heroPositionX -= 0.1f;
            if(heroPositionX < -420.32){
                heroPositionX += 0.1f;
            }
        }
        else if(input.isKeyDown(input.KEY_LEFT)){
            hero = movingLeft;
            heroPositionX += 0.1f;
            if(heroPositionX > 395.12){
                heroPositionX -= 0.1f;
            }
        }
        if(!(input.isKeyDown(input.KEY_UP)) &&!( input.isKeyDown(input.KEY_DOWN)) &&!(input.isKeyDown(input.KEY_LEFT))&&!(input.isKeyDown(input.KEY_RIGHT))){
            hero = steady;
        }
        if(rHero.intersects(transportRealWorld)){
            player.setName("Mike Gwapo");
            sbg.enterState(2);
        }
        else{
            moreLeft = moreRight = moreUp = moreDown = true;
        }

        if(rHero.intersects(transportRealWorld)){
            sbg.enterState(2);
            shiftY -= 10f;
        }


    }
    public int getID(){
        return 5;
    }
}

*/
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.*;

import java.awt.Font;

//shell of a screen: Const, init,render,update, return ID
public class House3 extends BasicGameState{
    Animation hero,steadyDown,steadyLeft,steadyRight,steadyUp, movingUp, movingDown,movingRight, movingLeft;
    boolean interaction, moreLeft, moreRight,moreUp,moreDown;

    Hero player;
    Char3 fFred;
    Image house3,char3,textbox;
    int direction;

    Font font;
    TrueTypeFont ttf;

    int[] duration = {200,200,200}; // an animation is a series of frames(milliseconds)
    int[] duration2 = {1000,100,100};
    float heroPositionX = 0;// keep track of position of hero
    float heroPositionY = 0;
    float heroW = 30.0f;
    float heroL = 45.0f;
    float shiftX = heroPositionX+339;
    float shiftY = heroPositionY+264;

    Rectangle rHero,rChar3, chair, transportRealWorld, walldown1,
            walldown2,longtable,bed,table1,table2,table3,table4,
            table5,table6,table7,window1,window2,window3,window4,window5;

    Item  ichair,ilongtable,ibed,itable1,itable2,itable3,itable4,
            itable5,itable6,itable7,iwindow1,iwindow2,iwindow3,iwindow4,iwindow5;
    public House3(int state,Hero player)throws SlickException{
        this.player = player;
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        ichair  = new Item("What a comfy looking chair.");
        ilongtable  = new Item("This is mahogany.");
        ibed  = new Item("This bed is soft.");
        itable1  = itable2  = itable3  = itable4  = itable5  = itable6  = itable7  = new Item("I better not disarrange these.");
        iwindow1  = iwindow2  = iwindow3  = iwindow4  = iwindow5 = new Item("I can see Schnitzels house from here.");

        player.subquests(6);

        house3 = new Image("res/background/house3.png");
        char3 = new Image("res/characters/char3/4.png");
        textbox = new Image("res/etc/textbox4.png");
        fFred = new Char3();
        rHero = new Rectangle(shiftX,shiftY,heroW,heroL);

        textbox = new Image("res/etc/textbox4.png");
        font = new Font("Century Gothic",Font.BOLD,20);
        ttf = new TrueTypeFont(font, true);

        Image[] iSteadyDown = {new Image("res/characters/hero/0.png"),new Image("res/characters/hero/3.png"),new Image("res/characters/hero/4.png")};
        Image[] iSteadyUp = {new Image("res/characters/hero/2.png"),new Image("res/characters/hero/2.png"),new Image("res/characters/hero/2.png")};
        Image[] iSteadyLeft = {new Image("res/characters/hero/1.png"),new Image("res/characters/hero/5.png"),new Image("res/characters/hero/6.png")};
        Image[] iSteadyRight = {new Image("res/characters/hero/R1.png"),new Image("res/characters/hero/R1.png"),new Image("res/characters/hero/R1.png")};

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
        transportRealWorld = new Rectangle(305f,329f,94f,5f);
        walldown1 = new Rectangle(15f,0.0f,310f,10);
        walldown2 = new Rectangle(15f,0.0f,300f,10);
        bed=new Rectangle(15.6f,87.89f,68.78f,58f);
        longtable = new Rectangle(0,0,180f,30f);
        table1 = new Rectangle(0,0,60f,50f);
        table2 = new Rectangle(0,0,60f,50f);
        table3 = new Rectangle(0,0,60f,50f);
        table4 = new Rectangle(0,0,60f,50f);
        table5 = new Rectangle(0,0,60f,50f);
        table6 = new Rectangle(0,0,60f,50f);
        table7 = new Rectangle(0,0,60f,50f);

        chair = new Rectangle(0,0,40f,40f);

        window1 = new Rectangle(138f,55f,35f,1f);
        window2 = new Rectangle(286f,55f,35f,1f);
        window3 = new Rectangle(404f,55f,35f,1f);
        window4 = new Rectangle(507f,55f,35f,1f);
        window5 = new Rectangle(507f,55f,35f,1f);
        rChar3= new Rectangle(heroPositionX+250,heroPositionY-10,heroW+10,(heroL+25)/2);
    }
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)  throws SlickException{
        house3.draw(heroPositionX,heroPositionY-250,720,600);

        char3.draw(heroPositionX+250,heroPositionY-10,heroW+10,heroL+25);
        g.drawString("QUEST COUNTER: "+player.getCurrentQuest()+" ",300,300);

        hero.draw(shiftX,shiftY,heroW,heroL);
        g.drawString("hero X position: "+shiftX+"\nhero Y position: "+shiftY,400,200);
        g.drawString("hero X position: "+heroPositionX+"\nhero Y position: "+heroPositionY,400,250);
        g.drawString("HERO NAME: "+player.getName()+" ",100,100);
        rHero = new Rectangle(shiftX,shiftY,heroW,heroL);
        g.draw(rHero);
        transportRealWorld.setLocation(heroPositionX+305,heroPositionY+319);
        walldown1.setLocation(heroPositionX+3,heroPositionY+288f);
        walldown2.setLocation(heroPositionX+394,heroPositionY+288f);
        bed.setLocation(heroPositionX+18,heroPositionY-165.44f);
        longtable.setLocation(heroPositionX+169,heroPositionY-104);
        table1.setLocation(heroPositionX+629f,heroPositionY-134.85f);
        table2.setLocation(heroPositionX+629,heroPositionY-75);
        table3.setLocation(heroPositionX+629f,heroPositionY-16);
        table4.setLocation(heroPositionX+629,heroPositionY+38);
        table5.setLocation(heroPositionX+629f,heroPositionY+92);
        table6.setLocation(heroPositionX+629f,heroPositionY+150);
        table7.setLocation(heroPositionX+629f,heroPositionY+208);
        window1.setLocation(heroPositionX+168f,heroPositionY-194);
        window2.setLocation(heroPositionX+317,heroPositionY-194);
        window3.setLocation(heroPositionX+425f,heroPositionY-194);
        window4.setLocation(heroPositionX+532,heroPositionY-194);
        window5.setLocation(heroPositionX+626f,heroPositionY-194);
        chair.setLocation(heroPositionX+243f,heroPositionY-133);
        rChar3.setLocation(heroPositionX+250,heroPositionY-10);

        g.draw(rChar3);
        g.draw(chair);
        g.draw(longtable);
        g.draw(table1);
        g.draw(table2);
        g.draw(table3);
        g.draw(table4);
        g.draw(table5);
        g.draw(table6);
        g.draw(table7);

        g.draw(window1);
        g.draw(window2);
        g.draw(window3);
        g.draw(window4);
        g.draw(window5);

        g.draw(transportRealWorld);
        g.draw(walldown1);
        g.draw(walldown2);
        g.draw(bed);

        if(interaction && collidesanything()){
            textbox.draw(0,295,720,100);
            if(rHero.intersects(rChar3)) {
                ttf.drawString(28.0f, 310.83f, fFred.Interact(2), Color.black);
                Image temp;
                if (fFred.getImage(fFred.images[fFred.ctrMessages]) == 0) {
                    temp = new Image("res/characters/char3/0.png");
                    temp.draw(556, 150, 120f, 152f);
                } else if (fFred.getImage(fFred.images[fFred.ctrMessages]) == 1) {
                    temp = new Image("res/characters/char3/1.png");
                    temp.draw(556, 150, 120f, 152f);
                } else if (fFred.getImage(fFred.images[fFred.ctrMessages]) == 2) {
                    temp = new Image("res/characters/char3/2.png");
                    temp.draw(556, 150, 120f, 152f);
                } else if (fFred.getImage(fFred.images[fFred.ctrMessages]) == 3) {
                    temp = new Image("res/characters/char3/3.png");
                    temp.draw(556, 150, 120f, 152f);
                } else if (fFred.getImage(fFred.images[fFred.ctrMessages]) == 4) {
                    temp = new Image("res/characters/char3/4.png");
                    temp.draw(556, 150, 120f, 152f);
                }
            }
            else if(rHero.intersects(chair)){ ttf.drawString(28.0f, 310.83f, ichair.Interact(-2), Color.black);  }
            else if(rHero.intersects(longtable)){  ttf.drawString(28.0f, 310.83f, ilongtable.Interact(-2), Color.black); }
            else if(rHero.intersects(bed)){  ttf.drawString(28.0f, 310.83f, ibed.Interact(-2), Color.black); }
            else if(rHero.intersects(table1) || rHero.intersects(table2) ||rHero.intersects(table3) ||rHero.intersects(table4) ||
                    rHero.intersects(table5) ||rHero.intersects(table6) ||rHero.intersects(table7)){
                ttf.drawString(28.0f, 310.83f, itable1.Interact(-2), Color.black);
            }
            else if(rHero.intersects(window1) || rHero.intersects(window2) ||rHero.intersects(window3) ||rHero.intersects(window4) ||
                    rHero.intersects(window5)){  ttf.drawString(28.0f, 310.83f, iwindow1.Interact(-2), Color.black); }

        }
    }
    public void update(GameContainer gc, StateBasedGame sbg, int delta)  throws SlickException {
        Input input = gc.getInput();
        if (!interaction) {
            if (input.isKeyDown(input.KEY_UP)) {
                //movingUp.update(delta);
                direction = 1;
                hero = movingUp;
                if (shiftY < 106.39f) {
                    heroPositionY += 0.15f;
                    if (!moreUp) {
                        heroPositionY -= 0.15f;
                    }
                    if (heroPositionY > 253.12) {
                        heroPositionY -= 0.15f;
                        if (!moreUp) {
                            heroPositionY += 0.15f;
                        }
                        if (shiftY > 56) {
                            shiftY -= 0.15f;
                            if (!moreUp) {
                                shiftY += 0.15f;
                            }
                        }
                    }
                } else {
                    shiftY -= 0.15f;
                    if (!moreUp) {
                        shiftY += 0.15f;
                    }
                }
            } else if (input.isKeyDown(input.KEY_DOWN)) {
                direction = 3;
                hero = movingDown;
                if (shiftY > 315) {
                    heroPositionY -= 0.15f;
                    if (!moreDown) {
                        heroPositionY += 0.15f;
                    }
                } else {
                    shiftY += 0.15f;
                    if (!moreDown) {
                        shiftY -= 0.15f;
                    }
                }
            } else if (input.isKeyDown(input.KEY_RIGHT)) {
                direction = 2;
                hero = movingRight;
                shiftX += 0.15f;
                if (shiftX > 671.46f || !moreRight) {
                    shiftX -= 0.15f;
                }
            } else if (input.isKeyDown(input.KEY_LEFT)) {
                direction = 4;
                hero = movingLeft;
                shiftX -= 0.15f;
                if (shiftX < 15.5f || !moreLeft) {
                    shiftX += 0.15f;
                }
            }
        }
        if (input.isKeyPressed(input.KEY_I)) {
            if (interaction) {
                if (rHero.intersects(rChar3)) {
                    if (fFred.ctrMessages >= 8 && (player.subquests[0] == false || player.subquests[1] == false
                            && player.subquests[2] == false || player.subquests[3] == false || player.subquests[4] == false
                            && player.subquests[5] == false)) {
                        if(fFred.ctrMessages == 8 && player.getCurrentQuest() == 2) {//setting quest
                            player.setQuest();
                            player.subquests(6);
                        }
                        interaction = false;
                    }else if(fFred.ctrMessages >= fFred.messages.length - 1){
                        player.subquests(1);
                        interaction = false;
                    }else{
                        if (player.getCurrentQuest() > 1){
                            fFred.ctrMessages++;
                        }else{
                            interaction = false;
                        }
                        if(fFred.ctrMessages == fFred.messages.length - 1){
                            player.setQuest();
                            System.out.print(fFred.ctrMessages+"\n");
                        }
                    }
                }
                else {
                    interaction = false;
                }
            } else if (collidesanything()) {
                interaction = true;
            }
        }
        if (collidesanything()) {
            if(rHero.intersects(chair) && rHero.intersects(longtable)){
                if(direction == 2) {
                    shiftX -=2;
                    shiftY -=2;
                    moreUp = moreRight = false;
                }
                else if(direction == 4) {
                    shiftY -=2;
                    shiftX +=2;
                    moreUp = moreLeft = false;
                }
                else if(direction == 3){
                    shiftY -=2;
                    shiftX +=2;//TODO
                    moreDown = false;
                }
            }
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
            player.setName("Mike Gwapo");
            sbg.enterState(2);
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
    }
    public boolean collidesanything(){
        if(rHero.intersects(rChar3) ||rHero.intersects(walldown2) ||rHero.intersects(walldown1) ||rHero.intersects(longtable) ||rHero.intersects(table1) || rHero.intersects(table2)|| rHero.intersects(table3)|| rHero.intersects(table4)|| rHero.intersects(table5)|| rHero.intersects(table6)|| rHero.intersects(table7)|| rHero.intersects(bed)|| rHero.intersects(chair)){
            return true;
        }
        else{
            return false;
        }
    }
    public int getID(){
        return 5;
    }
}

