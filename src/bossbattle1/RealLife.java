package bossbattle1; /**
 * Created by Juan Carlos on 10/20/2016.
 */

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class RealLife extends BasicGameState {
    Animation hero, movingUp, movingDown, movingLeft, movingRight;
    Image worldMap;
    boolean quit = false;
    int[] duration = {200, 200};
    float heroPositionX = 0;
    float heroPositionY = 0;
    float shiftX = heroPositionX + 540;
    float shiftY = heroPositionY + 360;

    Image mushroom;
    int objX = 200;
    int objY = 200;
    public String title = "Welcome to Alptraum!";

    public RealLife(int state) {
        //housekeeping stuff
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        worldMap = new Image("res/town.png");

        Image[] walkUp = {new Image("res/heroUp.png"), new Image("res/heroUp.png")};
        Image[] walkDown = {new Image("res/heroDown.png"), new Image("res/heroDown.png")};
        Image[] walkLeft = {new Image("res/heroLeft.png"), new Image("res/heroLeft.png")};
        Image[] walkRight = {new Image("res/heroRight.png"), new Image("res/heroRight.png")};

        movingUp = new Animation(walkUp, duration, false);
        movingDown = new Animation(walkDown, duration, false);
        movingLeft = new Animation(walkLeft, duration, false);
        movingRight = new Animation(walkRight, duration, false);

        hero = movingDown;

        mushroom = new Image("res/mushroom.png");
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        worldMap.draw(heroPositionX, heroPositionY);
        hero.draw(shiftX, shiftY);

        g.drawString("Hero xPosition:" + heroPositionX + "\nHero yPosition: " + heroPositionY, 400, 20);

        if (quit == true) {
            g.drawString("Resume (R)", 250, 100);
            g.drawString("Main Menu (M)", 250, 150);
            g.drawString("Quit Game (Q)", 250, 200);
            if (quit == false) {
                g.clear();
            }
        }
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input input = gc.getInput();

        if (input.isKeyDown(input.KEY_UP)) {
            hero = movingUp;
            heroPositionY += delta * .1f;
            if (heroPositionY > 373) {
                heroPositionY -= delta * .1f;
            }
        }

        if (input.isKeyDown(input.KEY_DOWN)) {
            hero = movingDown;
            heroPositionY -= delta * .1f;
            if (heroPositionY < -497) {
                heroPositionY += delta * .1f;
            }
        }
        if (input.isKeyDown(input.KEY_RIGHT)) {
            hero = movingRight;
            heroPositionX -= delta * .1f;
            if (heroPositionX < -224) {
                heroPositionX += delta * .1f;
            }
        }
        if (input.isKeyDown(input.KEY_LEFT)) {
            hero = movingLeft;
            heroPositionX += delta * .1f;
            if (heroPositionX > 537) {
                heroPositionX -= delta * .1f;
            }
        }
        if (input.isKeyDown(input.KEY_ESCAPE)) {
            quit = true;
        }

        if(quit == true){
            if(input.isKeyDown(input.KEY_R)){
                quit = false;
            }
            if(input.isKeyDown(input.KEY_M)){
                sbg.enterState(0);
            }
            if(input.isKeyDown(input.KEY_Q)){
                System.exit(0);
            }
        }
    }

    public int getID() {
        return 1;
    }
}

