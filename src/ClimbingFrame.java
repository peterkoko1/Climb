import java.util.*;


public class ClimbingFrame {
    Set<String> frames;
    int difficulty;

    ClimbingFrame(Set<String> frames, int difficulty) {
        this.frames = frames;
        this.difficulty = difficulty;
    }

    Set<String> getFrames() {
        return frames;
    }

    int getDifficulty() {
        return difficulty;
    }
}

