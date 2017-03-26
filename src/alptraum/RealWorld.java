package alptraum;//Mikes
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.*;

public class RealWorld extends BasicGameState{
    Animation hero,steadyDown,steadyLeft,steadyRight,steadyUp, movingUp, movingDown,movingRight, movingLeft;
    boolean interaction, moreLeft, moreRight,moreUp,moreDown;

    java.awt.Font font;
    TrueTypeFont ttf;
    int[] duration = {200,200,200};
    int[] duration2 = {2000,100,100};
    int direction;
    Rectangle rHero;
    Image textbox;
    Hero player;

    float heroPositionX = 0;// keep track of position of hero
    float heroPositionY = 0;
    float heroW = 30.0f;
    float heroL = 45.0f;
    float shiftX = heroPositionX+360;
    float shiftY = heroPositionY+203;

    Rectangle transporthouse1 = new Rectangle(0,0,29f,5f);
    Rectangle transporthouse2 = new Rectangle(0,0,50f,5f);
    Rectangle transporthouse3 = new Rectangle(0,0,50f,5f);
    Image realWorld,treetop1,treetop2,housetop;

    Rectangle helper1,helper2,house4Left,house4Right, wallLeft, wallRight
            , squareFence, house3Right, house3Left, house2Right
            , house2Left, house1Right, house1Left, char1, rFlower1,rFlower2,rFlower3,rFlower4,rFlower6,rFlower5,rFlute;

    Item  iFlower1,iFlower2,iFlower3,iFlower4,iFlower6,iFlower5,iFlute;
    public RealWorld(int state,Hero player)throws SlickException{
        this.player = player;
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        font = new java.awt.Font("Century Gothic", java.awt.Font.BOLD,20);
        ttf = new TrueTypeFont(font, true);

        textbox = new Image("res/etc/textbox4.png");
        moreLeft = moreRight = moreUp = moreDown = true;
        realWorld = new Image("res/background/town.png");
        treetop1 = new Image("res/etc/treetop.png");
        treetop2 = new Image("res/etc/treetop.png");
        housetop = new Image("res/etc/housetop.png");

        String[] messages ={"The sweet scent of this flower is enticing", "Essence of white flower collected"};
        iFlower1 = new Item(messages,3);
        iFlower2 = new Item(messages,3);
        iFlower3 = new Item(messages,3);
        iFlower4 = new Item(messages,3);
        iFlower5 = new Item(messages,3);
        iFlower6 = new Item(messages,3);

        String[] messages6 ={"The grass looks strange now...", "You found the flute!"};
        iFlute = new Item(messages6,3);


        Image[] walkUp = {new Image("res/characters/hero/2.png"),new Image("res/characters/hero/11.png"),new Image("res/characters/hero/12.png")};
        Image[] walkLeft = {new Image("res/characters/hero/1.png"),new Image("res/characters/hero/9.png"),new Image("res/characters/hero/10.png")};
        Image[] walkRight = {new Image("res/characters/hero/R1.png"),new Image("res/characters/hero/R2.png"),new Image("res/characters/hero/R3.png")};
        Image[] walkDown = {new Image("res/characters/hero/0.png"),new Image("res/characters/hero/7.png"),new Image("res/characters/hero/8.png")};

        Image[] iSteadyDown = {new Image("res/characters/hero/0.png"),new Image("res/characters/hero/3.png"),new Image("res/characters/hero/4.png")};
        Image[] iSteadyUp = {new Image("res/characters/hero/2.png"),new Image("res/characters/hero/2.png"),new Image("res/characters/hero/2.png")};
        Image[] iSteadyLeft = {new Image("res/characters/hero/1.png"),new Image("res/characters/hero/5.png"),new Image("res/characters/hero/6.png")};
        Image[] iSteadyRight = {new Image("res/characters/hero/R1.png"),new Image("res/characters/hero/R1.png"),new Image("res/characters/hero/R1.png")};

        movingDown = new Animation(walkDown, 200);
        movingLeft = new Animation(walkLeft, 200);
        movingUp = new Animation(walkUp, 200);
        movingRight = new Animation(walkRight, 200);

        steadyRight = new Animation(iSteadyRight,duration2,true);
        steadyUp = new Animation(iSteadyUp,duration2,true);
        steadyLeft = new Animation(iSteadyLeft,duration2,true);
        steadyDown = new Animation(iSteadyDown,duration2,true);

        hero = steadyDown;

        char1 = new Rectangle (0,0,heroW,heroL);
        rHero = new Rectangle(shiftX,shiftY,heroW,heroL);

        transporthouse1 = new Rectangle(0,0,35f,5f);
        transporthouse2 = new Rectangle(0,0,55f,5f);
        transporthouse3 = new Rectangle(0,0,55f,5f);

        house1Left = new Rectangle(0,0,140,220f);
        house2Left = new Rectangle(0,0,230,280f);
        house3Left = new Rectangle(0,0,225,300f);
        helper1 = new Rectangle(0,0,140,3f);

        house1Right = new Rectangle(0,0,160,220f);
        house2Right = new Rectangle(0,0,300,280f);
        house3Right = new Rectangle(0,0,190,300f);
        helper2 = new Rectangle(0,0,140,3f);

        house4Left = new Rectangle(0,0,208,1);
        house4Right = new Rectangle(0,0,289,1);

        wallLeft = new Rectangle(0,0,420,64);
        wallRight = new Rectangle(0,0,1000,64);

        squareFence = new Rectangle (0,0,300,345);
        rFlower1 = new Rectangle (0,0,30,20);
        rFlower2 = new Rectangle (0,0,30,20);
        rFlower3 = new Rectangle (0,0,30,20);
        rFlower4 = new Rectangle (0,0,30,20);
        rFlower5 = new Rectangle (0,0,30,20);
        rFlower6 = new Rectangle (0,0,30,20);

        rFlute = new Rectangle (0,0,30,20);
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)  throws SlickException{
        realWorld.draw(heroPositionX-500,heroPositionY-1000,2.5f);
        hero.draw(shiftX,shiftY,heroW,heroL);

        //g.drawString("hero X position: "+heroPositionX+"\nhero Y position: "+heroPositionY,400,200);
        g.drawString("QUEST COUNTER: "+player.getCurrentQuest()+" ",300,300);
        rHero = new Rectangle(shiftX,shiftY,heroW,heroL);
        //g.draw(rHero);
        if(player.subquests[0]) {
            g.drawString("quest1 complete \n", 200, 200);
        }
        if(player.subquests[1]){
            g.drawString("quest2 complete\n", 200, 210);
        }
        if(player.subquests[2]){
            g.drawString("quest3 complete\n", 200, 220);
        }
        if(player.subquests[3]){
            g.drawString("quest4 complete\n", 200, 230);
        }
        if(player.subquests[4]){
            g.drawString("quest2 complete\n", 200, 240);
        }
        if(player.subquests[5]){
            g.drawString("quest3 complete\n", 200, 250);
        }
        transporthouse1.setLocation(heroPositionX+57.29f,heroPositionY+197.8f);
        transporthouse2.setLocation(heroPositionX+940.41f,heroPositionY+267);
        transporthouse3.setLocation(heroPositionX+144.79f,heroPositionY+678);

        house1Left.setLocation(heroPositionX-92f,heroPositionY-6.74f);
        house1Right.setLocation(heroPositionX+95f,heroPositionY-6.74f);
        helper1.setLocation(heroPositionX+1f,heroPositionY-6.74f);

        house2Left.setLocation(heroPositionX+709f,heroPositionY+76f);
        house2Right.setLocation(heroPositionX+1002f,heroPositionY+76f);

        house3Left.setLocation(heroPositionX-89f,heroPositionY+473f);
        house3Right.setLocation(heroPositionX+212.69f,heroPositionY+473);

        rFlower1.setLocation(heroPositionX+420f,heroPositionY+684f);
        rFlower2.setLocation(heroPositionX+903f,heroPositionY+920f);
        rFlower3.setLocation(heroPositionX+1061f,heroPositionY+568f);
        rFlower4.setLocation(heroPositionX+618f,heroPositionY+80f);
        rFlower5.setLocation(heroPositionX+213f,heroPositionY-119f);
        rFlower6.setLocation(heroPositionX+1053f,heroPositionY-165f);
        rFlute.setLocation(heroPositionX+491f,heroPositionY+876f);

        helper2.setLocation(heroPositionX+100f,heroPositionY+473f);

        house4Left.setLocation(heroPositionX+334f,heroPositionY-157f);
        house4Right.setLocation(heroPositionX+610f,heroPositionY-157);

        squareFence.setLocation(heroPositionX+941.00f,heroPositionY+634);

        wallRight.setLocation(heroPositionX+403.00f,heroPositionY+15);
        wallLeft.setLocation(heroPositionX-70f,heroPositionY+15);
        treetop1.draw(heroPositionX+695.95f,heroPositionY+874.20f,150,160);
        treetop2.draw(heroPositionX+418.89f,heroPositionY+911.20f,150,160);
        housetop.draw(heroPositionX+12.89f,heroPositionY+311.20f,318.5f,205.5f);

        /*g.draw(wallLeft);
        g.draw(wallRight);
        g.draw(squareFence);

        g.draw(transporthouse1);
        g.draw(transporthouse2);
        g.draw(transporthouse3);

        g.draw(house1Left);
        g.draw(house2Left);
        g.draw(house3Left);
        g.draw(helper1);
        g.draw(helper2);
        g.draw(house1Right);
        g.draw(house2Right);
        g.draw(house3Right);

        g.draw(rFlower1);
        g.draw(rFlower2);
        g.draw(rFlower3);
        g.draw(rFlower4);
        g.draw(rFlower5);
        g.draw(rFlower6);*/

        //g.draw(rFlute);

        //g.draw(house4Right);
        //g.draw(house4Left);

        //g.draw(squareFence);
        //g.drawString("HERO NAME: "+player.getName()+" ",100,100);

        if(interaction && collidesanything()) {
            textbox.draw(0,295,720,100);
            if (rHero.intersects(rFlower1)) {
                if (player.getCurrentQuest() == 3 && !player.subquests[0]) {
                    ttf.drawString(28.0f, 310.83f, iFlower1.Interact(1), Color.black);
                } else {
                    ttf.drawString(28.0f, 310.83f, iFlower1.Interact(-2), Color.black);
                }
            }
            if (rHero.intersects(rFlower2)) {
                if (player.getCurrentQuest() == 3 && !player.subquests[1]) {
                    ttf.drawString(28.0f, 310.83f, iFlower2.Interact(1), Color.black);
                } else {
                    ttf.drawString(28.0f, 310.83f, iFlower2.Interact(-2), Color.black);
                }
            }
            if (rHero.intersects(rFlower3)) {
                if (player.getCurrentQuest() == 3 && !player.subquests[2]) {
                    ttf.drawString(28.0f, 310.83f, iFlower3.Interact(1), Color.black);
                } else {
                    ttf.drawString(28.0f, 310.83f, iFlower3.Interact(-2), Color.black);
                }
            }
            if (rHero.intersects(rFlower4)) {
                if (player.getCurrentQuest() == 3 && !player.subquests[3]) {
                    ttf.drawString(28.0f, 310.83f, iFlower4.Interact(1), Color.black);
                } else {
                    ttf.drawString(28.0f, 310.83f, iFlower4.Interact(-2), Color.black);
                }
            }
            if (rHero.intersects(rFlower5)) {
                if (player.getCurrentQuest() == 3 && !player.subquests[4]) {
                    ttf.drawString(28.0f, 310.83f, iFlower5.Interact(1), Color.black);
                } else {
                    ttf.drawString(28.0f, 310.83f, iFlower5.Interact(-2), Color.black);
                }
            }
            if (rHero.intersects(rFlower6)) {
                if (player.getCurrentQuest() == 3 && !player.subquests[5]) {
                    ttf.drawString(28.0f, 310.83f, iFlower6.Interact(1), Color.black);
                } else {
                    ttf.drawString(28.0f, 310.83f, iFlower6.Interact(-2), Color.black);
                }

            }if (rHero.intersects(rFlute)) {
                if (player.getCurrentQuest() == 5 && !player.subquests[0]) {
                    ttf.drawString(28.0f, 310.83f, iFlower3.Interact(1), Color.black);
                } else {
                    ttf.drawString(28.0f, 310.83f, iFlower3.Interact(-2), Color.black);
                }
            }

        }
    }
    public void update(GameContainer gc, StateBasedGame sbg, int delta)  throws SlickException{
        Input input = gc.getInput();
        if(!interaction) {
            if (input.isKeyDown(input.KEY_UP)) {//TODO IBALIK SILA UG 1.5
                direction = 1;
                movingUp.update(delta);
                hero = movingUp;
                heroPositionY += delta * 0.095f;
           /*if(heroPositionY > 401.12 ||!moreUp){
                heroPositionY -= 0.3f;
            }*/
            } else if (input.isKeyDown(input.KEY_DOWN)) {
                direction = 3;
                movingDown.update(delta);
                hero = movingDown;
                heroPositionY -= delta * 0.095f;
            /*if(heroPositionY < -705.17 || !moreDown){
                heroPositionY += 0.3f;
            }*/
            } else if (input.isKeyDown(input.KEY_RIGHT)) {
                direction = 2;
                movingRight.update(delta);
                hero = movingRight;
                heroPositionX -= delta * 0.095f;
            /*if(heroPositionX < -732.32 ||!moreRight){
                heroPositionX += 0.3f;
            }*/
            } else if (input.isKeyDown(input.KEY_LEFT)) {
                direction = 4;
                movingLeft.update(delta);
                hero = movingLeft;
                heroPositionX += delta * 0.095f;
            /*if(heroPositionX > 382.61 || !moreLeft){
                heroPositionX -= 0.3f;
            }*/
            }
        }
        if(input.isKeyPressed(input.KEY_I)){
            if (interaction) {
                if(rHero.intersects(rFlower1)){
                    if(player.getCurrentQuest() == 3) {
                        if (iFlower1.ctrMessages >= iFlower1.messages.length - 1) {//stop
                            interaction = false;
                            player.subquests[0] = true;
                        } else {
                            iFlower1.ctrMessages++;
                        }
                    }else{
                        interaction = false;
                    }
                }if(rHero.intersects(rFlower2)) {
                    if (player.getCurrentQuest() == 3) {
                        if (iFlower2.ctrMessages >= iFlower2.messages.length - 1) {//stop
                            interaction = false;
                            player.subquests[1] = true;
                        } else {
                            iFlower2.ctrMessages++;
                        }
                    } else {
                        interaction = false;
                    }
                }if(rHero.intersects(rFlower3)){
                    if(player.getCurrentQuest() == 3) {
                        if (iFlower3.ctrMessages >= iFlower3.messages.length - 1) {//stop
                            interaction = false;
                            player.subquests[2] = true;
                        } else {
                            iFlower3.ctrMessages++;
                        }
                    }else{
                        interaction = false;
                    }
                }
                else if(rHero.intersects(rFlower4)){
                    if(player.getCurrentQuest() == 3) {
                        if (iFlower4.ctrMessages >= iFlower4.messages.length - 1) {//stop
                            interaction = false;
                            player.subquests[3] = true;
                        } else {
                            iFlower4.ctrMessages++;
                        }
                    }else{
                        interaction = false;
                    }
                }
                else if(rHero.intersects(rFlower5)){
                    if(player.getCurrentQuest() == 3) {
                        if (iFlower5.ctrMessages >= iFlower5.messages.length - 1) {//stop
                            interaction = false;
                            player.subquests[4] = true;
                        } else {
                            iFlower5.ctrMessages++;
                        }
                    }else{
                        interaction = false;
                    }
                }
                else if(rHero.intersects(rFlower6)){
                    if(player.getCurrentQuest() == 3) {
                        if (iFlower6.ctrMessages >= iFlower6.messages.length - 1) {//stop
                            interaction = false;
                            player.subquests[5] = true;
                        } else {
                            iFlower6.ctrMessages++;
                        }
                    }else{
                        interaction = false;
                    }
                }
            }else if(rHero.intersects(rFlute)) {
                if (player.getCurrentQuest() == 5) {
                    if (iFlute.ctrMessages >= iFlute.messages.length - 1) {//stop
                        interaction = false;
                        player.subquests[0] = true;
                    } else {
                        iFlute.ctrMessages++;
                    }
                } else {
                    interaction = false;
                }
            }
            else {
                if (collidesanything()) {
                    interaction = true;
                }
            }
        }
        if(!(input.isKeyDown(input.KEY_UP)) &&!( input.isKeyDown(input.KEY_DOWN)) &&!(input.isKeyDown(input.KEY_LEFT))&&!(input.isKeyDown(input.KEY_RIGHT))){
            if(direction == 1){hero =steadyUp;}
            else if(direction == 2){hero =steadyRight;}
            else if(direction == 3){hero =steadyDown;}
            else if(direction == 4){hero =steadyLeft;}
        }

        if(rHero.intersects(helper1) ||rHero.intersects(helper2) ||rHero.intersects(house1Left) ||rHero.intersects(house1Right) ||rHero.intersects(house2Left) ||rHero.intersects(house2Right) || rHero.intersects(house3Left)|| rHero.intersects(house3Right)|| rHero.intersects(house4Left)|| rHero.intersects(house4Right)|| rHero.intersects(wallRight)|| rHero.intersects(wallLeft)|| rHero.intersects(squareFence)){
            if(rHero.intersects(house1Right) && rHero.intersects(wallLeft)){
                heroPositionX -=2;
                if(direction ==1) {
                    heroPositionY -= 2;
                    moreLeft = moreUp = false;
                }
                else if(direction ==3){
                    heroPositionY += 2;
                    moreLeft = moreDown = false;
                }
            }
            else if(rHero.intersects(house2Left) && rHero.intersects(wallRight)){
                heroPositionX +=2;
                heroPositionY -=2;
                moreLeft = moreUp = false;
            }
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
        }
        else{
            moreLeft = moreRight = moreUp = moreDown = true;
        }

        if(rHero.intersects(transporthouse1)){
            sbg.enterState(3);
            heroPositionY-=7;
        }
        else if(rHero.intersects(transporthouse2)){
            sbg.enterState(4);
            heroPositionY-=7;
        }
        else if(rHero.intersects(transporthouse3)){
            sbg.enterState(5);
            heroPositionY-=7;
        }
    }

    public boolean collidesanything(){
        if(rHero.intersects(rFlute) ||rHero.intersects(rFlower1) ||rHero.intersects(rFlower2) || rHero.intersects(rFlower3)|| rHero.intersects(rFlower4)||rHero.intersects(rFlower5) ||rHero.intersects(rFlower6) ||rHero.intersects(rFlower6)){
            return true;
        }
        else{
            return false;
        }
    }

    public int getID(){
        return 2;
    }
}

