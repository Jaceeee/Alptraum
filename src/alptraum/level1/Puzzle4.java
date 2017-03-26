package alptraum.level1;

/**
 * Created by Loewe on 11/21/2016.
 */

import alptraum.Hero;
import alptraum.Camera;
import alptraum.Item;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.tiled.TiledMap;
import java.awt.Font;

public class Puzzle4 extends BasicGameState{
    Animation hero,steady, movingUp, movingDown,movingRight, movingLeft;
    int[] duration = {200,200,200}; // an animation is a series of frames(milliseconds)
    int[] duration2 = {1000,100,100};
    private float heroPositionX = 1.5f;// keep track of position of hero
    private float heroPositionY = 9f;
    private  float heroW = 20.0f;
    private  float heroL = 27.0f;
    private Hero player;
    private Rectangle rHero;
    private Rectangle cake1,cake2,cake3,cake4,cake5,cake6,cake7,cake8,cake9;
    private Rectangle sword1,sword2,sword3,sword4,sword5,sword6,sword7,sword8,sword9,sword10,sword11;
    private Rectangle meat1,meat2,meat3,sw1;
    private TiledMap cave4;
    private Camera camera;
    private static final float SPEEDOBJ = 0.025f;
    private boolean blocked[][];
    private static final int TILESIZE = 32;
    private Image cakes;
    private Image swords;
    private Image meats;
    private Image hp;
    private boolean up1, down1, up2, down2;
    private static final int NUMBEROFLAYERS = 7;
    private static final float SPEED = 0.0025f;
    private Item sw;
    private int hit;
    private boolean turnon;
    Animation ghost;
    Image textbox;
    private Ghost rensarrews;
    TrueTypeFont ttf;
    Font font;
    private float ghostposX = 2f, ghostposY = 4f;
    private boolean start;
    private boolean interaction;
    Rectangle ghost1;

    private float moveX1 = 6f, moveX2 = 7f, moveX3 = 8f, moveX4 = 9f,moveX5 = 10f,moveX6 = 11f,moveX7 = 12f,
            moveX8 = 13f,moveX9 = 14f,moveX10 = 15f, moveX11 = 16f, moveX12 = 17f, moveX13 = 18f;
    private float moveY = 2f;
    private float moveY2 = 10f;

    public Puzzle4(int state, Hero player) throws SlickException{
        this.player = player;
    }

    private void initializeBlocked() {
        for (int l = 0; l < NUMBEROFLAYERS; l++) {
            String layerValue = cave4.getLayerProperty(l, "blocked", "false");
            if (layerValue.equals("true")) {
                for (int c = 0; c < cave4.getHeight(); c++) {
                    for(int r = 0; r < cave4.getWidth(); r++) {
                        if(cave4.getTileId(r, c, l) != 0) {
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
        cake1 = new Rectangle(0,0,0,0);
        cake2 = new Rectangle(0,0,0,0);
        cake3 = new Rectangle(0,0,0,0);
        cake4 = new Rectangle(0,0,0,0);
        cake5 = new Rectangle(0,0,0,0);
        cake6 = new Rectangle(0,0,0,0);
        cake7 = new Rectangle(0,0,0,0);
        cake8 = new Rectangle(0,0,0,0);
        cake9 = new Rectangle(0,0,0,0);
        sword1 = new Rectangle(0,0,0,0);
        sword2 = new Rectangle(0,0,0,0);
        sword3 = new Rectangle(0,0,0,0);
        sword4 = new Rectangle(0,0,0,0);
        sword5 = new Rectangle(0,0,0,0);
        sword6 = new Rectangle(0,0,0,0);
        sword7 = new Rectangle(0,0,0,0);
        sword8 = new Rectangle(0,0,0,0);
        sword9 = new Rectangle(0,0,0,0);
        sword10 = new Rectangle(0,0,0,0);
        sword11 = new Rectangle(0,0,0,0);
        meat1 = new Rectangle(0,0,0,0);
        meat2 = new Rectangle(0,0,0,0);
        meat3 = new Rectangle(0,0,0,0);
        rHero = new Rectangle(0,15,0,0);
        ghost1 = new Rectangle(0,0,0,0);
        sw1 = new Rectangle(0,0,0,0);
        rensarrews = new Ghost(ghostposX,ghostposY);

        up1 = down2= false;
        down1 = up2 = true;
        hit = 20;
        textbox = new Image("res/etc/textbox1.png");
        hp = new Image("res/background/puzzle1/FOOD/heart.png");
        swords = new Image("res/background/puzzle1/FOOD/Sword.png");
        cakes = new Image("res/background/puzzle1/FOOD/Cake.png");
        meats = new Image("res/background/puzzle1/FOOD/Meat.png");
        cave4 = new TiledMap("res/background/puzzle1/Cave3part2Final.tmx", "res/background/puzzle1");
        blocked = new boolean[cave4.getWidth()][cave4.getHeight()];
        initializeBlocked();

        String[] message = {"What's this?", "A switch"};
        sw = new Item(message,2);
        sw.setCtrMessage(-1);

        Image[] ghostSteady = {new Image("res/characters/Ghost/0.png"),new Image("res/characters/Ghost/2.png"),new Image("res/characters/Ghost/left.png")};
        Image[] heroSteady = {new Image("res/characters/hero/0.png"),new Image("res/characters/hero/3.png"),new Image("res/characters/hero/4.png")};
        Image[] walkUp = {new Image("res/characters/hero/2.png"),new Image("res/characters/hero/11.png"),new Image("res/characters/hero/12.png")};
        Image[] walkLeft = {new Image("res/characters/hero/1.png"),new Image("res/characters/hero/9.png"),new Image("res/characters/hero/10.png")};
        Image[] walkRight = {new Image("res/characters/hero/R1.png"),new Image("res/characters/hero/R2.png"),new Image("res/characters/hero/R3.png")};
        Image[] walkDown = {new Image("res/characters/hero/0.png"),new Image("res/characters/hero/7.png"),new Image("res/characters/hero/8.png")};

        font = new Font("Century Gothic",Font.BOLD,20);
        ttf = new TrueTypeFont(font,true);
        start = true;
        interaction = false;

        ghost = new Animation(ghostSteady,duration,true);
        movingRight = new Animation(walkRight,duration,true);
        movingUp = new Animation(walkUp,duration,true);
        movingLeft = new Animation(walkLeft,duration,true);
        movingDown = new Animation(walkDown,duration,true);
        steady = new Animation(heroSteady,duration2, true);
        hero = steady;
        camera = new Camera(container,cave4);
        rensarrews.setCtrMessages(28);
        turnon = false;
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        camera.translateGraphics();
        camera.drawMap(0);
        camera.drawMap(1);
        camera.drawMap(2);

        hero.draw(heroPositionX * 32,heroPositionY * 32);
        camera.drawMap(3);
        camera.drawMap(4);
        ghost.draw(rensarrews.ghostposX * TILESIZE, rensarrews.ghostposY * 32);
        sw1 = new Rectangle(2 * TILESIZE, 2 * TILESIZE, 35,35);
        ghost1 = new Rectangle(rensarrews.ghostposX * 32, (rensarrews.ghostposY + 0.4f)* 32,25,25);
        rHero = new Rectangle(heroPositionX * TILESIZE - 1,heroPositionY * TILESIZE,heroW,heroL);
        cake1 = new Rectangle(moveX1 * 32,moveY * 32,25,25);
        cake2 = new Rectangle(moveX1 * 32,moveY2 * 32,25,25);
        cake3 = new Rectangle(moveX3 * 32,moveY * 32,25,25);
        cake4 = new Rectangle(moveX5 * 32,moveY2 * 32,25,25);
        cake5 = new Rectangle(moveX5 * 32,moveY * 32,25,25);
        cake6 = new Rectangle(moveX7 * 32,moveY * 32,25,25);
        cake7 = new Rectangle(moveX9 * 32,moveY * 32,25,25);
        cake8 = new Rectangle(moveX10 * 32,moveY2 * 32,25,25);
        cake9 = new Rectangle(moveX4 * 32,moveY2 * 32,25,25);

        sword1 = new Rectangle(moveX2 * 32,moveY *32, 25,25);
        sword2 = new Rectangle(moveX4 * 32,moveY *32, 25,25);
        sword3 = new Rectangle(moveX4 * 32,moveY2 *32, 25,25);
        sword4 = new Rectangle(moveX6 * 32,moveY *32, 25,25);
        sword5 = new Rectangle(moveX8 * 32,moveY *32, 25,25);
        sword6 = new Rectangle(moveX10 * 32,moveY *32, 25,25);
        sword7 = new Rectangle(moveX13 * 32,moveY *32, 25,25);
        sword8 = new Rectangle(moveX9 * 32,moveY2 *32, 25,25);
        sword9 = new Rectangle(moveX7 * 32,moveY2 *32, 25,25);
        sword10 = new Rectangle(moveX6 * 32,moveY2 *32, 25,25);
        sword10 = new Rectangle(moveX3 * 32,moveY2 *32, 25,25);

        meat1 = new Rectangle(moveX13 * 32, moveY * 32,25,25);
        meat2 = new Rectangle(moveX12 * 32, moveY * 32,25,25);
        meat3 = new Rectangle(moveX11 * 32, moveY * 32,25,25);

        cakes.draw(moveX1 * 32, moveY * 32);
        cakes.draw(moveX1 * 32, moveY2 * 32);
        meats.draw(moveX13 * 32, moveY * 32);
        swords.draw(moveX2 * 32, moveY * 32);
        cakes.draw(moveX3 * 32, moveY * 32);
        swords.draw(moveX4 * 32, moveY * 32);
        swords.draw(moveX4 * 32, moveY2 * 32);
        cakes.draw(moveX5 * 32, moveY2 * 32);
        cakes.draw(moveX5 * 32, moveY * 32);
        swords.draw(moveX6 * 32, moveY * 32);
        meats.draw(moveX12 * 32, moveY * 32);
        cakes.draw(moveX7 * 32, moveY * 32);
        swords.draw(moveX8 * 32, moveY * 32);
        cakes.draw(moveX9 * 32, moveY * 32);
        meats.draw(moveX11 * 32, moveY * 32);
        swords.draw(moveX10 * 32, moveY * 32);
        cakes.draw(moveX10 * 32, moveY2 * 32);
        swords.draw(moveX13 * 32, moveY * 32);
        cakes.draw(moveX4 * 32, moveY2 * 32);
        swords.draw(moveX9 * 32, moveY2 * 32);
        swords.draw(moveX7 * 32, moveY2 * 32);
        swords.draw(moveX6 * 32, moveY2 * 32);
        swords.draw(moveX3 * 32, moveY2 * 32);
        g.draw(rHero);
        g.draw(cake1);
        g.draw(cake2);
        g.draw(cake3);
        g.draw(cake4);
        g.draw(cake5);
        g.draw(cake6);
        g.draw(cake7);
        g.draw(cake8);
        g.draw(cake9);
        g.draw(sword1);
        g.draw(sword2);
        g.draw(sword3);
        g.draw(sword4);
        g.draw(sword5);
        g.draw(sword6);
        g.draw(sword7);
        g.draw(sword8);
        g.draw(sword9);
        g.draw(sword10);
        g.draw(sword11);
        g.draw(meat1);
        g.draw(meat2);
        g.draw(meat3);
        g.draw(ghost1);
        //life points
        if(player.getHp() >= 1){ hp.draw(3 * TILESIZE,12 * TILESIZE,20,15); }
        if(player.getHp() >= 2) { hp.draw(4 * TILESIZE, 12 * TILESIZE, 20, 15); }
        if(player.getHp() >= 3) { hp.draw(5 * TILESIZE,12 * TILESIZE,20,15); }
        if(player.getHp() >= 4) { hp.draw(6 * TILESIZE,12 * TILESIZE,20,15); }
        if(player.getHp() == 5) { hp.draw(7 * TILESIZE,12 * TILESIZE,20,15); }
        g.drawString("Health: ", 1 * TILESIZE, 11.8f * TILESIZE);
        g.drawString("hero X position: "+heroPositionX+"\nhero Y position: "+heroPositionY,400,200);
        g.drawString("HERO NAME: "+player.getName()+" ",100,100);

        if(start){
            if(rensarrews.getMessages() <= 29) {
                if(rensarrews.getMessages() == 28)
                    rensarrews.getImage(3).draw(19 * TILESIZE, 6 * TILESIZE, 2.75f);
                else
                    rensarrews.getImage(0).draw(19 * TILESIZE, 6 * TILESIZE, 2.75f);

                textbox.draw(0, 9 * TILESIZE, 720, 115);
                ttf.drawString(3 * TILESIZE, 10 * TILESIZE, rensarrews.Interact(rensarrews.getMessages()), org.newdawn.slick.Color.white);
            } else{
                rensarrews.setCtrMessages(29);
                start = false;
            }
        }
        if(interaction){
            if(rensarrews.getMessages() <= 31){
                if(rensarrews.getMessages() == 30)
                    rensarrews.getImage(4).draw(19 * TILESIZE, 6 * TILESIZE, 2.75f);
                else
                    rensarrews.getImage(0).draw(19 * TILESIZE, 6 * TILESIZE, 2.75f);

                textbox.draw(0, 9 * TILESIZE, 720, 115);
                ttf.drawString(3 * TILESIZE, 10 * TILESIZE, rensarrews.Interact(rensarrews.getMessages()), org.newdawn.slick.Color.white);
            } else {
                rensarrews.setCtrMessages(29);
                interaction = false;
            }
        }
        if(turnon){
            if(sw.getCtrMessages() <= 1){
                textbox.draw(0, 9 * TILESIZE, 720, 115);
                ttf.drawString(3 * TILESIZE, 10 * TILESIZE, sw.Interact(sw.getCtrMessages()), org.newdawn.slick.Color.white);
            }
        }
    }

    private void move(int delta){
        if(down1 && (int) moveY != 11){
            moveY += delta * SPEEDOBJ;
            up1 = true;
        } else if(up1 && (int) moveY != 1f){
            moveY -= delta * SPEEDOBJ;
            down1 = false;
        } else {
            down1 = true;
        }
        if(up2 && (int) moveY2 != 1f){
            moveY2 -= delta *SPEEDOBJ;
            down2 = true;
        } else if(down2 && (int) moveY2 != 11f){
            up2 = false;
            moveY2 += delta * SPEEDOBJ;
        } else {
            up2 = true;
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input input = gc.getInput();

        if(player.getHp() == 0){
            sbg.enterState(10, new FadeOutTransition(org.newdawn.slick.Color.white, 3000), new FadeInTransition(org.newdawn.slick.Color.white, 3000));
            player.setHp(5);
            heroPositionX = 1.5f;
            heroPositionY = 9f;
        }

        if(!turnon) { move(delta); }
        if(start){
            if(input.isKeyPressed(Input.KEY_I))
                rensarrews.addMessage();

        } else{
            if(rHero.intersects(sw1)){
                if(input.isKeyPressed(Input.KEY_I)){
                    if(sw.getCtrMessages() > 1){
                        sw.setCtrMessage(-1);
                    }

                    turnon = true;
                    sw.addCtrMessage();
                    System.out.println(sw.getCtrMessages() + " mao ni si ctrmessage");
                }
            }

            if(rHero.intersects(ghost1)){
                if(input.isKeyPressed(Input.KEY_I)){
                    interaction = true;
                    rensarrews.addMessage();
                }
            }

            if (input.isKeyDown(Input.KEY_UP)) {
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
                if (!isBlocked(heroPositionX + delta * SPEED + 0.4f, heroPositionY)){
                    heroPositionX += delta * SPEED;
                }
            } else {
                hero = steady;
            }

            if(!(input.isKeyDown(input.KEY_UP)) &&!( input.isKeyDown(input.KEY_DOWN)) &&!(input.isKeyDown(input.KEY_LEFT))&&!(input.isKeyDown(input.KEY_RIGHT))){
                hero = steady;
            }

            if (hit == 20 && (rHero.intersects(cake1) || rHero.intersects(cake2) || rHero.intersects(cake3) || rHero.intersects(cake4) ||
            rHero.intersects(cake5) || rHero.intersects(cake6) || rHero.intersects(cake7) || rHero.intersects(cake8) || rHero.intersects(cake9)) ||
                    rHero.intersects(sword1) || rHero.intersects(sword2) || rHero.intersects(sword3) || rHero.intersects(sword4) || rHero.intersects(sword5)
                    || rHero.intersects(sword6) || rHero.intersects(sword7) || rHero.intersects(sword8) || rHero.intersects(sword9) || rHero.intersects(sword10) ||
                    rHero.intersects(sword11) || rHero.intersects(meat1) || rHero.intersects(meat2) || rHero.intersects(meat3)){
                hit--;
                player.minusHp();
            }
            if (hit == 0) {
                hit = 20;
            } else if (hit < 20 && hit != 0) {
                hit--;
            }


            if((int) heroPositionX == 0 && (heroPositionY > 8 && heroPositionY < 11))
                sbg.enterState(9);
        }
    }

    @Override
    public int getID(){
        return 10;
    }
}
