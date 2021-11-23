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
    Arena arena = new Arena(40,20);
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
        public void draw () throws IOException {
        //era private so posso chamar dentro da classe
            screen.clear();
            arena.draw(screen.newTextGraphics());
            screen.refresh();
        }
        private void processKey(KeyStroke key) throws IOException {arena.processKey(key);}



        public void run () throws IOException {
        while (Verify){
            draw();
            KeyStroke keyy = screen.readInput();
            if(keyy.getKeyType() == KeyType.Character && keyy.getCharacter() =='q') screen.close();
            if(keyy.getKeyType() == KeyType.EOF) {break;}
            processKey(keyy);
        }

        }
    }
