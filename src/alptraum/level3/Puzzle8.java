package alptraum.level3;

import alptraum.Camera;
import alptraum.Hero;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.tiled.TiledMap;

import java.awt.Font;

/**
 * Created by niervin on 11/25/2016.
 */
public class Puzzle8 extends BasicGameState{
    Animation hero,steady, movingUp, movingDown,movingRight, movingLeft;
    private static final int TILEWIDTH = 32;
    private static final int TILEHEIGHT = 32;

    private int[] duration = {200,200,200}; // an animation is a series of frames(milliseconds)
    private int[] duration2 = {1000,100,100};
    private int[] duration3 = {250,150,150,100};
    private float heroPositionX = 6.5f;// keep track of position of hero
    private float heroPositionY = 11f;
    private float heroW = 25.0f;
    private float heroL = 34.0f;
    private boolean check;
    Animation angel;
    Rectangle rHero;
    private Ghost1 rensarrews;
    private Angel angie;
    private Rectangle ghost1,angel1;
    private boolean start;
    private boolean interact;
    private TrueTypeFont ttf;
    private Font font;
    private Image textbox;
    private Image heart;
    private Rectangle rec;
    private float recPosX, recPosY;
    private boolean movenow;
    private TiledMap forest2;
    private static final int NUMBEROFLAYERS = 6;
    private static final float SPEED = 0.0025f;
    private Hero player;
    private Camera camera;
    private boolean[][] blocked;
    private boolean interact2;

    public Puzzle8(int state, Hero player) throws SlickException {
        this.player = player;
    }

    private void initializeBlocked() {
        int numTiles = 0;
        for (int l = 0; l < NUMBEROFLAYERS; l++) {
            String layerValue = forest2.getLayerProperty(l, "blocked", "false");
            if (layerValue.equals("true")) {
                for (int c = 0; c < forest2.getHeight(); c++) {
                    for(int r = 0; r < forest2.getWidth(); r++) {
                        if(forest2.getTileId(r, c, l) != 0) {
                            blocked[r][c] = true;
                            numTiles++;
                        }
                    }
                }
            }
        }
    }

    private boolean isBlocked(float x, float y) {
        if((x < 0 ) || x >= TILEWIDTH || y < 0 || y >= TILEHEIGHT){
            return true;
        }
        int xBlock = (int) x /* TILEWIDTH*/;
        int yBlock = (int) y /* TILEHEIGHT*/;
        return blocked[xBlock][yBlock];
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        forest2 = new TiledMap("res/background/puzzle3/map2.tmx");
        blocked = new boolean[forest2.getWidth()][forest2.getHeight()];
        initializeBlocked();

        start = true;
        interact = false;
        recPosX = 5f;
        recPosY = 11.7f;
        movenow = true;

        angie = new Angel(14,6);
        angie.setCtrMessages(10);
        heart = new Image("res/background/PUZZLE1/FOOD/heart.png");
        textbox = new Image("res/etc/textbox1.png");
        rHero = new Rectangle(0,15,0,0);
        ghost1 = new Rectangle(0,0,0,0);
        angel1 = new Rectangle(0,0,0,0);
        rec = new Rectangle(0,0,0,0);
        font = new Font("Century Gothic", Font.BOLD,20);
        ttf = new TrueTypeFont(font,true);
        interact2 = false;

        Image[] heroSteady = {new Image("res/characters/hero/0.png"),new Image("res/characters/hero/3.png"),new Image("res/characters/hero/4.png")};
        Image[] walkUp = {new Image("res/characters/hero/2.png"),new Image("res/characters/hero/11.png"),new Image("res/characters/hero/12.png")};
        Image[] walkLeft = {new Image("res/characters/hero/1.png"),new Image("res/characters/hero/9.png"),new Image("res/characters/hero/10.png")};
        Image[] walkRight = {new Image("res/characters/hero/R1.png"),new Image("res/characters/hero/R2.png"),new Image("res/characters/hero/R3.png")};
        Image[] walkDown = {new Image("res/characters/hero/0.png"),new Image("res/characters/hero/7.png"),new Image("res/characters/hero/8.png")};
        Image[] angelstead = {new Image("res/characters/Angel/8.png"),new Image("res/characters/Angel/9.png"),new Image("res/characters/Angel/10.png")
                ,new Image("res/characters/Angel/11.png")};

        angel = new Animation(angelstead,duration3,true);
        movingRight = new Animation(walkRight,duration,true);
        movingUp = new Animation(walkUp,duration,true);
        movingLeft = new Animation(walkLeft,duration,true);
        movingDown = new Animation(walkDown,duration,true);
        steady = new Animation(heroSteady,duration2, true);
        hero = steady;
        camera = new Camera(container, forest2);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        camera.translateGraphics();
        camera.drawMap(0);
        angel.draw(angie.angelposX * TILEWIDTH, angie.angelposY * TILEHEIGHT);
        hero.draw(heroPositionX * 32, heroPositionY * 32);
        camera.drawMap(2);
        camera.drawMap(3);
        camera.drawMap(4);
        camera.drawMap(1);
        camera.drawMap(5);
        g.drawString("hero X position: "+heroPositionX+"\nhero Y position: "+heroPositionY,400,200);
        g.drawString("HERO NAME: "+player.getName()+" ",100,100);

        rHero = new Rectangle(heroPositionX * 32,heroPositionY * 32,heroW,heroL);
        angel1 = new Rectangle((angie.angelposX + .55f) * TILEWIDTH , (angie.angelposY + .75f) * TILEHEIGHT,25,25);
        if(interact){
            if(angie.getMessages() <= 18){
                movenow = false;
                angie.getImage().draw(19 * TILEWIDTH, 6 * TILEHEIGHT, 1.75f);
                textbox.draw(0, 9 * TILEHEIGHT, 720, 115);

                if(angie.getMessages() == 15) {
                    //rec = new Rectangle(recPosX * 32, recPosY * 32, 55, 55);
                    //g.fill(rec);
                    heart.draw(recPosX * 32, recPosY * 32,20,15);
                    ttf.drawString(4 * TILEWIDTH, 11.5f * TILEHEIGHT, angie.Interact(9), org.newdawn.slick.Color.white);
                }

                ttf.drawString(3 * TILEWIDTH, 10 * TILEHEIGHT, angie.Interact(angie.getMessages()), org.newdawn.slick.Color.white);
            } else{
                movenow = true;
            }
        }
        g.draw(rHero);
        g.draw(angel1);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input input = gc.getInput();

        if(rHero.intersects(angel1)){
            if(angie.getMessages() == 15) {
                if (input.isKeyPressed(Input.KEY_LEFT)) {
                    recPosX = 5f;
                    check = false;   //left no

                } else if (input.isKeyPressed(Input.KEY_RIGHT)) {
                    recPosX = 15f;
                    check = true;  //right yes
                }
            }

            if(input.isKeyPressed(Input.KEY_I)){
                if(angie.getMessages() == 15) {
                    if (!check) {
                        angie.setCtrMessages(14);
                        sbg.enterState(15, new FadeOutTransition(org.newdawn.slick.Color.white, 3000), new FadeInTransition(org.newdawn.slick.Color.white, 3000));
                    }
                }
                interact = true;
                angie.addMessage();
            }
        }


        if(movenow) {
            if (input.isKeyDown(Input.KEY_UP)) {
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

            if ((heroPositionX >= 2 && heroPositionX <= 11) && (int) heroPositionY == 12) {
                sbg.enterState(14, new FadeOutTransition(org.newdawn.slick.Color.white, 3000), new FadeInTransition(org.newdawn.slick.Color.white, 3000));
            } else if((heroPositionX >= 11 && heroPositionX <= 16) && (int) heroPositionY == 0){
                sbg.enterState(2, new FadeOutTransition(org.newdawn.slick.Color.white, 3000), new FadeInTransition(org.newdawn.slick.Color.white, 3000));
            }
        }
    }

    @Override
    public int getID() {
        return 15;
    }

}
