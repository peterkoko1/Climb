import java.util.*;


/**
 * The type Climbing frame.
 */
public class ClimbingFrame {
    public String frameSet;
    /**
     * The Frames.
     */
    Set<String> frames;
    /**
     * The Difficulty.
     */
    int difficulty;

    /**
     * Instantiates a new Climbing frame.
     *
     * @param frames     the frames
     * @param difficulty the difficulty
     */
    ClimbingFrame(Set<String> frames, int difficulty) {
        this.frames = frames;
        this.difficulty = difficulty;
    }

    public ClimbingFrame() {

    }

    /**
     * Gets frames.
     *
     * @return the frames
     */
    Set<String> getFrames() {
        return frames;
    }

    /**
     * Gets difficulty.
     *
     * @return the difficulty
     */
    int getDifficulty() {
        return difficulty;
    }

    public String getFrameSet() {
        return frameSet;
    }

    public void setFrameSet(Set<String> frameSet) {
        this.frameSet = frameSet.toString();
    }
}

