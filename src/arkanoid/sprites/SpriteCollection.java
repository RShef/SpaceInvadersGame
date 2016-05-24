package arkanoid.sprites;

import biuoop.DrawSurface;

import java.util.ArrayList;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 3/31/2016
 */

public class SpriteCollection {

    private ArrayList<Sprite> sprites;

    /**
     * Instantiate a collection of sprites.
     * <p/>
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * Gets the sprites collection.
     * <p/>
     *
     * @return the sprite collection.
     */
    public ArrayList<Sprite> getSprites() {
        return this.sprites;
    }

    /**
     * Adds a sprite to the collection.
     * <p/>
     *
     * @param s Sprite.
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * Call time Passed on all sprites in the collection.
     * <p/>
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < this.sprites.size(); i++) {
            this.sprites.get(i).timePassed();
        }
    }

    /**
     * Draw on function.
     *
     * @param d the draw surface.
     */
    public void drawOn(DrawSurface d) {
        for (Sprite sprite : this.sprites) {
            //d.setColor(this.Color);

            sprite.drawOn(d);
        }
    }

}
