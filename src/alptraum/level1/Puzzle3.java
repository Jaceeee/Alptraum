package alptraum.level1;

import java.awt.Font;

import alptraum.Camera;
import alptraum.Dog;
import alptraum.Hero;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * Created by Loewe on 11/14/2016.
 *
 */

public class Puzzle3 extends BasicGameState {
    Animation hero,steady, movingUp, movingDown,movingRight, movingLeft;
    private int[] duration = {200,200,200}; // an animation is a series of frames(milliseconds)
    private int[] duration2 = {1000,100,100};
    private int[] duration3 = {200,200};
    private float heroPositionX = 2f;// keep track of position of hero
    private float heroPositionY = 10f;
    private Hero player;
    private Animation dog;
    private Dog andog;
    private Animation ghost;
    private Image textbox;
    private Ghost rensarrews;
    TrueTypeFont ttf;
    Font font;
    private float ghostposX = 5f, ghostposY = 2f;
    private boolean start,dogdown,dogup,dogmove;
    private boolean interaction,accident;
    private Rectangle ghost1;
    private Rectangle rHero;
    private TiledMap cave3;
    private Camera camera;
    private boolean[][] blocked;
    private boolean moreup, moredown, moreright, moreleft;
    private Image meats;
    private Image cake1,arrow;
    private Image hp;
    private boolean Ipress;
    private static final int NUMBEROFLAYERS = 5;
    private static final float SPEED = 0.0025f;
    private static final int TILESIZE = 32;
    private static final float MEATSPEED = 0.0025f;
    private float meat1X = 4f, meat2X = 7f, meat3X = 11f, meat4X = 15f, meat5X = 8f;
    private float meat1Y = 4f, meat2Y = 6f, meat3Y = 3f, meat4Y = 3f, meat5Y = 8f;
    private float cake1X = 14f;
    private float cake1Y = 3f;
    private  float heroW = 20.0f;
    private  float heroL = 27.0f;
    private Rectangle meat1,meat2,meat3,meat4,meat5,cake,rock;
    private int hit;
    private boolean diagup1, diagdown1,diagdown2,diagup2;
    private boolean up1,down1, up2,down2, movenow;
    private Image apple;

    public Puzzle3(int state, Hero player) throws SlickException{
        this.player = player;
    }

    private void initializeBlocked() {
        for (int l = 0; l < NUMBEROFLAYERS; l++) {
            String layerValue = cave3.getLayerProperty(l, "blocked", "false");
            if (layerValue.equals("true")) {
                for (int c = 0; c < cave3.getHeight(); c++) {
                    for(int r = 0; r < cave3.getWidth(); r++) {
                        if(cave3.getTileId(r, c, l) != 0) {
                            blocked[r][c] = true;
                        }
                    }
                }
            }
        }
    }

    private boolean isBlocked(float x, float y) {
        if((x < 0 ) || x >= TILESIZE || y < 0 || y >= TILESIZE){
            return true;
        }
        int xBlock = (int) x /* TILEWIDTH*/;
        int yBlock = (int) y /* TILEHEIGHT*/;
        return blocked[xBlock][yBlock];
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        rock = new Rectangle(0,0,0,0);
        meat1 = new Rectangle(0,0,0,0);
        meat2 = new Rectangle(0,0,0,0);
        meat3 = new Rectangle(0,0,0,0);
        meat4 = new Rectangle(0,0,0,0);
        meat5 = new Rectangle(0,0,0,0);
        cake = new Rectangle(0,0,0,0);
        rHero = new Rectangle(0,15,0,0);
        ghost1 = new Rectangle(0,0,0,0);
        cave3 = new TiledMap("res/background/puzzle1/Cave2Part1Final.tmx", "res/background/puzzle1");
        moreup = moredown = moreright = moreleft = true;
        blocked = new boolean[cave3.getWidth()][cave3.getHeight()];
        initializeBlocked();
        andog = new Dog(0,0);
        dogup = dogmove = false;
        dogdown = false;
        font = new Font("Century Gothic",Font.BOLD,20);
        ttf = new TrueTypeFont(font,true);
        movenow = start = true;
        interaction  = false;
        hit = 3000;
        rensarrews = new Ghost(ghostposX,ghostposY);
        rensarrews.setCtrMessages(16);
        apple = new Image("res/background/puzzle1/FOOD/Apples.png");
        textbox = new Image("res/etc/textbox1.png");
        meats = new Image("res/background/puzzle1/FOOD/Meat.png");
        hp = new Image("res/background/puzzle1/FOOD/heart.png");
        cake1 = new Image("res/background/puzzle1/FOOD/Cake.png");
        arrow = new Image("res/background/puzzle1/FOOD/Arrow.png");
        Image[] dogmove = {new Image("res/characters/Dog/16.png"),new Image("res/characters/Dog/21.png")};
        Image[] ghostSteady = {new Image("res/characters/Ghost/0.png"),new Image("res/characters/Ghost/2.png"),new Image("res/characters/Ghost/left.png")};
        Image[] heroSteady = {new Image("res/characters/hero/0.png"),new Image("res/characters/hero/3.png"),new Image("res/characters/hero/4.png")};
        Image[] walkUp = {new Image("res/characters/hero/2.png"),new Image("res/characters/hero/11.png"),new Image("res/characters/hero/12.png")};
        Image[] walkLeft = {new Image("res/characters/hero/1.png"),new Image("res/characters/hero/9.png"),new Image("res/characters/hero/10.png")};
        Image[] walkRight = {new Image("res/characters/hero/R1.png"),new Image("res/characters/hero/R2.png"),new Image("res/characters/hero/R3.png")};
        Image[] walkDown = {new Image("res/characters/hero/0.png"),new Image("res/characters/hero/7.png"),new Image("res/characters/hero/8.png")};
        diagup1 = down1 = diagdown2 = down2 = true;
        diagdown1 = up1 = diagup2 = up2 = false;

        dog = new Animation(dogmove,duration3,true);
        ghost = new Animation(ghostSteady,duration,true);
        movingRight = new Animation(walkRight,duration,true);
        movingUp = new Animation(walkUp,duration,true);
        movingLeft = new Animation(walkLeft,duration,true);
        movingDown = new Animation(walkDown,duration,true);
        steady = new Animation(heroSteady,duration2, true);
        hero = steady;
        camera = new Camera(container,cave3);

    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        camera.translateGraphics();
        camera.drawMap(0);
        //camera.drawMap(1);
        camera.drawMap(2);
        camera.drawMap(3);
        //camera.drawMap(4);

        ghost.draw(rensarrews.ghostposX * 32, rensarrews.ghostposY * 32);
        hero.draw(heroPositionX * 32,heroPositionY * 32);
        meats.draw(meat1X * 32, meat1Y * 32,25,25);
        meats.draw(meat2X * 32, meat2Y * 32,25,25);
        meats.draw(meat3X * 32, meat3Y * 32,25,25);
        meats.draw(meat4X * 32, meat4Y * 32,25,25);
        meats.draw(meat5X * 32, meat5Y * 32,25,25);
        cake1.draw(cake1X * 32, cake1Y * 32,25,25);
        arrow.draw(19 * TILESIZE, 8 * TILESIZE);
        g.drawString("hero X position: "+heroPositionX+"\nhero Y position: "+heroPositionY,400,200);
        g.drawString("HERO NAME: "+player.getName()+" ",100,100);
        g.drawString("HEALTH: ", 1 * TILESIZE, 11.8f * TILESIZE);

        rock = new Rectangle(18f * TILESIZE, 8 * TILESIZE,25,25);
        apple.draw(18f * TILESIZE, 8 * TILESIZE,25,25);
        meat1 = new Rectangle(meat1X * 32, meat1Y * 32,25,25);
        meat2 = new Rectangle(meat2X * 32, meat2Y * 32,25,25);
        meat3 = new Rectangle(meat3X * 32, meat3Y * 32,25,25);
        meat4 = new Rectangle(meat4X * 32, meat4Y * 32,25,25);
        meat5 = new Rectangle(meat5X * 32, meat5Y * 32,25,25);
        cake = new Rectangle(cake1X * 32, cake1Y * 32,25,25);
        ghost1 = new Rectangle(rensarrews.ghostposX * 32, (rensarrews.ghostposY + 0.4f)* 32,25,25);
        rHero = new Rectangle(heroPositionX * TILESIZE - 1,heroPositionY * TILESIZE,heroW,heroL);

        g.draw(rock);
        g.draw(meat1);
        g.draw(meat2);
        g.draw(meat3);
        g.draw(meat4);
        g.draw(meat5);
        g.draw(cake);
        g.draw(rHero);
        g.draw(ghost1);

        if(player.getHp() == 5){ hp.draw(7 * TILESIZE,12 * TILESIZE,20,15);}
        if(player.getHp() >= 4) { hp.draw(6 * TILESIZE, 12 * TILESIZE, 20, 15); }
        if(player.getHp() >= 3){ hp.draw(5 * TILESIZE,12 * TILESIZE,20,15); }
        if(player.getHp() >= 2){ hp.draw(4 * TILESIZE,12 * TILESIZE,20,15);}
        if(player.getHp() >= 1){ hp.draw(3 * TILESIZE,12 * TILESIZE,20,15);}

        if(start){
            if(rensarrews.getMessages() <= 24){
                if(rensarrews.getMessages() == 16 || rensarrews.getMessages() == 18 || rensarrews.getMessages() == 23) {
                    rensarrews.getImage(3).draw(19 * TILESIZE, 6 * TILESIZE, 2.75f);

                } else if(rensarrews.getMessages() == 19){
                    rensarrews.getImage(2).draw(19 * TILESIZE, 6 * TILESIZE, 2.75f);

                } else {
                    rensarrews.getImage(0).draw(19 * TILESIZE, 6 * TILESIZE, 2.75f);
                }
                textbox.draw(0, 9 * TILESIZE, 720, 115);
                ttf.drawString(3 * TILESIZE, 10 * TILESIZE, rensarrews.Interact(rensarrews.getMessages()), org.newdawn.slick.Color.white);
            } else{
                rensarrews.setCtrMessages(24);
                start = false;
            }
        }
        if(interaction){
            if(!dogmove && rensarrews.getMessages() <= 27) {
                if (rensarrews.getMessages() == 25)
                    rensarrews.getImage(4).draw(19 * TILESIZE, 6 * TILESIZE, 2.75f);
                else
                    rensarrews.getImage(0).draw(19 * TILESIZE, 6 * TILESIZE, 2.75f);

                textbox.draw(0, 9 * TILESIZE, 720, 115);
                ttf.drawString(3 * TILESIZE, 10 * TILESIZE, rensarrews.Interact(rensarrews.getMessages()), org.newdawn.slick.Color.white);

            } else if(dogmove && rensarrews.getMessages() <= 34){
                    rensarrews.getImage(5).draw(19 * TILESIZE, 6 * TILESIZE, 2.75f);

                textbox.draw(0, 9 * TILESIZE, 720, 115);
                ttf.drawString(3 * TILESIZE, 10 * TILESIZE, rensarrews.Interact(rensarrews.getMessages()), org.newdawn.slick.Color.white);

            } else {
                movenow = true;
                interaction = false;
            }
        }

        if(accident){
            if(andog.dogX < 23) {
                dogmove = true;
                dog.draw(andog.dogX * TILESIZE, andog.dogY * 32);
            } else {
                //Ipress = false;
                dogdown = false;
                dogup = true;
                andog.dogX = 0f;
                andog.dogY = 0f;
                accident = false;
                movenow = true;
            }
        }
    }

    private void meatMove(int delta){
        //meat1
        if((int) meat1Y == 4 && (int) meat1X != 8){ meat1X += delta * MEATSPEED; } //moveright
        else if((int) meat1X == 8 && (int) meat1Y != 6){ meat1Y += delta * MEATSPEED; } //movedown
        else if((int) meat1Y == 6 && (int) meat1X != 3){ meat1X -= delta * MEATSPEED; } //moveleft
        else if((int) meat1X == 3 && (int) meat1Y != 4){ meat1Y -= delta * MEATSPEED; } //moveup
        //meat2
        if(diagup1 && (int) meat2X != 12 && (int) meat2Y != 1){
            meat2X += delta * MEATSPEED;
            meat2Y -= delta * MEATSPEED;
            diagdown1 = true;
        } else if(diagdown1 && (int) meat2X != 7 && (int) meat2Y != 6){
            meat2Y += delta * MEATSPEED;
            meat2X -= delta * MEATSPEED;
            diagup1 = false;
        } else{ diagup1 = true; }

        //meat3
        if(down1 && (int) meat3Y != 9){
            //movedown
            meat3Y += delta * MEATSPEED;
            up1 = true;
        } else if(up1 && (int) meat3Y != 2){
            down1 = false;
            meat3Y -= delta * MEATSPEED;
            //moveup
        } else {
            down1 = true;
        }
        //meat4
        if((int) meat4Y == 3 && (int) meat4X != 20){ meat4X += delta * MEATSPEED; } //move right
        else if((int) meat4X == 20 && (int)meat4Y != 8){ meat4Y+= delta * MEATSPEED; } //movedown
        else if((int) meat4Y == 8 && (int) meat4X != 14){ meat4X -= delta * MEATSPEED; } //moveleft
        else if((int) meat4X == 14 && (int) meat4Y != 3){ meat4Y -= delta * MEATSPEED; } //moveup

        //meat5
        if(diagdown2 && (int) meat5Y != 12 && (int) meat5X != 3){
            meat5Y += delta * MEATSPEED;
            meat5X -= delta * MEATSPEED;
            diagup2 = true;
        } else if(diagup2 && (int) meat5X != 9 && (int) meat5Y != 8) {
            meat5X += delta * MEATSPEED;
            meat5Y -= delta * MEATSPEED;
            diagdown2 = false;
        } else{
            diagdown2 = true;
        }
        //cake1
        if(down2 && (int) cake1Y != 7){
            //movedown
            cake1Y += delta * MEATSPEED;
            up2 = true;
        } else if(up2 && (int) cake1Y != 2){
            cake1Y -= delta * MEATSPEED;
            down2 = false;
        } else {
            down2 = true;
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input input = gc.getInput();
        if(player.getHp() == 0){
            sbg.enterState(8, new FadeOutTransition(org.newdawn.slick.Color.white, 500), new FadeInTransition(org.newdawn.slick.Color.white, 500));
            player.setHp(5);
            heroPositionX = 2f;
            heroPositionY = 10f;
        }

        if(!accident) {
            meatMove(delta);
        }
        if(start) {
            if (input.isKeyPressed(Input.KEY_I))
                rensarrews.addMessage();
        }

        else{
            if(rHero.intersects(ghost1)){
                if(input.isKeyPressed(Input.KEY_I)){
                    if(!dogmove){
                        if(rensarrews.getMessages() > 27){
                            rensarrews.setCtrMessages(24);
                        }
                    } else {
                        if(rensarrews.getMessages() > 34){
                            rensarrews.setCtrMessages(31);
                        }
                    }
                    movenow = false;
                    interaction = true;
                    rensarrews.addMessage();
                }
            }
            if(movenow && rHero.intersects(rock)){
                if(input.isKeyPressed(Input.KEY_I)){
                    movenow = false;
                    dogdown = true;
                    accident = true;
                    rensarrews.setCtrMessages(31);
                }
            }

            if(accident){
                dog.update(delta);
                if((int) andog.dogY == 10 && (int) andog.dogX == 10){
                    dogdown = false;
                    dogup = true;
                }
                if(dogdown) {
                    andog.dogY += delta * MEATSPEED;
                } else {
                    andog.dogY -= delta * MEATSPEED;
                }
                andog.dogX += delta * MEATSPEED;
            }

            if(movenow){

                if(input.isKeyDown(Input.KEY_UP)) {
                    movingUp.update(delta);
                    hero = movingUp;
                    if(!isBlocked(heroPositionX, heroPositionY + delta * SPEED - 0.1f)){ heroPositionY -= delta * SPEED; }
                }
                else if (input.isKeyDown(Input.KEY_DOWN)) {
                    movingDown.update(delta);
                    hero = movingDown;
                    if(!isBlocked(heroPositionX, heroPositionY + delta * SPEED + 0.1f)){ heroPositionY += delta * SPEED; }

                } else if (input.isKeyDown(Input.KEY_LEFT)) {
                    movingLeft.update(delta);
                    hero = movingLeft;
                    if (!isBlocked(heroPositionX - delta * SPEED - 0.1f, heroPositionY )) { heroPositionX -=  delta * SPEED; }

                } else if (input.isKeyDown(Input.KEY_RIGHT)) {
                    movingRight.update(delta);
                    hero = movingRight;
                    if (!isBlocked(heroPositionX + delta * SPEED + 0.6f, heroPositionY)){ heroPositionX += delta * SPEED; }

                } else {
                    hero = steady;
                }

                if(hit == 3000 && (rHero.intersects(meat1) || rHero.intersects(meat2) || rHero.intersects(meat3) ||
                        rHero.intersects(meat4)  || rHero.intersects(meat5) || rHero.intersects(cake))){
                    hit--;
                    player.minusHp();
                }
                if(hit == 0){
                    hit = 3000;
                } else if(hit < 3000 && hit != 0){
                    hit--;
                }

                if(!(input.isKeyDown(input.KEY_UP)) &&!( input.isKeyDown(input.KEY_DOWN)) &&!(input.isKeyDown(input.KEY_LEFT))&&!(input.isKeyDown(input.KEY_RIGHT))){
                    hero = steady;
                }
                if((int) heroPositionX == 0 && ((int) heroPositionY >= 9 && (int) heroPositionY <= 11))
                    sbg.enterState(7);

                if((int) heroPositionY == 0 && ((int) heroPositionX >= 12 && (int) heroPositionX <= 14))
                    sbg.enterState(9);
            }
        }
    }

    @Override
    public int getID(){
        return 8;
    }
}
