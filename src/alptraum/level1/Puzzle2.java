package alptraum.level1;

/**
 * Created by Loewe on 11/10/2016.
 */

import java.awt.Font;
import alptraum.Camera;
import alptraum.Hero;
import org.newdawn.slick.*;
//import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.tiled.TiledMap;

public class Puzzle2 extends BasicGameState{
    private Animation hero,steady, movingUp, movingDown,movingRight, movingLeft;
    private Ghost rensarrews;
    private Animation ghost;
    private Font font;
    private boolean accident;
    private TrueTypeFont ttf;
    private boolean interaction;
    private static final int TILEWIDTH = 32;
    private static final int TILEHEIGHT = 32;
    private int[] duration = {200,200,200}; // an animation is a series of frames(milliseconds)
    private int[] duration2 = {1000,100,100};
    private float heroPositionX = 5f;// keep track of position of hero
    private float heroPositionY = 11f;
    private  float heroW = 20.0f;
    private  float heroL = 27.0f;
    private Rectangle weapon1, weapon2, weapon3, food1,ghost1;
    private Image hp,textbox;
    private Image swords;
    private Rectangle rHero;
    private TiledMap cave2;
    private static final int NUMBEROFLAYERS = 5;
    private static final float SPEED = 0.0025f;
    private Hero player;
    private Camera camera;
    private boolean[][] blocked;
    private Image cake1;
    private boolean diagupright, diagdownright, diagdownleft, diagupleft, diagdown1,diagup1;
    private boolean interaction1;
    private boolean upmove, downmove,start;
    private float sword1moveX = 19, sword2moveX = 3, sword3moveX = 10f;
    private float sword1moveY = 7, sword2moveY = 6, sword3moveY = 3f;
    private float ghostposX = 10, ghostposY = 2;
    private boolean takecake;
    private boolean movenow;
    private int hit;
    public Puzzle2(int state, Hero player) throws SlickException{
        this.player = player;
    }

    private void initializeBlocked() {
        for (int l = 0; l < NUMBEROFLAYERS; l++) {
            String layerValue = cave2.getLayerProperty(l, "blocked", "false");
            if (layerValue.equals("true")) {
                for (int c = 0; c < cave2.getHeight(); c++) {
                    for(int r = 0; r < cave2.getWidth(); r++) {
                        if(cave2.getTileId(r, c, l) != 0) {
                            blocked[r][c] = true;
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
        ghost1 = new Rectangle(0,0,0,0);
        weapon1 = new Rectangle(0,0,0,0);
        weapon2 = new Rectangle(0,0,0,0);
        weapon3 = new Rectangle(0,0,0,0);
        rHero = new Rectangle(15,0,heroW,heroL);
        cave2 = new TiledMap("res/background/puzzle1/cave1Final.tmx");
        blocked = new boolean[cave2.getWidth()][cave2.getHeight()];
        initializeBlocked();
        hit = 3000;
        takecake = false;
        movenow = false;
        start = diagupright = diagdown1 = true;
        diagdownright = diagdownleft = diagupleft = true;
        interaction1 = upmove = diagup1 = interaction = false;
        downmove = true;
        accident = false;
        font = new Font("Century Gothic", Font.BOLD,20);
        ttf = new TrueTypeFont(font,true);
        textbox = new Image("res/etc/textbox1.png");
        cake1 = new Image("res/background/puzzle1/FOOD/Cake.png");
        swords = new Image("res/background/puzzle1/FOOD/Sword.png");
        hp = new Image("res/background/puzzle1/FOOD/heart.png");

        rensarrews = new Ghost(ghostposX,ghostposY);
        rensarrews.setCtrMessages(10);

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
        camera = new Camera(container,cave2);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        camera.translateGraphics();
        camera.drawMap(0);
        camera.drawMap(1);
        camera.drawMap(2);
        camera.drawMap(5);
        ghost.draw(rensarrews.ghostposX * 32, rensarrews.ghostposY * 32);
        hero.draw(heroPositionX * 32, heroPositionY * 32);
        camera.drawMap(4);
        swords.draw(sword1moveX * 32, sword1moveY * 32);
        swords.draw(sword2moveX * 32, sword2moveY * 32);
        swords.draw(sword3moveX * 32, sword3moveY * 32);

        if(!takecake) {
            cake1.draw(12 * TILEWIDTH, 6 * TILEHEIGHT, 25, 25);
            food1 = new Rectangle(12 * TILEWIDTH,6 * TILEHEIGHT,25,25);
            g.draw(food1);
        }

        ghost1 = new Rectangle(rensarrews.ghostposX * 32, (rensarrews.ghostposY + 0.4f)* 32,25,25);
        weapon1 = new Rectangle(sword1moveX * 32, sword1moveY * 32, 25,25);
        weapon2 = new Rectangle(sword2moveX * 32, sword2moveY * 32,25,25);
        weapon3 = new Rectangle(sword3moveX * 32, sword3moveY * 32,25,25);
        rHero = new Rectangle(heroPositionX * TILEWIDTH - 1,heroPositionY * TILEHEIGHT,heroW,heroL);

        if(player.getHp() == 5) {hp.draw(7 * TILEWIDTH,1 * TILEHEIGHT,20,15);}
        if(player.getHp() >= 4) {hp.draw(6 * TILEWIDTH, 1 * TILEHEIGHT, 20, 15);}
        if(player.getHp() >= 3) {hp.draw(5 * TILEWIDTH,1 * TILEHEIGHT,20,15);}
        if(player.getHp() >= 2) {hp.draw(4 * TILEWIDTH,1 * TILEHEIGHT,20,15);}
        if(player.getHp() >= 1) {hp.draw(3 * TILEWIDTH,1 * TILEHEIGHT,20,15);}

        g.drawString("HEALTH: ", 1 * TILEWIDTH, 1 * TILEHEIGHT);
        g.drawString("hero X position: "+heroPositionX+"\nhero Y position: "+heroPositionY,400,200);
        g.drawString("HERO NAME: "+player.getName()+" ",100,100);

        g.draw(rHero);
        g.draw(weapon1);
        g.draw(weapon2);
        g.draw(weapon3);
        g.draw(ghost1);

        if(start){
            if(rensarrews.getMessages() <= 12){
                rensarrews.getImage(0).draw(19 * TILEWIDTH, 6 * TILEHEIGHT, 2.75f);
                textbox.draw(0, 9 * TILEHEIGHT, 720, 115);
                ttf.drawString(3 * TILEWIDTH, 10 * TILEHEIGHT, rensarrews.Interact(rensarrews.getMessages()), org.newdawn.slick.Color.white);
            } else {
                movenow = true;
                rensarrews.setCtrMessages(12);
                start = false;
            }
        }

        if(interaction){
            if(rensarrews.getMessages() == 9) {
                rensarrews.getImage(2).draw(19 * TILEWIDTH, 6 * TILEHEIGHT, 2.75f);
                textbox.draw(0, 9 * TILEHEIGHT, 720, 115);
                ttf.drawString(3 * TILEWIDTH, 10 * TILEHEIGHT, rensarrews.Interact(9), org.newdawn.slick.Color.white);

            } else if(rensarrews.getMessages() == 15) {
                rensarrews.getImage(3).draw(19 * TILEWIDTH, 6 * TILEHEIGHT, 2.75f);
                textbox.draw(0, 9 * TILEHEIGHT, 720, 115);
                ttf.drawString(3 * TILEWIDTH, 10 * TILEHEIGHT, rensarrews.Interact(15), org.newdawn.slick.Color.white);

            } else{
                movenow = true;
                interaction = false;
            }
        }

        if(interaction1) {
            if (rensarrews.getMessages() <= 15) {
                rensarrews.getImage(2).draw(19 * TILEWIDTH, 6 * TILEHEIGHT, 2.75f);
                textbox.draw(0, 9 * TILEHEIGHT, 720, 115);
                ttf.drawString(3 * TILEWIDTH, 10 * TILEHEIGHT, rensarrews.Interact(rensarrews.getMessages()), org.newdawn.slick.Color.white);

            } else {
                rensarrews.setCtrMessages(14);
                accident = true;
                interaction1 = false;
                interaction = true;
            }
        }
    }

    private void swordmove(int delta){
        if((int) sword1moveY == 6) {    //sword1
            downmove = true;
        }
        if(downmove && (int) sword1moveY != 10){
            sword1moveY += delta * 0.0025f;
            upmove = true;
        } else if(upmove && (int) sword1moveY != 6){
            sword1moveY -= delta * 0.0025f;
            downmove = false;
        }
        //sword2
        if(diagdown1 && (int) sword2moveX != 6 && (int) sword2moveY != 9){
            sword2moveX += delta * 0.005f;
            sword2moveY += delta * 0.005f;
            diagup1 = true;
        } else if(diagup1 && (int) sword2moveX != 3 && (int) sword2moveY != 6){
            sword2moveX -= delta * 0.0025f;
            sword2moveY -= delta * 0.0025f;
            diagdown1 = false;
        } else {
            diagdown1 = true;
        }

        //sword3
        if((int) sword3moveX != 15 && (int) sword3moveY == 3){
            //move right
            sword3moveX += delta * 0.0095f;
        } else if((int) sword3moveX == 15 && (int) sword3moveY != 8){
            //move down
            sword3moveY += delta * 0.0095f;

        } else if((int) sword3moveY == 8 && (int) sword3moveX != 9){
            //move left
            sword3moveX -= delta  * 0.015f;

        } else if((int) sword3moveX == 9 && (int) sword3moveY != 3){
            //move up
            sword3moveY -= delta * 0.015f;
        }
    }


    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input input = gc.getInput();
        swordmove(delta);

        if(player.getHp() == 0){
            sbg.enterState(7, new FadeOutTransition(org.newdawn.slick.Color.white, 3000), new FadeInTransition(org.newdawn.slick.Color.white, 3000));
            player.setHp(5);
            heroPositionX = 5f;
            heroPositionY = 11f;
            takecake = true;
        }

        if(start){
            if(input.isKeyPressed(Input.KEY_I)){
                rensarrews.addMessage();
            }
        }
        else {
            //interacts with ghost1
            if(rHero.intersects(ghost1)){
                if (input.isKeyPressed(Input.KEY_I)) {
                    if(accident){
                        if(rensarrews.getMessages() > 15){
                            rensarrews.setCtrMessages(14);
                        }
                    } else {
                        if(rensarrews.getMessages() > 9){
                            rensarrews.setCtrMessages(8);
                        }
                    }
                    movenow = false;
                    interaction = true;
                    rensarrews.addMessage();
                }
            }
            //interacts with the cake
            else if(!accident && rHero.intersects(food1)){
                if(input.isKeyPressed(Input.KEY_I)){
                    if(!takecake){
                        takecake = true;
                        player.minusHp();
                    }
                    if(rensarrews.getMessages() < 13){
                        rensarrews.setCtrMessages(12);
                    }
                    movenow = false;
                    interaction1 = true;
                    rensarrews.addMessage();
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
                    if (!isBlocked(heroPositionX + delta * SPEED + 0.6f, heroPositionY)) {
                        heroPositionX += delta * SPEED;
                    }
                } else {
                    hero = steady;
                }

                if ((int) heroPositionY == 12 && ((int) heroPositionX >= 4 && (int) heroPositionX <= 6)) {
                    sbg.enterState(6);
                }
                if ((int) heroPositionX == 22 && ((int) heroPositionY >= 7 && (int) heroPositionY <= 10)) {
                    sbg.enterState(8);
                }

                if(hit == 3000 && (rHero.intersects(weapon1) || rHero.intersects(weapon2) || rHero.intersects(weapon3))){
                    hit--;
                    player.minusHp();
                }
                if(hit == 0){
                    hit = 3000;
                } else if(hit < 3000 && hit != 0){
                    hit--;
                }
            }
        }
    }

    @Override
    public int getID() {
        return 7;
    }
}
