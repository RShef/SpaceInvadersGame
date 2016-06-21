package spaceinvaders.animation;

import spaceinvaders.game.Counter;
import biuoop.DrawSurface;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.awt.Color;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 15/5/2016
 */

public class EndScreen implements Animation {

    private Counter score;
    private boolean win;

    /**
     * Instantiates a new End Screen.
     *
     * @param score the current score
     * @param win   the win condition
     */
    public EndScreen(Counter score, boolean win) {
        this.score = score;
        this.win = win;
    }

    /**
     * Does one frame.
     * <p>
     *
     * @param d  the draw surface.
     * @param dt is the time passed from previous frame.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        Image back = null;

        if (this.win) {
            try {
                back = ImageIO.read(new File("resources/background_images/fireworks.jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            d.drawImage(0, 0, back);

            //d.setColor(Color.green);
            //d.fillRectangle(0, 0, 800, 600);
            d.setColor(Color.black);
            d.drawText(300, 200, "You Win!", 50);
        } else {
            try {
                back = ImageIO.read(new File("resources/background_images/gameover.jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            d.drawImage(0, 0, back);

            //d.setColor(Color.red);
            //d.fillRectangle(0, 0, 800, 600);
            //d.setColor(Color.black);
            //d.drawText(250, 200, "Game Over...", 50);
        }
        d.drawText(200, 550, "Your score is: " + this.score.getValue(), 50);
    }

    /**
     * When to stop.
     * <p>
     *
     * @return when to stop.
     */
    public boolean shouldStop() {
        return false;
    }
}