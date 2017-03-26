package alptraum.level1;

import alptraum.Hero;
import alptraum.Camera;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

/**
 * Created by niervin on 11/21/2016.
 */
public class SecretRoom extends BasicGameState{
    Animation hero,steady, movingUp, movingDown,movingRight, movingLeft;
    int[] duration = {200,200,200}; // an animation is a series of frames(milliseconds)
    int[] duration2 = {1000,100,100};
    float heroPositionX = 2f;// keep track of position of hero
    float heroPositionY = 5f;
    Hero player;
    Image hp;
    private TiledMap secretRoom;
    Camera camera;

    private boolean blocked[][];
    private static final int NUMBEROFLAYERS = 5;
    private static final float SPEED = 0.0025f;
    private static final int TILESIZE = 32;

    public SecretRoom(int state, Hero player) throws SlickException {
        this.player = player;
    }

    private void initializeBlocked() {
        int numtiles = 0;
        for (int l = 0; l < NUMBEROFLAYERS; l++) {
            String layerValue = secretRoom.getLayerProperty(l, "blocked", "false");
            if (layerValue.equals("true")) {
                for (int c = 0; c < secretRoom.getHeight(); c++) {
                    for(int r = 0; r < secretRoom.getWidth(); r++) {
                        if(secretRoom.getTileId(r, c, l) != 0) {
                            blocked[r][c] = true;
                            numtiles ++;
                        }
                    }
                }
            }
        }
        System.out.println(numtiles + " MAO NI SI NUMTILES NUMBER!");
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
        secretRoom = new TiledMap("res/background/puzzle1/Cave1Part3Final.tmx");
        blocked = new boolean[secretRoom.getWidth()][secretRoom.getHeight()];

        hp = new Image("res/background/puzzle1/FOOD/heart.png");
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
        camera = new Camera(container,secretRoom);
        initializeBlocked();
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        camera.translateGraphics();
        camera.drawMap(0);
        //camera.drawMap(1);
        camera.drawMap(3);
        camera.drawMap(2);

        hero.draw(heroPositionX * 32,heroPositionY * 32);
        camera.drawMap(4);


        if(player.getHp() == 5) {hp.draw(7 * TILESIZE,12 * TILESIZE,20,15);}
        if(player.getHp() >= 4) {hp.draw(6 * TILESIZE, 12 * TILESIZE, 20, 15);}
        if(player.getHp() >= 3) {hp.draw(5 * TILESIZE,12 * TILESIZE,20,15);}
        if(player.getHp() >= 2) {hp.draw(4 * TILESIZE,12 * TILESIZE,20,15);}
        if(player.getHp() >= 1) {hp.draw(3 * TILESIZE,12 * TILESIZE,20,15);}

        g.drawString("Health: ", 1 * TILESIZE, 11.5f * TILESIZE);
        g.drawString("hero X position: "+heroPositionX+"\nhero Y position: "+heroPositionY,400,200);

        g.drawString("hero X position: "+heroPositionX+"\nhero Y position: "+heroPositionY,400,200);
        g.drawString("HERO NAME: "+player.getName()+" ",100,100);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input input = gc.getInput();
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
            if (!isBlocked(heroPositionX + delta * SPEED + 0.6f, heroPositionY)) {
                heroPositionX += delta * SPEED;
            }
        } else {
            hero = steady;
        }
        if ((int) heroPositionX == 0 && ((int) heroPositionY >= 2 && (int) heroPositionY <= 7)) {
            sbg.enterState(6);
        }
    }

    @Override
    public int getID(){
        return 11;
    }

}
