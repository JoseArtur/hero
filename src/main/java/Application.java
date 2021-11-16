import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;
import java.io.IOException;
public class Application {

    public static void main(String[] args) throws IOException {
    Game obj = new Game();
    obj.run();
    }}

    /*private void draw() throws IOException{

        screen.clear();
        screen.setCharacter(10, 10, TextCharacter.fromCharacter('X')
                [0]);
        screen.refresh();

    }
    public void run() throws IOException {
        draw();
    }
}

*/

