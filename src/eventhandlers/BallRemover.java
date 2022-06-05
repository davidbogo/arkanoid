package eventhandlers;

import gameelements.Ball;
import gameelements.Block;
import levels.GameLevel;

/**
 * The type gameElements.Ball remover.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private eventhandlers.Counter remainingBalls;

    /**
     * Instantiates a new GameElements.Ball remover.
     *
     * @param gameParam     the game
     * @param remBalls      the remaining balls
     */
    public BallRemover(GameLevel gameParam, eventhandlers.Counter remBalls) {
        game = gameParam;
        remainingBalls = remBalls;
    }

    /**
    * Balls that hit the special blocks associated with EventHandlers.BallRemover need to be removed
    * from the game. hitEvent() does it.
    *
    * @param beingHit the block hit by the ball
    * @param hitter the ball hitting the death block
    */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        remainingBalls.decrease(1);
    }
}
