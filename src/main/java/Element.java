import com.googlecode.lanterna.graphics.TextGraphics;

abstract class Element {

    protected Position position;

    public Element(int x, int y) {
        position = new Position(x, y);
    }

    abstract void draw(TextGraphics graphics);

    public void setPosition(Position position) {
        this.position.setX(position.getX());
        this.position.setY(position.getY());

    }

    public Position getPosition() {
        return position;
    }
}
