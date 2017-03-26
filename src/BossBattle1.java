import alptraum.level1.Ghost;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import java.awt.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import java.awt.*;

/**
 * Created by Juan Carlos on 11/13/2016.
 */

public class BossBattle1 extends BasicGameState {
    private static final int MAXSHOTS = 5;
    //private static final float ORBSPEED = 5;

    private int shot;
    private boolean playerMoved;
    private boolean launched;
    private Image bossBattleMap;
    private Image playerOrb;
    private Image computerOrb;
    private Image textBox;
    private TrueTypeFont ttf;
    private Ghost rensarrews;
    private Font font;
    private boolean talk;

    private ShotBuffer[] shotBuffer;
    private EnemyOrb enemyOrb;
    private float playerPosX = 360;
    private float playerPosY = 202;
    private float compPosX = 360;
    private float compPosY = 20;
    private String mouse = "";
    private float x;
    private float y;

    Rectangle player1, player2, shotBounds;

    public BossBattle1(int state) {

    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        //TODO figure out map
        talk = true;
        playerOrb = new Image("res/playerWisp1.png");
        computerOrb = new Image("res/launchedShot3.png");
        bossBattleMap = new Image("res/Sanctuary_of_the_Magic_Crest.png");
        textBox = new Image("res/etc/textbox1.png");

        shotBuffer = new ShotBuffer[MAXSHOTS];
        playerMoved = false;
        enemyOrb = new EnemyOrb(compPosX,compPosY,1,1);
        enemyOrb.start();
        player1 = new Rectangle(playerPosX,playerPosY,50,50);
        player2 = new Rectangle(compPosX,compPosY,50,50);
        shotBounds = new Rectangle(0,0,0,0);

//        rensarrews = new Ghost(17, 4);
//        rensarrews.setCtrMessages();
//        font = new Font("Century Gothic", java.awt.Font.BOLD, 20);
//        ttf = new TrueTypeFont(font, true);
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        bossBattleMap.draw(0, 0, 0.9f);
        if (shotBuffer[0] != null && launched) {
            shotBuffer[0].draw(shotBuffer[0].getX(), shotBuffer[0].getY(), .2f);
            shotBuffer[0].run();
        }

        playerOrb.draw(playerPosX, playerPosY, 0.8f);
        computerOrb.draw(compPosX, compPosY, 0.8f);

        g.drawString("Hero xPosition:" + playerPosX + "\nHero yPosition: " + playerPosY, 400, 20);
        g.drawString(mouse, 500, 500);
        player1.setBounds(playerPosX,playerPosY,50,50);
        player2.setBounds(compPosX,compPosY,50,50);
        g.draw(player1);
        g.draw(player2);

//        textBox.draw(0, 9 * 32, 720, 115);
//        ttf.drawString(3 * 32, 10 * 32, rensarrews.Interact(rensarrews.getMessages()), org.newdawn.slick.Color.white);

//        if(talk){
//            if (rensarrews.getMessages() != 5) {
//
//                if (rensarrews.getMessages() == 2 || rensarrews.getMessages() == 1) {
//                    rensarrews.getImage(1).draw(19 * 32, 6 * 32, 2.75f);
//                } else if (rensarrews.getMessages() == 4) {
//                    rensarrews.getImage(2).draw(19 * 32, 6 * 32, 2.75f);
//                } else {
//                    rensarrews.getImage(0).draw(19 * 32, 6 * 32, 2.75f);
//                }
//
//                textBox.draw(0, 9 * 32, 720, 115);
//                ttf.drawString(3 * 32, 10 * 32, rensarrews.Interact(rensarrews.getMessages()), org.newdawn.slick.Color.white);
//
//            } else {
//                rensarrews.setCtrMessages(4);
//            }
//        }
        if(launched){
            g.draw(shotBounds);
        }
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input input = gc.getInput();
        compPosX = enemyOrb.getX();
        enemyOrb.move();
        if (!(launched && playerMoved)) {
            for (int i = 0; i < MAXSHOTS; i++) {
                shotBuffer[i] = new ShotBuffer();
            }
        }
        if (input.isKeyDown(input.KEY_UP)) {
            playerPosY -= delta * .1f;
            if (playerPosY < 180.58) {
                playerPosY += delta * .1f;
            }
            playerMoved = true;
        }
        if (input.isKeyDown(input.KEY_DOWN)) {
            playerPosY += delta * .1f;
            if (playerPosY > 362) {
                playerPosY -= delta * .1f;
            }
            playerMoved = true;
        }
        if (input.isKeyDown(input.KEY_RIGHT)) {
            playerPosX += delta * .1f;
            if (playerPosX > 680) {
                playerPosX -= delta * .1f;
            }
            playerMoved = true;
        }
        if (input.isKeyDown(input.KEY_LEFT)) {
            playerPosX -= delta * .1f;
            if (playerPosX < 0.5) {
                playerPosX += delta * .1f;
            }
            playerMoved = true;
        }

        float x = /*720 - */Mouse.getX();//mouse coordinates like cartesian
        float y = 405 - Mouse.getY();

        mouse = "Mouse Position\n\t x: " + x + "\t y: " + y;

        if (Mouse.isButtonDown(0) && playerMoved) {
            if (shotBuffer[0] != null) {
                shotBuffer[0].setX(playerPosX - 30);
                shotBuffer[0].setY(playerPosY + 30);
                shotBuffer[0].launch(x, y, delta);
            }
            launched = true;
        }

        if (shotBuffer[0].getX() < 0 || shotBuffer[0].getX() > 720 || shotBuffer[0].getY() < 0 || shotBuffer[0].getY() > 405) {
            shotBuffer[0].halt();
            System.out.println("X: " + shotBuffer[0].getX() + " Y: " + shotBuffer[0].getY());
            launched = false;
            shotBuffer[0].setX(0);
            shotBuffer[0].setY(0);
        }

        if(launched){
            shotBounds.setBounds(shotBuffer[0].getX(),shotBuffer[0].getY(),15,15);
            if(shotBounds.intersects(player2)){
                enemyOrb.subtractHP();
                launched = false;
            }
        }

        if(enemyOrb.getHp() == 0){
            //TODO do something
            //TODO like transport to another world
            sbg.enterState(3, new FadeOutTransition(Color.white,3000), new FadeInTransition(Color.white,3000));
        }

    }

    public int getID() {
        return 16;
    }
}