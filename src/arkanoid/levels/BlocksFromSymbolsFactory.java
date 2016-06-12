package arkanoid.levels;

import arkanoid.sprites.Block;

import java.util.Map;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 10/06/2016
 */
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    /**
     * The constructor.
     *
     * @param sw treeMap of the spacer Widths.
     * @param bc treeMap of the lock Creators.
     */
    public BlocksFromSymbolsFactory(Map<String, Integer> sw, Map<String, BlockCreator> bc) {
        this.blockCreators = bc;
        this.spacerWidths = sw;
    }

    /**
     * Tells if a symbol is in in the file.
     *
     * @param s a symbol.
     * @return true if the symbol is in the level file definition for a space.
     */
    // returns true if 's' is a valid space symbol.
    public boolean isSpaceSymbol(String s) {
        return this.spacerWidths.containsKey(s);
    }

    /**
     * Tells if a block is in in the file.
     *
     * @param s a symbol.
     * @return true if the symbol is in the level file definition for a block.
     */
    // returns true if 's' is a valid block symbol.
    public boolean isBlockSymbol(String s) {
        return this.blockCreators.containsKey(s);
    }

    /**
     * Creates a block from a symbol and coordinates.
     *
     * @param s    a block symbol.
     * @param xpos Y value of the block.
     * @param ypos X value of the block.
     * @return Return a block according to the definitions associated.
     */
    // with symbol s. The block will be located at position (xpos, ypos).
    public Block getBlock(String s, int xpos, int ypos) {
        return this.blockCreators.get(s).create(xpos, ypos);

    }

    /**
     * Gets the width.
     *
     * @param s a space symbol.
     * @return Returns the width in pixels associated with the given spacer-symbol.
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }

    /**
     * See return.
     *
     * @return the block creators.
     */
    public Map<String, BlockCreator> getBlockCreators() {
        return this.blockCreators;
    }


}
