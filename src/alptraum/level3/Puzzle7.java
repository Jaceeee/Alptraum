package alptraum.level3;


import alptraum.Hero;
import alptraum.Camera;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.tiled.TiledMap;
import java.awt.Font;

public class Puzzle7 extends BasicGameState{
    Animation hero,steady, movingUp, movingDown,movingRight, movingLeft;
    Animation angel;

    private static final int TILESIZE  = 32;

    private Ghost1 rensarrews;
    private Angel angie;
    private Rectangle ghost1,angel1;
    Animation ghost;
    private boolean start;
    private boolean interact;
    TrueTypeFont ttf;
    Font font;
    Image textbox;
    Rectangle rec;

    private boolean movenow;
    private float recPosX, recPosY;
    private int[] duration = {200,200,200}; // an animation is a series of frames(milliseconds)
    private int[] duration2 = {1000,100,100};
    private int[] duration3 = {250,150,150,100};
    private float heroPositionX = 3.5f;// keep track of position of hero
    private float heroPositionY = 8f;
    private float heroW = 25.0f;
    private float heroL = 34.0f;

    private boolean nowleft,nowright,nowUp,nowDown;
    private TiledMap forest1;
    private static final int NUMBEROFLAYERS = 7;
    private static final float SPEED = 0.0025f;
    private Hero player;
    private Camera camera;
    private boolean[][] blocked;
    private Rectangle rHero;
    private boolean interact2;
    private boolean check;
    private Image heart;

    public Puzzle7(int state, Hero player) throws SlickException{
        this.player = player;
    }

    private void initializeBlocked() {
        for (int l = 0; l < NUMBEROFLAYERS; l++) {
            String layerValue = forest1.getLayerProperty(l, "blocked", "false");
            if (layerValue.equals("true")) {
                for (int c = 0; c < forest1.getHeight(); c++) {
                    for(int r = 0; r < forest1.getWidth(); r++) {
                        if(forest1.getTileId(r, c, l) != 0) {
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
        forest1 = new TiledMap("res/background/puzzle3/map1Final.tmx");
        blocked = new boolean[forest1.getWidth()][forest1.getHeight()];
        initializeBlocked();
        start = true;
        interact = false;
        recPosX = 5f;
        recPosY = 11.7f;
        movenow = false;

        rHero = new Rectangle(0,15,0,0);
        ghost1 = new Rectangle(0,0,0,0);
        angel1 = new Rectangle(0,0,0,0);
        rec = new Rectangle(0,0,0,0);
        font = new Font("Century Gothic", Font.BOLD,20);
        ttf = new TrueTypeFont(font,true);

        rensarrews = new Ghost1(8,3);
        angie = new Angel(12,4);
        angie.setCtrMessages(10);
        textbox = new Image("res/etc/textbox1.png");

        Image[] ghostSteady = {new Image("res/characters/Ghost/0.png"),new Image("res/characters/Ghost/2.png"),new Image("res/characters/Ghost/left.png")};
        Image[] heroSteady = {new Image("res/characters/hero/0.png"),new Image("res/characters/hero/3.png"),new Image("res/characters/hero/4.png")};
        Image[] walkUp = {new Image("res/characters/hero/2.png"),new Image("res/characters/hero/11.png"),new Image("res/characters/hero/12.png")};
        Image[] walkLeft = {new Image("res/characters/hero/1.png"),new Image("res/characters/hero/9.png"),new Image("res/characters/hero/10.png")};
        Image[] walkRight = {new Image("res/characters/hero/R1.png"),new Image("res/characters/hero/R2.png"),new Image("res/characters/hero/R3.png")};
        Image[] walkDown = {new Image("res/characters/hero/0.png"),new Image("res/characters/hero/7.png"),new Image("res/characters/hero/8.png")};
        Image[] angelstead = {new Image("res/characters/Angel/8.png"),new Image("res/characters/Angel/9.png"),new Image("res/characters/Angel/10.png")
                        ,new Image("res/characters/Angel/11.png")};

        ghost = new Animation(ghostSteady,duration,true);
        angel = new Animation(angelstead,duration3,true);
        movingRight = new Animation(walkRight,duration,true);
        movingUp = new Animation(walkUp,duration,true);
        movingLeft = new Animation(walkLeft,duration,true);
        movingDown = new Animation(walkDown,duration,true);
        steady = new Animation(heroSteady,duration2, true);
        hero = steady;
        nowleft = nowright = nowUp = nowDown = false;
        camera = new Camera(container, forest1);
        interact2 = false;

    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        camera.translateGraphics();
        camera.drawMap(0);
        ghost.draw(rensarrews.ghostposX * TILESIZE, rensarrews.ghostposY * TILESIZE);
        angel.draw(12 * TILESIZE,4 * TILESIZE);
        hero.draw(heroPositionX * 32, heroPositionY * 32);
        camera.drawMap(1);
        camera.drawMap(2);
        camera.drawMap(6);
        camera.drawMap(3);
        camera.drawMap(4);
        camera.drawMap(5);

        angel1 = new Rectangle((angie.angelposX + .55f) * TILESIZE , (angie.angelposY + .75f) * TILESIZE,25,25);
        ghost1 = new Rectangle((rensarrews.ghostposX * TILESIZE + 0.255f), rensarrews.ghostposY * TILESIZE,30,30);
        rHero = new Rectangle(heroPositionX * TILESIZE, heroPositionY * TILESIZE, 25,25);

        g.draw(rHero);
        g.drawString("hero X position: "+heroPositionX+"\nhero Y position: "+heroPositionY,400,200);
        g.drawString("HERO NAME: "+player.getName()+" ",100,100);
        g.draw(ghost1);
        g.draw(angel1);

        if(start){
            if(rensarrews.getMessages() <= 6){
                if(rensarrews.getMessages() == 1 || rensarrews.getMessages() == 2){
                    rensarrews.getImage(6).draw(19 * TILESIZE, 6 * TILESIZE, 2.75f);
                } else if(rensarrews.getMessages() == 5 || rensarrews.getMessages() == 3){
                    rensarrews.getImage(7).draw(19 * TILESIZE, 6 * TILESIZE, 2.75f);
                } else {
                    rensarrews.getImage(0).draw(19 * TILESIZE, 6 * TILESIZE, 2.75f);
                }
                textbox.draw(0, 9 * TILESIZE, 720, 115);
                ttf.drawString(3 * TILESIZE, 10 * TILESIZE, rensarrews.
                        Interact(rensarrews.getMessages()), org.newdawn.slick.Color.white);
            } else {
                rensarrews.setCtrMessages(6);
                start = false;
                movenow = true;
            }
        }

        if(interact){
            if(rensarrews.getMessages() <= 8){
                movenow = false;
                rensarrews.getImage(0).draw(19 * TILESIZE, 6 * TILESIZE, 2.75f);
                textbox.draw(0, 9 * TILESIZE, 720, 115);
                ttf.drawString(3 * TILESIZE, 10 * TILESIZE, rensarrews.Interact(rensarrews.getMessages()), org.newdawn.slick.Color.white);
            } else {
                movenow = true;
                interact = false;
            }
        }

        if(interact2){
            if(angie.getMessages() <= 8){
                movenow = false;
                angie.getImage().draw(19 * TILESIZE, 6 * TILESIZE, 1.75f);
                textbox.draw(0, 9 * TILESIZE, 720, 115);

                if(angie.getMessages() == 5) {
                    //rec = new Rectangle(recPosX * 32, recPosY * 32, 55, 55);
                    heart.draw(recPosX * 32, recPosY * 32,20,15);
                    //g.fill(rec);
                    ttf.drawString(4 * TILESIZE, 11.5f * TILESIZE, angie.Interact(9), org.newdawn.slick.Color.white);
                }

                ttf.drawString(3 * TILESIZE, 10 * TILESIZE, angie.Interact(angie.getMessages()), org.newdawn.slick.Color.white);

            } else {
                movenow = true;
                interact2 = false;
            }
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input input = gc.getInput();

        if(start) {
            if(input.isKeyPressed(Input.KEY_I)){
                rensarrews.addMessage();
            }

        } else {
            if(rHero.intersects(ghost1)){
                if(input.isKeyPressed(Input.KEY_I)){
                    if(rensarrews.getMessages() > 9){ rensarrews.setCtrMessages(7); }
                    interact = true;
                    rensarrews.addMessage();
                }
            }
            if(rHero.intersects(angel1)) {
                if(angie.getMessages() == 5) {
                    if (input.isKeyPressed(Input.KEY_LEFT)) {
                        recPosX = 5f;
                        check = false;   //left no

                    } else if (input.isKeyPressed(Input.KEY_RIGHT)) {
                        recPosX = 15f;
                        check = true;  //right yes
                    }
                }
                if(input.isKeyPressed(Input.KEY_I)){
                    if(!check && angie.getMessages() == 6){
                        angie.setCtrMessages(8);
                    }

                    if(angie.getMessages() == 5) {
                        if (!check)
                            angie.setCtrMessages(5);
                        else
                            angie.setCtrMessages(6);
                    }
                    interact2 = true;
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
                if ((heroPositionX >= 2 && heroPositionX <= 5) && (int) heroPositionY == 0) {
                    sbg.enterState(15, new FadeOutTransition(org.newdawn.slick.Color.white, 3000), new FadeInTransition(org.newdawn.slick.Color.white, 3000));
                }
            }
        }
    }

    @Override
    public int getID() {
        return 14;
    }
}
