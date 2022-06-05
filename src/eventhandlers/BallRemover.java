package eventhandlers;

import gameelements.Ball;
import gameelements.Block;
import miscellaneous.Game;

/**
 * The type GameElements.Ball remover.
 */
public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;

    /**
     * Instantiates a new GameElements.Ball remover.
     *
     * @param game           the game
     * @param remainingBalls the remaining balls
     */
    public BallRemover(Game game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
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
        hitter.removeFromGame(this.game);
        this.remainingBalls.decrease(1);
    }
}