import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;
public class Game {

    private static Screen screen; //now it's part of the class field.
    boolean Verify = true;

    private int j = 10;
    private int y = 10;
    public Game() {

        try {
            //Terminal terminal = new DefaultTerminalFactory().createTerminal();
            TerminalSize terminalSize = new TerminalSize(40, 20);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null); // we don't need a cursor
            screen.startScreen(); // screens must be started
            screen.doResizeIfNecessary(); // resize screen if necessary

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
        private void draw () throws IOException {
        //private so posso chamar dentro da classe
            screen.clear();
            screen.setCharacter(j, y, TextCharacter.fromCharacter('X')
                    [0]);
            screen.refresh();
        }

        private void processKey(KeyStroke key) throws IOException {
            switch (key.getKeyType()) {
                case ArrowUp :y=y-1;
                case ArrowDown :y++;
                case ArrowLeft :j=y-1;
                case ArrowRight :j++;
            }
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q'){
                 Verify = false;
                 screen.close();
            }
        }
        public void run () throws IOException {
        while (Verify){
            draw();
            KeyStroke keyy = screen.readInput();
            processKey(keyy);
        }screen.close();

        }
    }
