package alptraum;

import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import java.awt.Font;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.*;
public class House1 extends BasicGameState{
    Animation hero,steadyDown,steadyLeft,steadyRight,steadyUp, movingUp, movingDown,movingRight, movingLeft;
    boolean interaction, moreLeft, moreRight,moreUp,moreDown,subquest1,subquest2,subquest3,subquest4;
    Item  itable3, ibed1, ibed2, icabinet, ishelf, itable1, itable2, ipot1, ipot2, iwindow1, iwindow2, iwindow3, iwindow4;
    Char1 schnitzel;
    Hero player;
    Font font;
    TrueTypeFont ttf;
    Image house1,char1,textbox;
    int[] duration = {200,200,200}; // an animation is a series of frames(milliseconds)
    int[] duration2 = {1000,100,100};
    float heroPositionX = 0;// keep track of position of hero
    float heroPositionY = 0;
    float heroW = 30.0f;
    float heroL = 45.0f;
    float shiftX = heroPositionX + 346.93f;
    float shiftY = heroPositionY + 312.79f;
    int direction;
    float char1PositionX = 349.17f;
    float char1PositionY = 120f;

    Rectangle wall1 ,wall2, table3, rHero,rChar1, transportRealWorld,bed1,bed2,cabinet,shelf,table1,table2,pot1,pot2,window1,window2,window3,window4;

    public House1(int state,Hero player)throws SlickException{
        this.player = player;
    }
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

        subquest1 = subquest2 = subquest3 = subquest4 = false;
        String[] messages4 ={"You see some faint glimmer withing the flowers"," You reach your hand to take it..",
                "Oh, It is a feather"};
        itable3 =new Item(messages4,1);

        itable2 =new Item("Mhm.. The smell of freshly picked flowers");
        String[] messages ={"The bed feels soft","Oh, but I feel something sharp inside","It looks like a feather",
                "This must be that feather she's talking about.."," I wonder where the rest of her wings are"};
        ibed1 = new Item(messages,1);
        ibed2 =  new Item("The bed feels soft");
        icabinet =  new Item("Mom told me to respect people inside of the closet...I wonder if this is what she meant");
        String[] messages3 ={"Wow, there are so many books here","Oohh, a book on Programming",
                " Is programming a form of witchcraft?", "What's this thing sticking out?"," It is a feather!"};
        ishelf =  new Item(messages3, 1);
        itable1 = new Item("I shouldn't disarrange this. The progarammers..err..Angel would get mad");
        ipot1 = ipot2 =  new Item("This pot is empty");
        String[] messages2 ={"The view outside is pleasant","The majestic light shines through the window panes",
                "You see an object stuck between the sill ", "You pull it out"," It is a feather!"};
        iwindow4 =  new Item(messages2,1);
        iwindow1 = iwindow2 = iwindow3 = new Item("The view outside is pleasant");

        schnitzel = new Char1();
        textbox = new Image("res/etc/textbox4.png");
        font = new Font("Century Gothic",Font.BOLD,20);
        ttf = new TrueTypeFont(font, true);
        direction = 3;

        interaction = false;
        house1 = new Image("res/background/house1.png");
        char1 = new Image("res/characters/char1/10.png");
        rHero = new Rectangle(shiftX,shiftY,heroW,heroL);

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

        boolean moreLeft=moreRight=moreUp=moreDown;
        transportRealWorld= new Rectangle(310.4f,369.60f,90f,5f);
        bed1=new Rectangle(67.6f,87.89f,68.78f,58f);
        bed2=new Rectangle(178.3f,87.89f,68.78f,58f);
        shelf=new Rectangle(588f,19f,64f,65f);
        cabinet=new Rectangle(662f,19f,33f,65f);
        table1 = new Rectangle(98f,212f,190f,31f);
        table2 = new Rectangle(27.48f,82.39f,34f,1f);
        pot1= new Rectangle(661.28f,245.9f,32f,24f);
        pot2= new Rectangle(661.28f,278.39f,32f,24f);
        table3 = new Rectangle (661.28f,147f,32,35);
        window1 = new Rectangle(138f,55f,35f,1f);
        window2 = new Rectangle(286f,55f,35f,1f);
        window3 = new Rectangle(404f,55f,35f,1f);
        window4 = new Rectangle(507f,55f,35f,1f);
        rChar1 = new Rectangle(char1PositionX,char1PositionY,heroW,heroL/2);
        wall1 = new Rectangle(0f,341f,318f,1f);
        wall2 = new Rectangle(402f,341f,318f,1f);

    }
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)  throws SlickException{
        house1.draw(-5,-5,740,405);
        char1.draw(char1PositionX,char1PositionY,heroW,heroL+10);
        hero.draw(shiftX,shiftY,heroW,heroL);

        //g.drawString("hero X position: "+shiftX+"\nhero Y position: "+shiftY,400,200);
        //g.drawString("HERO NAME: "+player.getName()+" ",100,100);
        g.drawString("QUEST COUNTER: "+player.getCurrentQuest()+" ",300,300);
        if(subquest1) {
            g.drawString("quest1 complete \n", 200, 200);
        }
        if(subquest2){
            g.drawString("quest2 complete\n", 200, 210);
        }
        if(subquest3){
            g.drawString("quest3 complete\n", 200, 220);
        }
        if(subquest4){
            g.drawString("quest4 complete\n", 200, 230);
        }
        rHero = new Rectangle(shiftX,shiftY,heroW,heroL);

        /*g.draw(rChar1);
        g.draw(rHero);
        g.draw(transportRealWorld);
        g.draw(bed1);
        g.draw(bed2);
        g.draw(shelf);
        g.draw(cabinet);
        g.draw(table1);
        g.draw(table2);
        g.draw(pot1);
        g.draw(pot2);
        g.draw(table3);
        g.draw(window1);
        g.draw(window2);
        g.draw(window3);
        g.draw(window4);
        g.draw(wall1);
        g.draw(wall2);*/

        if(interaction && collidesanything()){
            textbox.draw(0,295,720,100);
            if(rHero.intersects(rChar1)) {
                ttf.drawString(28.0f, 310.83f, schnitzel.Interact(-2), Color.black);
                Image temp;

                if (schnitzel.getImage(schnitzel.images[schnitzel.ctrMessages]) == 0) {
                    temp = new Image("res/characters/char1/0.png");
                    temp.draw(556, 150, 120f, 152f);
                } else if (schnitzel.getImage(schnitzel.images[schnitzel.ctrMessages]) == 1) {
                    temp = new Image("res/characters/char1/1.png");
                    temp.draw(556, 150, 120f, 152f);
                } else if (schnitzel.getImage(schnitzel.images[schnitzel.ctrMessages]) == 2) {
                    temp = new Image("res/characters/char1/2.png");
                    temp.draw(556, 150, 120f, 152f);
                } else if (schnitzel.getImage(schnitzel.images[schnitzel.ctrMessages]) == 3) {
                    temp = new Image("res/characters/char1/3.png");
                    temp.draw(556, 150, 120f, 152f);
                } else if (schnitzel.getImage(schnitzel.images[schnitzel.ctrMessages]) == 4) {
                    temp = new Image("res/characters/char1/4.png");
                    temp.draw(556, 150, 120f, 152f);
                }
            }
            else if(rHero.intersects(bed1)) {
                if(player.getCurrentQuest() == 1  && !subquest1){
                    ttf.drawString(28.0f, 310.83f, ibed1.Interact(1), Color.black);
                }
                else {
                    ttf.drawString(28.0f, 310.83f, ibed1.Interact(-2), Color.black);
                }
            }
            else if(rHero.intersects(bed2)) { ttf.drawString(28.0f, 310.83f, ibed2.Interact(-2), Color.black); }
            else if(rHero.intersects(table1)) { ttf.drawString(28.0f, 310.83f, itable1.Interact(-2), Color.black); }
            else if(rHero.intersects(table2)) { ttf.drawString(28.0f, 310.83f, itable2.Interact(-2), Color.black); }
            else if(rHero.intersects(table3)) {
                if(player.getCurrentQuest() == 1  && !subquest4) {
                    ttf.drawString(28.0f, 310.83f, itable3.Interact(1), Color.black);
                }
                else {
                    ttf.drawString(28.0f, 310.83f, itable3.Interact(-2), Color.black);
                }
            }
            else if(rHero.intersects(cabinet)) { ttf.drawString(28.0f, 310.83f, icabinet.Interact(-2), Color.black); }
            else if(rHero.intersects(shelf)) {
                if(player.getCurrentQuest() == 1  && !subquest3){
                    ttf.drawString(28.0f, 310.83f, ishelf.Interact(1), Color.black);
                }
                else {
                    ttf.drawString(28.0f, 310.83f, ishelf.Interact(-2), Color.black);
                }
            }
            else if(rHero.intersects(pot1)) { ttf.drawString(28.0f, 310.83f, ipot2.Interact(-2), Color.black); }
            else if(rHero.intersects(pot2)) { ttf.drawString(28.0f, 310.83f, ipot1.Interact(-2), Color.black); }
            else if(rHero.intersects(window4)) {
                if(player.getCurrentQuest() == 1  && !subquest2){
                    ttf.drawString(28.0f, 310.83f, iwindow4.Interact(1), Color.black);
                }
                else {
                    ttf.drawString(28.0f, 310.83f, iwindow4.Interact(-2), Color.black);
                }
            }
            else if(rHero.intersects(window1) ||rHero.intersects(window2) || rHero.intersects(window3)|| rHero.intersects(window4)) { ttf.drawString(28.0f, 310.83f, iwindow1.Interact(2), Color.black); }
        }
    }
    public void update(GameContainer gc, StateBasedGame sbg, int delta)  throws SlickException{
        Input input = gc.getInput();

        if(!interaction) {
            if (input.isKeyDown(input.KEY_UP)) {
                direction =1;
                hero = movingUp;
                shiftY -= .15f;
                if (shiftY < 55f || !moreUp) {
                    shiftY += .15f;
                }
            } else if (input.isKeyDown(input.KEY_DOWN)) {
                direction = 3;
                hero = movingDown;
                shiftY += .15f;
                if (!moreDown) {
                    shiftY -= .15f;
                }
            } else if (input.isKeyDown(input.KEY_RIGHT)) {
                direction = 2;
                hero = movingRight;
                shiftX += .15f;
                if (shiftX > 666.9f || !moreRight) {
                    shiftX -= .15f;
                }
            } else if (input.isKeyDown(input.KEY_LEFT)) {
                direction = 4;
                hero = movingLeft;
                shiftX -= .15f;
                if (shiftX < 26.28f || !moreLeft) {
                    shiftX += .15f;
                }
            }
        }

        if(input.isKeyPressed(input.KEY_I)) {
            if (interaction) {//continue talking
                if (rHero.intersects(rChar1)) {//interaction intitially false
                    if (schnitzel.ctrMessages >= 5 && (subquest1 == false || subquest2 ==false || subquest3 == false || subquest4 ==false)) {//stop
                        if (schnitzel.ctrMessages == 5 && player.getCurrentQuest() == 0 ) {//setting quest
                            player.setQuest();
                            subquest1 = subquest2 = subquest3 = subquest4 = false;
                        }
                        interaction = false;
                    } else if(schnitzel.ctrMessages >= schnitzel.messages.length - 1){
                        interaction = false;
                    }
                    else {
                        schnitzel.ctrMessages++;
                        if(schnitzel.ctrMessages == schnitzel.messages.length - 1){
                            player.setQuest();
                            System.out.print(schnitzel.ctrMessages+"\n");
                        }
                    }
                }
                else if(rHero.intersects(bed1)){
                    if(player.getCurrentQuest() == 1) {
                        if (ibed1.ctrMessages >= ibed1.messages.length - 1) {//stop
                            interaction = false;
                            subquest1 = true;
                        } else {
                            ibed1.ctrMessages++;
                            System.out.println(ibed1.ctrMessages+" ");
                        }
                    }else{
                        interaction = false;
                    }
                }
                else if(rHero.intersects(window4)){
                    if(player.getCurrentQuest() == 1) {
                        if (iwindow4.ctrMessages >= iwindow4.messages.length - 1) {//stop
                            interaction = false;
                            subquest2 = true;
                        } else {
                            iwindow4.ctrMessages++;
                            System.out.println(iwindow4.ctrMessages+" ");
                        }
                    }else{
                        interaction = false;
                    }
                }
                else if(rHero.intersects(shelf)){
                    if(player.getCurrentQuest() == 1) {
                        if (ishelf.ctrMessages >= ishelf.messages.length - 1) {//stop
                            interaction = false;
                            subquest3 = true;
                        } else {
                            ishelf.ctrMessages++;
                            System.out.println(ishelf.ctrMessages+" ");
                        }
                    }else{
                        interaction = false;
                    }
                }

                else if(rHero.intersects(table3)){
                    if(player.getCurrentQuest() == 1) {
                        if (itable3.ctrMessages >= itable3.messages.length - 1) {//stop
                            interaction = false;
                            subquest4 = true;
                        } else {
                            itable3.ctrMessages++;
                            System.out.println(itable3.ctrMessages+" ");
                        }
                    }else{
                        interaction = false;
                    }
                } else{//other items
                    interaction = false;
                }
            } else {// initiate talking
                if(collidesanything()){
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

        if(rHero.intersects(transportRealWorld)){
            //player.setName("Mike in House1");
            sbg.enterState(2);
            shiftY-=7;
        }
        if(collidesanything()){
            if(rHero.intersects(bed1) && rHero.intersects(table2)){
                if(rHero.intersects(bed1)&& rHero.intersects(table2) && direction == 2) {
                    shiftX -=2;
                    shiftY +=2;
                    moreUp = moreRight = false;
                }
                else if(rHero.intersects(bed1)&& rHero.intersects(table2) && direction == 1) {
                    shiftY +=2;
                    shiftX -=2;
                    moreUp = moreRight = false;
                }
            }
            else if(rHero.intersects(bed1) && rHero.intersects(window1)){
                shiftX +=2;
                shiftY +=2;
                moreUp = moreLeft = false;
            }
            else if(rHero.intersects(bed2) && rHero.intersects(window1)){
                shiftX -=2;
                shiftY +=2;
                moreUp = moreRight = false;
            }
            else if(moreLeft == moreRight == moreUp == moreDown == true) {
                if (input.isKeyDown(input.KEY_UP)) {
                    moreUp = false;
                    moreDown = moreRight = moreLeft = true;
                } else if (input.isKeyDown(input.KEY_DOWN)) {
                    moreDown = false;
                    moreRight = moreLeft = moreUp = true;
                } else if (input.isKeyDown(input.KEY_RIGHT)) {
                    moreRight = false;
                    moreRight = false;
                    moreDown= moreLeft = moreUp = true;
                } else if (input.isKeyDown(input.KEY_LEFT)) {
                    moreLeft = false;
                    moreDown = moreRight = moreUp = true;
                }
            }
        }
        else{
            moreLeft = moreRight = moreUp = moreDown = true;
        }
    }
    public int getID(){
        //which screen
        return 3;
    }
    public boolean collidesanything(){
        if(rHero.intersects(window1) ||rHero.intersects(window2) || rHero.intersects(window3)|| rHero.intersects(window4)||rHero.intersects(table3) ||rHero.intersects(wall2) ||rHero.intersects(wall1) || rHero.intersects(rChar1) ||rHero.intersects(bed1) || rHero.intersects(bed2)|| rHero.intersects(cabinet)|| rHero.intersects(shelf)|| rHero.intersects(bed2)|| rHero.intersects(table1)|| rHero.intersects(table2)|| rHero.intersects(pot1)|| rHero.intersects(pot2)){
            return true;
        }
        else{
            return false;
        }
    }
}

