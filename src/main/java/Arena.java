import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {
    private int width;
    private int height;
    private Hero hero ;
    private List<Wall> walls;
    private List<Coin> coins;
    private List<Monster> monsters;



    public Arena(int width,int height){
    this.width = width;
    this.height = height;
    hero = new Hero(10,10);
    this.walls = createWalls();
    this.coins = createCoins();
    this.monsters = createMonsters();
    }
    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height - 1));
        }
        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }
        return walls;
    }
    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            coins.add(new Coin(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1));
        return coins;
    }
    private List<Monster> createMonsters() {
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < 15; i++)
            monsters.add(new Monster(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1));
        return monsters;
    }


    private void retrieveCoins(){
        IsEmpty();
        int i = 0;
        for(Coin coin:coins){
            if(coin.getPosition().equals(hero.getPosition())){
                coins.remove(i);
                IsEmpty();
                break;
            }
            i++;
        }


    }
    private void IsEmpty(){
        if(coins.isEmpty()){
            System.out.println("Congratulations, you win");
            System.exit(0);
        }
    }
    private boolean canHeroMove(Position position){
        if(position.getX() +1>=width || position.getX()<=0 || position.getY() +1 >=height ||position.getY()<=0){
            return false;
        }
        for(Wall wall:walls){
        if(wall.getPosition().equals(position)){return false;
        }}
        return true;
    }

    private void moveHero(Position position){
        if(canHeroMove(position))
        hero.setPosition(position);
    }
    private boolean canMonsterMove(Position position){
        if(position.getX() +1>=width || position.getX()<=0 || position.getY() +1 >=height ||position.getY()<=0){
            return false;
        }
        for(Wall wall:walls){
            if(wall.getPosition().equals(position)){return false;
            }}
        for(Coin coin:coins){
            if(coin.getPosition().equals(position)){return false;
            }}
        for(Monster monster:monsters){
            if(monster.getPosition().equals(position)){return false;
            }}
        return true;
    }
    private void MoveMonsters(){
        Random random = new Random();
        for(Monster monster:monsters){
            int a = random.nextInt(3)-1;
            int b = random.nextInt(3)-1;
            Position position = new Position(monster.position.getX() + a,monster.position.getY()+b);
            if(canMonsterMove(position))
            monster.move(position);

        }
    }
    private void verifyMonsterColision(){int i = 0;
        for(Monster monster:monsters){
            if(monster.getPosition().equals(hero.getPosition())){
                System.out.println("Sorry, you lost");
                System.exit(0);
                break;
            }

        }

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
}
        retrieveCoins();
        verifyMonsterColision();
        MoveMonsters();
        verifyMonsterColision();
    }
    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#ffffff"));
        graphics.fillRectangle(new TerminalPosition(0,0),new TerminalSize(width, height),' ');
        hero.draw(graphics);
        for(Coin coin:coins) coin.draw(graphics);
        for(Monster monster:monsters) monster.draw(graphics);

        for(Wall wall:walls) wall.draw(graphics);
    }}
