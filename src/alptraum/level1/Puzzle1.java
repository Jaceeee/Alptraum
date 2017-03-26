package alptraum.level1;

/**
 * Created by Loewe on 11/10/2016.
 * <p>
 * //TODO adding saving points
 **/

/**
 //TODO adding saving points
 **/

//newly added function in hero is checkDamage please check

import alptraum.Camera;
import alptraum.Hero;
import org.newdawn.slick.*;

import java.awt.Font;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.tiled.TiledMap;

public class Puzzle1 extends BasicGameState {
    Animation hero, steady, movingUp, movingDown, movingRight, movingLeft;
    private TiledMap cave1;
    private static final int TILEWIDTH = 32;
    private static final int TILEHEIGHT = 32;
    int[] duration = {200, 200, 200}; // an animation is a series of frames(milliseconds)
    int[] duration2 = {1000, 100, 100};
    Animation ghost;
    Ghost rensarrews;

    private float ghostPositionX = 17;
    private float ghostPositionY = 4;
    private float heroPositionX = 20f;      // keep track of position of hero
    private float heroPositionY = 10f;
    float heroW = 20.0f;
    float heroL = 27.0f;
    Hero player;
    Camera camera;
    Font font;
    TrueTypeFont ttf;

    //Health health;
    private int hit;
    private boolean movenow;
    private boolean[][] blocked;
    private boolean start;
    private static final int NUMBEROFLAYERS = 6;
    private static final float SPEED = 0.0025f;
    private boolean interaction;
    //boolean moreleft,moreright,moreUp,moreDown;
    private Rectangle rHero;
    private Rectangle weapon1, weapon2, weapon3, weapon4, ghost1;
    private Image swords;
    private Image hp, textbox;
    private float swordMovementX = 3f;
    private float swordMovementY = 6f;
    private float swordMovementX1 = 7f;
    private float swordMovementY1 = 10f;
    private float swordMovementX2 = 16f;
    private float swordMovementY2 = 5f;
    private float swordMovementX3 = 3f;
    private float swordMovementY3 = 4f;
    private boolean diagdown, diagup, up1, down1, right, left;

    public Puzzle1(int state, Hero player) throws SlickException {
        this.player = player;
    }

    private void initializeBlocked() {
        for (int l = 0; l < NUMBEROFLAYERS; l++) {
            String layerValue = cave1.getLayerProperty(l, "blocked", "false");
            if (layerValue.equals("true")) {
                for (int c = 0; c < cave1.getHeight(); c++) {
                    for (int r = 0; r < cave1.getWidth(); r++) {
                        if (cave1.getTileId(r, c, l) != 0) {
                            blocked[r][c] = true;
                        }
                    }
                }
            }
        }
    }

    private boolean isBlocked(float x, float y) {
        int xBlock = (int) x /* TILEWIDTH*/;
        int yBlock = (int) y /* TILEHEIGHT*/;
        return blocked[xBlock][yBlock];
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        ghost1 = new Rectangle(0, 0, 0, 0);
        weapon1 = new Rectangle(0, 0, 0, 0);
        weapon2 = new Rectangle(0, 0, 0, 0);
        weapon3 = new Rectangle(0, 0, 0, 0);
        weapon4 = new Rectangle(0, 0, 0, 0);
        rHero = new Rectangle(0, 15, heroW, heroL);
        start = true;
        right = down1 = true;
        interaction = up1 = left = false;
        movenow = false;
        hit = 3000;
        rensarrews = new Ghost(ghostPositionX, ghostPositionY);
        font = new Font("Century Gothic", Font.BOLD, 20);
        ttf = new TrueTypeFont(font, true);

        textbox = new Image("res/etc/textbox1.png");
        cave1 = new TiledMap("res/background/puzzle1/Cave1part2Final.tmx", "res/background/puzzle1");
        swords = new Image("res/background/puzzle1/FOOD/Sword.png");
        hp = new Image("res/background/puzzle1/FOOD/heart.png");
        diagdown = false;
        diagup = true;
        blocked = new boolean[cave1.getWidth()][cave1.getHeight()];

        initializeBlocked();
        Image[] ghostSteady = {new Image("res/characters/Ghost/0.png"), new Image("res/characters/Ghost/2.png"), new Image("res/characters/Ghost/left.png")};
        Image[] heroSteady = {new Image("res/characters/hero/0.png"), new Image("res/characters/hero/3.png"), new Image("res/characters/hero/4.png")};
        Image[] walkUp = {new Image("res/characters/hero/2.png"), new Image("res/characters/hero/11.png"), new Image("res/characters/hero/12.png")};
        Image[] walkLeft = {new Image("res/characters/hero/1.png"), new Image("res/characters/hero/9.png"), new Image("res/characters/hero/10.png")};
        Image[] walkRight = {new Image("res/characters/hero/R1.png"), new Image("res/characters/hero/R2.png"), new Image("res/characters/hero/R3.png")};
        Image[] walkDown = {new Image("res/characters/hero/0.png"), new Image("res/characters/hero/7.png"), new Image("res/characters/hero/8.png")};

        ghost = new Animation(ghostSteady, duration, true);
        movingRight = new Animation(walkRight, duration, true);
        movingUp = new Animation(walkUp, duration, true);
        movingLeft = new Animation(walkLeft, duration, true);
        movingDown = new Animation(walkDown, duration, true);
        steady = new Animation(heroSteady, duration2, true);
        hero = steady;
        camera = new Camera(container, cave1);
        System.out.println(cave1.getLayerCount() + " mao ni si layer count");
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        camera.translateGraphics();
        camera.drawMap(0);
        camera.drawMap(2);
        camera.drawMap(3);
        camera.drawMap(5);
        ghost.draw(rensarrews.ghostposX * 32, rensarrews.ghostposY * 32);
        hero.draw(heroPositionX * TILEWIDTH, heroPositionY * TILEHEIGHT);
        camera.drawMap(4);

        swords.draw(swordMovementX * 32, swordMovementY * 32);
        swords.draw(swordMovementX1 * 32, swordMovementY1 * 32);
        swords.draw(swordMovementX2 * 32, swordMovementY2 * 32);
        swords.draw(swordMovementX3 * 32, swordMovementY3 * 32);
        //player HP
        weapon1 = new Rectangle(swordMovementX * 32, swordMovementY * 32, 25, 25);
        weapon2 = new Rectangle(swordMovementX1 * 32, swordMovementY1 * 32, 25, 25);
        weapon3 = new Rectangle(swordMovementX2 * 32, swordMovementY2 * 32, 25, 25);
        weapon4 = new Rectangle(swordMovementX3 * 32, swordMovementY3 * 32, 25, 25);
        ghost1 = new Rectangle(rensarrews.ghostposX * 32, (rensarrews.ghostposY + 0.4f) * 32, 25, 25);

        g.draw(ghost1);
        if (player.getHp() == 5) {
            hp.draw(7 * TILEWIDTH, 12 * TILEHEIGHT, 20, 15);
        }
        if (player.getHp() >= 4) {
            hp.draw(6 * TILEWIDTH, 12 * TILEHEIGHT, 20, 15);
        }
        if (player.getHp() >= 3) {
            hp.draw(5 * TILEWIDTH, 12 * TILEHEIGHT, 20, 15);
        }
        if (player.getHp() >= 2) {
            hp.draw(4 * TILEWIDTH, 12 * TILEHEIGHT, 20, 15);
        }
        if (player.getHp() >= 1) {
            hp.draw(3 * TILEWIDTH, 12 * TILEHEIGHT, 20, 15);
        }

        g.draw(weapon1);
        g.draw(weapon2);
        g.draw(weapon3);
        g.draw(weapon4);
        rHero = new Rectangle(heroPositionX * TILEWIDTH - 1, heroPositionY * TILEHEIGHT, heroW, heroL);

        g.draw(rHero);
        g.drawString("HEALTH: ", 1 * TILEWIDTH, 11.8f * TILEHEIGHT);
        g.drawString("hero X position: " + heroPositionX + "\nhero Y position: " + heroPositionY, 400, 200);
        g.drawString("HERO NAME: " + player.getName() + " ", 100, 100);

        if (start) {
            if (rensarrews.getMessages() != 5) {

                if (rensarrews.getMessages() == 2 || rensarrews.getMessages() == 1) {
                    rensarrews.getImage(1).draw(19 * TILEWIDTH, 6 * TILEHEIGHT, 2.75f);
                } else if (rensarrews.getMessages() == 4) {
                    rensarrews.getImage(2).draw(19 * TILEWIDTH, 6 * TILEHEIGHT, 2.75f);
                } else {
                    rensarrews.getImage(0).draw(19 * TILEWIDTH, 6 * TILEHEIGHT, 2.75f);
                }

                textbox.draw(0, 9 * TILEHEIGHT, 720, 115);
                ttf.drawString(3 * TILEWIDTH, 10 * TILEHEIGHT, rensarrews.Interact(rensarrews.getMessages()), org.newdawn.slick.Color.white);

            } else {
                rensarrews.setCtrMessages(4);
                start = false;
                movenow = true;
            }
        }

        if (interaction) {
            if (rensarrews.getMessages() <= 9) {
                if (rensarrews.getMessages() == 7 || rensarrews.getMessages() == 9) {
                    rensarrews.getImage(2).draw(19 * TILEWIDTH, 6 * TILEHEIGHT, 2.75f);

                } else {
                    rensarrews.getImage(0).draw(19 * TILEWIDTH, 6 * TILEHEIGHT, 2.75f);
                }

                textbox.draw(0, 9 * TILEHEIGHT, 720, 115);
                ttf.drawString(3 * TILEWIDTH, 10 * TILEHEIGHT, rensarrews.Interact(rensarrews.getMessages()), org.newdawn.slick.Color.white);
            } else {
                movenow = true;
                interaction = false;
            }
        }
    }

    private void moveSword(int delta) {
        //sword1
        if ((int) swordMovementY == 6 && (int) swordMovementX != 6) {
            swordMovementX += delta * 0.004f;
        } else if ((int) swordMovementX == 6 && (int) swordMovementY != 9) {
            swordMovementY += delta * 0.004f;
        } else if ((int) swordMovementY == 9 && (int) swordMovementX != 3) {
            swordMovementX -= delta * 0.004f;
        } else if ((int) swordMovementX == 3 && swordMovementY != 6)
            swordMovementY -= delta * 0.004f;
        //sword2
        if (diagup && (int) swordMovementX1 != 11 && (int) swordMovementY1 != 6) {
            swordMovementY1 -= delta * 0.004f;
            swordMovementX1 += delta * 0.004f;
            diagdown = true;
        } else if (diagdown && (int) swordMovementX1 != 7 && (int) swordMovementY1 != 10) {
            diagup = false;
            swordMovementY1 += delta * 0.004f;
            swordMovementX1 -= delta * 0.004f;
        } else {
            diagup = true;
        }
        //sword3
        if (down1 && (int) swordMovementY2 != 10) {
            swordMovementY2 += delta * 0.00065f;
            up1 = true;
        } else if (up1 && (int) swordMovementY2 != 5) {
            swordMovementY2 -= delta * 0.00065f;
            down1 = false;
        } else {
            down1 = true;
        }
        //sword4
        if (right && (int) swordMovementX3 != 5) {
            swordMovementX3 += delta * 0.00065f;
            left = true;
        } else if (left && (int) swordMovementX3 != 1) {
            swordMovementX3 -= delta * 0.00065f;
            right = false;
        } else {
            right = true;
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input input = gc.getInput();
        moveSword(delta);
        if (player.getHp() == 0) {
            sbg.enterState(6, new FadeOutTransition(org.newdawn.slick.Color.white, 500), new FadeInTransition(org.newdawn.slick.Color.white, 500));
            player.setHp(5);
            heroPositionX = 20f;
            heroPositionY = 10f;
            start = true;
            rensarrews.setCtrMessages(0);
        }
        if (start) {
            if (input.isKeyPressed(Input.KEY_I)) {
                rensarrews.addMessage();
            }
        } else {
            if (rHero.intersects(ghost1)) {

                if (input.isKeyPressed(Input.KEY_I)) {
                    if (rensarrews.getMessages() > 9) {
                        rensarrews.setCtrMessages(4);

                    }
                    interaction = true;
                    rensarrews.addMessage();
                    movenow = false;
                }
            }

            if (movenow) {
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
            }
        }
        if (((int) heroPositionX >= 3 && (int) heroPositionX <= 5) && (int) heroPositionY == 0) {
            sbg.enterState(7);
        }
        if ((int) heroPositionX == 22 && ((int) heroPositionY >= 2 && (int) heroPositionY <= 6)) {
            sbg.enterState(11);
        }
        if (hit == 3000 && (rHero.intersects(weapon1) || rHero.intersects(weapon2) || rHero.intersects(weapon3) || rHero.intersects(weapon4))) {
            hit--;
            player.minusHp();
        }
        if (hit == 0) {
            hit = 3000;
        } else if (hit < 3000 && hit != 0) {
            hit--;
        }
    }

    @Override
    public int getID() {
        return 6;
    }
}
