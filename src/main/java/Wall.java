import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Wall {
    private int x;
    private int y;
    private Position position;

    public Wall(int x,int y){
        position = new Position(x,y);

    }
    public int getX() {
        return position.getX();
    }
    public int getY() {
        return position.getY();
    }
    public Position getPosition(){
     return position;
    }

    public void draw(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF55"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(),position.getY()),"W");
    }

}
