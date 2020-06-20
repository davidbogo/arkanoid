package eventhandlers;

import gameelements.Ball;
import gameelements.Block;

/**
 * The type Score tracking listener.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;
    private Counter remainingBlocks;

    /**
     * Instantiates a new Score tracking listener.
     *
     * @param scoreCounter the score counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
        this.remainingBlocks = new Counter(0);
        /* We don't want to rely on an external counter as in this case its value would be
        // dependent on the order of listeners in the notifier's list. We'll update the number
        // of blocks when it's available and we'll keep our own count of hit blocks so we
        // don't miss it when the last one has been hit */
    }

    /**
     * Sets initial block count.
     *
     * @param blocks the blocks
     */
    public void setInitialBlockCount(int blocks) {
        remainingBlocks.increase(blocks);
    }

    /**
     * @param beingHit the being hit
     * @param hitter   the hitter
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHits() == 0) {
            this.currentScore.increase(5);
            this.remainingBlocks.decrease(1);
            if (remainingBlocks.getValue() == 0) {
                this.currentScore.increase(100);
            }
        }
    }
}
