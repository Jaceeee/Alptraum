package alptraum;/*
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;

import java.lang.*;

*//**
 * Created by User on 11/4/2016.
 *//*
public class Hero extends Character{
    int hp;
    int mana;
    int speed;
    int maxHealth;
    int sleepiness;
    int damage;

    public int getHp() {
        return hp;
    }

    public int getMana() {
        return mana;
    }

    public int getSpeed() {
        return speed;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getSleepiness() {
        return sleepiness;
    }

    public int getDamage() {
        return damage;
    }

    public Hero(String name){
        this.name = name;
        hp = 5;
        mana = 0;
        reputation= 0.0f;
    }

    public void setName(String name){
        this.name = name;
    }

    public void HPminus(){
        hp--;
    }

    //newly added
    public void checkDamage(Rectangle a, Rectangle b){
        if(a.intersects(b)){
            hp--;
            System.out.println(hp + " mao ni si hp");
        }
    }

    public String getName(){
        return this.name;
    }
}*/
/**
 * Created by User on 11/4/2016.
 */
public class Hero extends Character{
    int hp;
    int mana;
    int speed;
    int maxHealth;
    int sleepiness;
    int damage;
    int questcounter,maxquests = 10;
    boolean[] quests,subquests;

    public void resetsubquests() {
        return;
    }

    public Hero(String name){
        this.name = name;
        hp = 5;
        mana = 0;
        reputation= 0.0f;
        boolean[] quests = new boolean[maxquests];
        boolean[] subquests = new boolean[maxquests];
        questcounter=0;
        for(int i=0;  i< 10;i++){
            quests[i] = subquests[i] = false;
        }
    }

    public int getCurrentQuest(){
        return questcounter;
    }

    public void subquests(int x){
        subquests = new boolean[x];
        for(int i = 0 ; i < x ; i++){
            subquests[i] = false;
        }
    }
    public int getHp() {
        return hp;
    }

    public void setHp(int hp) { this.hp = hp; }

    public int getMana() {
        return mana;
    }

    public int getSpeed() {
        return speed;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getSleepiness() {
        return sleepiness;
    }

    public int getDamage() {
        return damage;
    }
    public void setQuest(){
        questcounter++;
    }

    public void setQuest(int x){
        questcounter = x;
    }

    public void completeQuest(){
        quests[questcounter] = true;
    }

    public void setName(String name){
        this.name = name;
    }

    public void minusHp() { hp--; }

    public String getName(){
        return this.name;
    }
}
