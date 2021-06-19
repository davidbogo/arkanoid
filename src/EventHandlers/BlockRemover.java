package eventhandlers;

import gameelements.Ball;
import gameelements.Block;
import levels.GameLevel;

/**
 * eventHandlers.BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * Instantiates a new GameElements.Block remover.
     *
     * @param gameParam         the game level
     * @param remBlocks         the remaining blocks
     */
    public BlockRemover(GameLevel gameParam, Counter remBlocks) {
        game = gameParam;
        remainingBlocks = remBlocks;
    }

    /**
     * Blocks that are hit need to be removed from the game.
     * @param beingHit the block being hit
     * @param hitter   the hitter
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHits() == 0) {
            beingHit.removeHitListener(this);
            beingHit.removeFromGame(game);
            remainingBlocks.decrease(1);
        }
    }

    /**
     * Gets the number of remaining blocks.
     *
     * @return 	number of remaining blocks
     */
    public Counter getRemainingBlocks() {
        return remainingBlocks;
    }
}
