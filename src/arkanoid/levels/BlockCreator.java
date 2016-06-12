package arkanoid.levels;

import arkanoid.sprites.Block;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 10/06/2016
 */
public interface BlockCreator {
    /**
     * Creates the blocks.
     *
     * @param xpos X value.
     * @param ypos Y value/
     * @return The new block.
     */
    Block create(int xpos, int ypos);

    /**
     * Returns the width.
     *
     * @return Returns the width.
     */
    int getWidth();

}
