package alptraum.level2;

import alptraum.Hero;
import alptraum.Camera;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import java.awt.Font;

/**
 * Created by Loewe on 11/20/2016.
 */


/**
 * TODO there are other parts in the map that needs further collision
 * TODO check more...
 */

public class Puzzle5 extends BasicGameState {
    Animation hero,steady, movingUp, movingDown,movingRight, movingLeft;
    private static final int TILEWIDTH = 32;
    private static final int TILEHEIGHT = 32;

    int[] duration = {200,200,200}; // an animation is a series of frames(milliseconds)
    int[] duration2 = {1000,100,100};
    float heroPositionX = 4f;// keep track of position of hero
    float heroPositionY = 10f;

    private Ghost2 rensarrews;
    private Rectangle ghost1,angel1;
    Animation ghost;
    private boolean start;
    private boolean interact;
    TrueTypeFont ttf;
    Font font;
    Image textbox;
    private Rectangle rHero;

    boolean nowleft,nowright,nowUp,nowDown;
    private TiledMap futureroom1;
    private static final int NUMBEROFLAYERS = 6;
    private static final float SPEED = 0.00095f;
    boolean moreleft,moreright,moreUp,moreDown;

    Hero player;
    Camera camera;
    private boolean[][] blocked;

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        futureroom1 = new TiledMap("res/background/puzzle2/futureroom2.tmx");

        start = true;
        ghost1 = new Rectangle(0,0,0,0);
        rHero = new Rectangle(0,15,0,0);
        textbox = new Image("res/etc/textbox1.png");
        font = new Font("Century Gothic", Font.BOLD,20);
        ttf = new TrueTypeFont(font,true);

        rensarrews = new Ghost2(1f,6f);
        //rensarrews.setCtrMessages();
        Image[] ghostSteady = {new Image("res/characters/Ghost/0.png"),new Image("res/characters/Ghost/2.png"),new Image("res/characters/Ghost/left.png")};
        Image[] heroSteady = {new Image("res/characters/hero/0.png"),new Image("res/characters/hero/3.png"),new Image("res/characters/hero/4.png")};
        Image[] walkUp = {new Image("res/characters/hero/2.png"),new Image("res/characters/hero/11.png"),new Image("res/characters/hero/12.png")};
        Image[] walkLeft = {new Image("res/characters/hero/1.png"),new Image("res/characters/hero/9.png"),new Image("res/characters/hero/10.png")};
        Image[] walkRight = {new Image("res/characters/hero/R1.png"),new Image("res/characters/hero/R2.png"),new Image("res/characters/hero/R3.png")};
        Image[] walkDown = {new Image("res/characters/hero/0.png"),new Image("res/characters/hero/7.png"),new Image("res/characters/hero/8.png")};

        ghost = new Animation(ghostSteady,duration,true);
        movingRight = new Animation(walkRight,duration,true);
        movingUp = new Animation(walkUp,duration,true);
        movingLeft = new Animation(walkLeft,duration,true);
        movingDown = new Animation(walkDown,duration,true);
        steady = new Animation(heroSteady,duration2, true);
        hero = steady;
        moreleft = moreright = moreUp = moreDown = false;
        camera = new Camera(container,futureroom1);
        blocked = new boolean[futureroom1.getWidth()][futureroom1.getHeight()];
        initializeBlocked();
    }

    private void initializeBlocked() {
        int numTiles = 0;
        for (int l = 0; l < NUMBEROFLAYERS; l++) {
            String layerValue = futureroom1.getLayerProperty(l, "blocked", "false");
            if (layerValue.equals("true")) {
                for (int c = 0; c < futureroom1.getHeight(); c++) {
                    for(int r = 0; r < futureroom1.getWidth(); r++) {
                        if(futureroom1.getTileId(r, c, l) != 0) {
                            blocked[r][c] = true;
                            numTiles++;
                        }
                    }
                }
            }
        }
        System.out.println(numTiles + " mao ni si numTiles");
    }

    private boolean isBlocked(float x, float y) {
        int xBlock = (int) x /* TILEWIDTH*/;
        int yBlock = (int) y /* TILEHEIGHT*/;
        System.out.println(xBlock + " mao ni si Xblock");
        System.out.println(yBlock + " mao ni si yBlock");
        System.out.println(blocked[xBlock][yBlock] + " mao ni ilang values");
        return blocked[xBlock][yBlock];
    }
    public Puzzle5(int state, Hero player) throws SlickException {
        this.player = player;
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        camera.translateGraphics();
        camera.drawMap(0);
        camera.drawMap(1);
        camera.drawMap(2);
        camera.drawMap(3);
        camera.drawMap(4);
        camera.drawMap(5);

        hero.draw(heroPositionX * 32, heroPositionY * 32);
        ghost.draw(rensarrews.ghostposX * 32, rensarrews.ghostposY * 32);

        g.drawString("hero X position: "+heroPositionX+"\nhero Y position: "+heroPositionY,400,200);
        g.drawString("HERO NAME: "+player.getName()+" ",100,100);
        rHero = new Rectangle(heroPositionX * TILEWIDTH, heroPositionY * 32,25,25);
        if(start){
            if(rensarrews.getMessages() <= 7 ){
                if(rensarrews.getMessages() >= 2 && rensarrews.getMessages() <= 4){
                    rensarrews.getImage(1).draw(19 * TILEHEIGHT, 6 * TILEWIDTH, 2.75f);
                } else if(rensarrews.getMessages() == 6){
                    rensarrews.getImage(7).draw(19 * TILEHEIGHT, 6 * TILEWIDTH, 2.75f);
                } else {
                    rensarrews.getImage(0).draw(19 * TILEHEIGHT, 6 * TILEWIDTH, 2.75f);
                }

                textbox.draw(0, 9 * TILEHEIGHT, 720, 115);
                ttf.drawString(3 * TILEWIDTH, 10 * TILEHEIGHT, rensarrews.Interact(rensarrews.getMessages()), org.newdawn.slick.Color.white);
            } else {
                start = false;
            }
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input input = gc.getInput();
        if(start){
            if(input.isKeyPressed(Input.KEY_I)){
                rensarrews.addMessage();
            }
        }
        else {
            if (input.isKeyDown(Input.KEY_UP)) {
                System.out.println(delta + " OH YEAH");
                movingUp.update(delta);
                hero = movingUp;
                if (!isBlocked(heroPositionX, heroPositionY + delta * SPEED - 0.1f)) {
                    heroPositionY -= delta * SPEED;
                }
            } else if (input.isKeyDown(Input.KEY_DOWN)) {
                movingDown.update(delta);
                hero = movingDown;
                if (!isBlocked(heroPositionX, heroPositionY + delta * SPEED + 0.1f)) {
                    heroPositionY += delta * SPEED;
                }
            } else if (input.isKeyDown(Input.KEY_LEFT)) {
                movingLeft.update(delta);
                hero = movingLeft;
                if (!isBlocked(heroPositionX - delta * SPEED - 0.1f, heroPositionY)) {
                    heroPositionX -= delta * SPEED;
                }
            } else if (input.isKeyDown(Input.KEY_RIGHT)) {
                movingRight.update(delta);
                hero = movingRight;
                if (!isBlocked(heroPositionX + delta * SPEED + 0.4f, heroPositionY)) {
                    heroPositionX += delta * SPEED;
                }
            } else {
                hero = steady;
            }

            if ((int) heroPositionX == 21 && ((int) heroPositionY <= 2 && (int) heroPositionY >= 0)) {
                sbg.enterState(13);
            }
            teleport();
        }
    }

    private void teleport(){
        //teleport 1
        if ((int) heroPositionX == 1 && ((int) heroPositionY == 10 || heroPositionY >= 9.5)) {
            heroPositionX = 4f;
            heroPositionY = 1f;
            heroPositionY++;
        } else if ((int) heroPositionX == 4 && (int) heroPositionY == 1){
            heroPositionX = 1f;
            heroPositionY = 10f;
            heroPositionX++;
        }
        //teleport2
        if((int) heroPositionX == 3 && ((int) heroPositionY == 11 || heroPositionY >= 10.6)){
            heroPositionX = 4f;
            heroPositionY = 8f;
            heroPositionY++;
        } else if((int) heroPositionX == 4 && (heroPositionY <= 8.5 && heroPositionY >= 8.0)){  //subjected to change
            heroPositionX = 3f;
            heroPositionY = 11f;
            heroPositionY--;
           // check
        }

        //teleport3
        if((int) heroPositionX == 6 && (int) heroPositionY == 9){
            heroPositionX = 7;
            heroPositionY = 1;
            heroPositionX++;
        } else if((int) heroPositionX == 7 && (int) heroPositionY == 1){
            heroPositionX = 6;
            heroPositionY = 9;
            heroPositionX--;
            //check
        }
        //teleport4
        if((int) heroPositionX == 3 && (heroPositionY <= 1.5 && heroPositionY >= 1)){
            heroPositionX = 13;
            heroPositionY = 9;
            heroPositionY++;
        } else if((int) heroPositionX == 13 && (heroPositionY <= 9.5 && heroPositionY >= 9)){
            heroPositionX = 3;
            heroPositionY = 1;
            heroPositionY++;
            //check
        }
        //teleport5
        if(((int) heroPositionX >= 1.0 && heroPositionX <= 1.5) && ((int) heroPositionY <= 4 && heroPositionY >= 3.6)){
            heroPositionX = 9;
            heroPositionY = 11;
            heroPositionX++;

        } else if(((int) heroPositionX >= 9 && heroPositionX <= 9.5) && (int) heroPositionY == 11){
            heroPositionX = 1;
            heroPositionY = 4;
            heroPositionX++;    //pushing
            //check
        }

        //teleport6
        if((int) heroPositionX == 1 && ((int) heroPositionY <= 5 && heroPositionY >= 4.7)){
            heroPositionX = 13;
            heroPositionY = 1;
            heroPositionY++;
        } else if((int) heroPositionX == 13 && (int) heroPositionY == 1){
            heroPositionX = 1;
            heroPositionY = 5;
            heroPositionX++;
            //check
        }
        //teleport7
        if((int) heroPositionX == 6 && (int) heroPositionY == 5){
            heroPositionX = 15;
            heroPositionY = 3;
            heroPositionX--;
        } else if((int) heroPositionX == 15 && (int) heroPositionY == 3){
            heroPositionX = 6;
            heroPositionY = 5;
            heroPositionX--;
            //check
        }
        //teleport8
        if((int) heroPositionX == 6 && (int) heroPositionY == 4){
            heroPositionX = 12;
            heroPositionY = 3;
            heroPositionX++;
        } else if((int) heroPositionX == 12 && (int) heroPositionY == 3){
            heroPositionX = 6;
            heroPositionY = 4;
            heroPositionX--;
            //check
        }
        //teleport9;
        if((int) heroPositionX == 4 && ((int)heroPositionY <= 6 && heroPositionY >= 5.6)){
            heroPositionX = 7;
            heroPositionY = 8;
            heroPositionX++;
        } else if((int) heroPositionX == 7 && ((int) heroPositionY <= 8 && heroPositionY >= 7.7)){
            heroPositionX = 4;
            heroPositionY = 6;
            heroPositionY--;
            //check
        }
        //teleport10
        if((int) heroPositionX == 3 && ((int)heroPositionY <= 6 && heroPositionY >= 5.6)){
            heroPositionX = 10;
            heroPositionY = 9;
            heroPositionX--;
        } else if((int) heroPositionX == 10 && ((int) heroPositionY <= 9 && heroPositionY >= 8.6 )){
            heroPositionX = 3;
            heroPositionY = 6;
            heroPositionY--;
            //check
        }
        //teleport11
        if((int) heroPositionX == 9 && ((int)heroPositionY <= 4 && heroPositionY >= 3.6)){
            heroPositionX = 21;
            heroPositionY = 11;
            heroPositionX--;
        } else if((int) heroPositionX == 21 && ((int) heroPositionY <= 11 && heroPositionY >= 10.6)){
            heroPositionX = 9;
            heroPositionY = 4;
            heroPositionY--;
            //check
        }
        //teleport12
        if((int) heroPositionX == 16 && ((int)heroPositionY <= 10 && heroPositionY >= 9.6)){
            heroPositionX = 19;
            heroPositionY = 9;
            heroPositionX++;
        } else if((int) heroPositionX == 19 && ((int) heroPositionY >= 9 && heroPositionY <= 9.6)){
            heroPositionX = 16;
            heroPositionY = 10;
            heroPositionY--;
            //check
        }
        //teleport13
        if((int) heroPositionX == 15 && ((int) heroPositionY >= 7 && heroPositionY <= 7.5)){
            heroPositionX = 10;
            heroPositionY = 6;
            heroPositionY++;
        } else if((int) heroPositionX == 10 && ((int) heroPositionY >= 6 && heroPositionY <= 6.6)){
            heroPositionX = 15;
            heroPositionY = 7;
            heroPositionY++;
            heroPositionX += 0.4f;
        }
        //teleport14
        if((int) heroPositionX == 14 && ((int)heroPositionY <= 5 && heroPositionY >= 4.6)){
            heroPositionX = 19;
            heroPositionY = 6;
            heroPositionX++;
        } else if((int)heroPositionX == 19 && ((int)heroPositionY <= 6 && heroPositionY >= 5.7)){
            heroPositionX = 14;
            heroPositionY = 5;
            heroPositionY--;
        }
    }

    public int getID() {
        return 12;
    }
}
