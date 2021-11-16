import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class Arena {
    private int width;
    private int height;
    Hero hero ;
    private static Screen screen; //now it's part of the class field.



    public Arena(int width,int height){
    this.width = width;
    this.height = height;
        hero = new Hero(10,10);
    }
    public boolean canHeroMove(Position position){
        if(position.getX()>=width || position.getX()<=0 || position.getY()>=height ||position.getY()<0){
            return false;
        }
        else return true;
    }
    public void moveHero(Position position){
        if(canHeroMove(position))
        hero.setPosition(position);
    }

    public void processKey(KeyStroke key) throws IOException {
        switch (key.getKeyType()) {
            case ArrowUp :moveHero(hero.moveUp());
                break;
            case ArrowDown :moveHero(hero.moveDown());
                break;
            case ArrowLeft :moveHero(hero.moveLeft());
                break;
            case ArrowRight :moveHero(hero.moveRight());
                break;
}}
    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0,0),new TerminalSize(width, height),' ');


        screen.setCharacter(hero.getX(), hero.getY(), TextCharacter.fromCharacter('X')[0]);
    }}
