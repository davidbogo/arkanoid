package eventhandlers;

import gameelements.Ball;
import gameelements.Block;
import miscellaneous.Game;

/**
 * The type GameElements.Ball remover.
 */
public class BallRemover implements HitListener {
    private Game game;
    private eventhandlers.Counter remainingBalls;

    /**
     * Instantiates a new GameElements.Ball remover.
     *
     * @param gameParam     the game
     * @param remBalls      the remaining balls
     */
    public BallRemover(Game gameParam, eventhandlers.Counter remBalls) {
        game = gameParam;
        remainingBalls = remBalls;
    }

    // Balls that hit the special blocks associated with EventHandlers.BallRemover need to be removed
    //from the game.
    /**
    *hitEvent removes hit balls from the game.
    *
     * @param beingHit the block hit by the ball
     * @param hitter the ball hittind the death block
    */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        remainingBalls.decrease(1);
    }
}
