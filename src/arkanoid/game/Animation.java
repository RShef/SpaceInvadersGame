package arkanoid.game;
import biuoop.DrawSurface;

/**
 * Created by RoyShefi on 15/05/2016.
 */
public interface Animation {
    void doOneFrame(DrawSurface d);
    boolean shouldStop();
}
