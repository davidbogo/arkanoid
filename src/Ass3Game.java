/**
 * The type Ass 3 game.
 */
public class Ass3Game {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize(800, 600, 20, 100, 10);
        game.run();
    }
}
