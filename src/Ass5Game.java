import eventhandlers.Counter;
import miscellaneous.Game;

/**
 * The type Ass 5 game.
 */
public class Ass5Game {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
//
    // * The entry point of application.
    // *
    // * @param args the input arguments
    // *
    public static void main(String[] args) {
        Game game = new Game(new Counter(0), 800, 600);
        game.initialize(800, 600, 20, 100, 10);
        game.run();
    }
}
