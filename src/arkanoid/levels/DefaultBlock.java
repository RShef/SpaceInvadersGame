package arkanoid.levels;

import java.awt.Color;
import java.util.TreeMap;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 3/30/2016
 */

public class DefaultBlock {
    private int hits;
    private int width;
    private int height;
    private String symbol;
    private TreeMap<Integer, Fill> fills;
    private Color stroke;
    private TreeMap<String, String> defLineMap = null;

    /**
     * The constructor.
     *
     * @param defLineMap the default block TreeMap .
     */
    public DefaultBlock(TreeMap<String, String> defLineMap) {
        this.width = 0;
        this.height = 0;
        this.stroke = null;
        this.symbol = null;
        this.hits = 0;
        this.fills = new TreeMap<Integer, Fill>();
        this.defLineMap = defLineMap;
    }

    /**
     * Creates thr default block.
     */
    public void defaultBlockMaker() {
        for (String key : this.defLineMap.keySet()) {
            if (key.equals("height")) {
                this.height = Integer.parseInt(this.defLineMap.get(key));
            } else if (key.equals("width")) {
                this.width = Integer.parseInt(this.defLineMap.get(key));
            } else if (key.equals("hit_points")) {
                this.hits = Integer.parseInt(this.defLineMap.get(key));
            } else if (key.equals("stroke")) {
                this.stroke = (Fill.fillFS(this.defLineMap.get(key)).getColor());
            } else if (key.equals("symbol")) {
                this.symbol = this.defLineMap.get(key);
            } else if (key.equals("fill")) {
                this.fills.put(1, Fill.fillFS(this.defLineMap.get(key)));
            } else if (key.startsWith("fill-")) {
                String[] s = key.split("-");
                this.fills.put(Integer.parseInt(s[1]), Fill.fillFS(this.defLineMap.get(key)));
            }
        }
    }

    /**
     * See return.
     *
     * @return the height of the block.
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * See return.
     *
     * @return the width of the block.
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * See return.
     *
     * @return the number of hits on the block.
     */
    public int getHits() {
        return this.hits;
    }

    /**
     * See return.
     *
     * @return the block symbol.
     */
    public String getSymbol() {
        return this.symbol;
    }

    /**
     * See return.
     *
     * @return the fills TreeMap.
     */
    public TreeMap<Integer, Fill> getFills() {
        return this.fills;
    }

    /**
     * See return.
     *
     * @return the stroke of the block.
     */
    public Color getStroke() {
        return this.stroke;
    }


}
