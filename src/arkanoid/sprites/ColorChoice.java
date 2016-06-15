package arkanoid.sprites;

import arkanoid.levels.Fill;

import java.awt.Image;
import java.awt.Color;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 09/06/2016
 */

// geting the color.
public class ColorChoice {

    private boolean isImage2 = false;
    private Color color = null;
    private Image im = null;
    private Fill f;

    /**
     * The constructor.
     *
     * @param f the Fill object.
     */
    public ColorChoice(Fill f) {
        this.f = f;
    }

    /**
     * Sets the color.
     *
     * @return void.
     */
    public boolean setColor() {
        if (this.f == null) {
            return false;
        }
        if (!this.f.isImage()) {
            this.color = this.f.getColor();
        } else {
            this.isImage2 = true;
            this.im = this.f.getImage();
        }
        return true;
    }

    /**
     * See return.
     *
     * @return the color.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * See return.
     *
     * @return the image.
     */
    public Image getImage() {
        return this.im;
    }

    /**
     * See return.
     *
     * @return true if there is an image.
     */
    public boolean isImage2() {
        return this.isImage2;
    }
}