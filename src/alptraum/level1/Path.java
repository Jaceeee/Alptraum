package alptraum.level1;

import alptraum.Hero;
import alptraum.Camera;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

/**
 * Created by niervin on 11/21/2016.
 */
public class Path extends BasicGameState{
    Animation hero,steady, movingUp, movingDown,movingRight, movingLeft;
    int[] duration = {200,200,200}; // an animation is a series of frames(milliseconds)
    int[] duration2 = {1000,100,100};
    float heroPositionX = 6f;// keep track of position of hero
    float heroPositionY = 11f;
    Hero player;
    Rectangle rHero;
    Image hp;
    private TiledMap cave3;
    Camera camera;

    private static final int NUMBEROFLAYERS = 7;
    private static final float SPEED = 0.0025f;
    private static final int TILESIZE = 32;
    private boolean[][] blocked;

    public Path(int state, Hero player) throws SlickException {
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
        System.out.println(xBlock + " mao ni si Xblock");
        System.out.println(yBlock + " mao ni si yBlock");

        System.out.println(blocked[xBlock][yBlock] + " mao ni ilang values");
        return blocked[xBlock][yBlock];
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        cave3 = new TiledMap("res/background/puzzle1/Cave3part1Final.tmx", "res/background/puzzle1");
        blocked = new boolean[cave3.getWidth()][cave3.getHeight()];
        initializeBlocked();
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
        camera = new Camera(container,cave3);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        camera.translateGraphics();
        camera.drawMap(0);
        camera.drawMap(1);
        camera.drawMap(2);
        hero.draw(heroPositionX * 32,heroPositionY * 32);
        camera.drawMap(3);
        camera.drawMap(5);
        //x = 9
        //y = 11;
        g.drawString("hero X position: "+heroPositionX+"\nhero Y position: "+heroPositionY,400,200);
        g.drawString("HERO NAME: "+player.getName()+" ",100,100);

        g.drawString("HEALTH: ", 9 * TILESIZE , 11.8f * TILESIZE);
        if(player.getHp() >= 1) hp.draw(11 * TILESIZE,12 * TILESIZE,20,15);
        if(player.getHp() >= 2) hp.draw(12 * TILESIZE, 12 * TILESIZE, 20, 15);
        if(player.getHp() >= 3) hp.draw(13 * TILESIZE,12 * TILESIZE,20,15);
        if(player.getHp() >= 4) hp.draw(14 * TILESIZE,12 * TILESIZE,20,15);
        if(player.getHp() == 5) hp.draw(15 * TILESIZE,12 * TILESIZE,20,15);

    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input input = gc.getInput();
        if (input.isKeyDown(Input.KEY_UP)) {
            System.out.println(delta + " OH YEAH");
            movingUp.update(delta);
            hero = movingUp;
            if(!isBlocked(heroPositionX, heroPositionY + delta * SPEED - 0.1f)){
                heroPositionY -= delta * SPEED;
            }
        }
        else if (input.isKeyDown(Input.KEY_DOWN)) {
            movingDown.update(delta);
            hero = movingDown;
            if(!isBlocked(heroPositionX, heroPositionY + delta * SPEED + 0.1f)){
                heroPositionY += delta * SPEED;
            }
        }
        else if (input.isKeyDown(Input.KEY_LEFT)) {
            movingLeft.update(delta);
            hero = movingLeft;
            if (!isBlocked(heroPositionX - delta * SPEED - 0.1f, heroPositionY )) {
                heroPositionX -=  delta * SPEED;
            }
        }
        else if (input.isKeyDown(Input.KEY_RIGHT)) {
            movingRight.update(delta);
            hero = movingRight;
            if (!isBlocked(heroPositionX + delta * SPEED + 0.6f, heroPositionY)){
                heroPositionX += delta * SPEED;
            }
        } else {
            hero = steady;
        }

        if(!(input.isKeyDown(input.KEY_UP)) &&!( input.isKeyDown(input.KEY_DOWN)) &&!(input.isKeyDown(input.KEY_LEFT))&&!(input.isKeyDown(input.KEY_RIGHT))){
            hero = steady;
        }

        if((int) heroPositionY == 12 && (heroPositionX >= 5 && heroPositionX <= 7))
            sbg.enterState(8);

        if((int) heroPositionX == 22 && (heroPositionY >= 1 && heroPositionY <= 3))
            sbg.enterState(10);
    }

    @Override
    public int getID(){
        return 9;
    }

}
