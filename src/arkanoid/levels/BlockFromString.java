package arkanoid.levels;

import arkanoid.geometry.Point;
import arkanoid.sprites.Block;

import java.awt.Color;
import java.util.TreeMap;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 3/30/2016
 */
public class BlockFromString implements BlockCreator {
    private DefaultBlock df;
    private TreeMap<String, String> bs;
    private Integer hits = null;
    private Integer width = null;
    private Integer height = null;
    private String symbol = null;
    private TreeMap<Integer, Fill> fills = null;
    private Color stroke = null;

    /**
     * The constractor.
     *
     * @param df the DefaultBlock for the level.
     * @param bs the blocks as strings.
     */
    public BlockFromString(DefaultBlock df, TreeMap<String, String> bs) {
        this.bs = bs;
        this.df = df;
        this.hits = 0;
        this.width = 0;
        this.height = 0;
        this.symbol = null;
        this.fills = null;
        this.stroke = null;
        this.fills = new TreeMap<Integer, Fill>();
        for (String key : bs.keySet()) {
            if (key.equals("height")) {
                this.height = Integer.parseInt(bs.get(key));
            } else if (key.equals("width")) {
                this.width = Integer.parseInt(bs.get(key));
            } else if (key.equals("hit_points")) {
                this.hits = Integer.parseInt(bs.get(key));
            } else if (key.equals("stroke")) {
                this.stroke = Fill.fillFS(bs.get(key)).getColor();
            } else if (key.equals("symbol")) {
                this.symbol = bs.get(key);
            } else if (key.equals("fill")) {
                this.fills.put(1, Fill.fillFS(bs.get(key)));
            } else if (key.startsWith("fill-")) {
                String[] s = key.split("-");
                this.fills.put(Integer.parseInt(s[1]), Fill.fillFS(bs.get(key)));
            }
        }
        if (this.df != null) {

            if (this.width == 0) {
                this.width = this.df.getWidth();
            }
            if (this.height == 0) {
                this.height = this.df.getHeight();
            }
            if (this.hits == null) {
                this.hits = this.df.getHits();
            }
            if (this.fills.size() < this.hits) {
                for (int i = this.fills.size(); i < this.hits; i++) {
                    Fill f = null;
                    if (this.df.getFills().containsKey(i - 1)) {
                        f = this.df.getFills().get(i - 1);
                    } else {
                        f = this.df.getFills().get(1);
                    }
                    this.fills.put(i - 1, f);
                }
            }
            if (this.symbol == null) {
                this.symbol = this.df.getSymbol();
            }
            if (this.stroke == null && this.df.getStroke() != null) {
                this.stroke = this.df.getStroke();
            }
        }
        //if (this.stroke == null) { this.stroke = this.df.getStroke();}
        // In the event there are more hits then specified fills.

        if (this.height == null || this.width == null || this.hits == null || this.fills == null || this.symbol == null
                ) {
            throw new RuntimeException("Missing block spec and no default!");
        }

    }

    /**
     * Returns the height of the block.
     *
     * @return Returns the height of the block.
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Returns the width of the block.
     *
     * @return Returns the width of the block.
     */
    @Override
    public int getWidth() {
        return this.width;
    }

    /**
     * Returns the number of hits of the block.
     *
     * @return Returns the number of hits of the block.
     */
    public int getHits() {
        return this.hits;
    }

    /**
     * Returns the symbol of the block.
     *
     * @return Returns the symbol of the block.
     */
    public String getSymbol() {
        return this.symbol;
    }

    /**
     * Returns the fills of the block.
     *
     * @return Returns the fills of the block.
     */
    public TreeMap<Integer, Fill> getFills() {
        return this.fills;
    }

    /**
     * Returns the stroke of the block.
     *
     * @return Returns the stroke of the block.
     */
    public Color getStroke() {
        return this.stroke;
    }

    /**
     * Returns the  block.
     *
     * @return Returns the  block.
     */
    public BlockCreator getBlock() {
        return this;
    }

    /**
     * Crates the block.
     *
     * @param xpos X value.
     * @param ypos Y value/
     * @return the new block.
     */
    @Override
    public Block create(int xpos, int ypos) {
        return new Block(new Point(xpos, ypos), this.getWidth(), this.getHeight(), this.getHits(), this.fills,
                this.getSymbol());
    }

}
