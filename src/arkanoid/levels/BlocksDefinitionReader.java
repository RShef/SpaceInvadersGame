package arkanoid.levels;


import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 3/30/2016
 */

public class BlocksDefinitionReader {
    private List<String> blockLines;
    private String defLine;
    private List<String> spacerLine;
    private BufferedReader re;
    private String temp;
    private DefaultBlock df;
    private TreeMap<String, BlockCreator> blockList;
    private TreeMap<String, Integer> spacersList;

    /**
     * The constructor.
     */
    public BlocksDefinitionReader() {
        this.blockLines = new ArrayList<String>();
        this.re = null;
        this.defLine = null;
        this.df = null;
        this.spacersList = null;
        this.spacersList = new TreeMap<String, Integer>();
        this.blockList = new TreeMap<String, BlockCreator>();
        this.spacerLine = new ArrayList<String>();

    }

    /**
     * Creates a BlocksFromSymbolsFactory.
     *
     * @param reader the reader form block def file.
     * @return a BlocksFromSymbolsFactory.
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        BufferedReader re = new BufferedReader(reader);
        BlocksDefinitionReader bdr = new BlocksDefinitionReader();
        bdr.readDef(re);
        return new BlocksFromSymbolsFactory(bdr.getSpacersList(), bdr.getBlockList());
    }

    /**
     * Reads from the String and splits it to form TreeMaps.
     *
     * @param reader the reader form block def file.
     */
    public void readDef(BufferedReader reader) {
        try {
            this.re = reader;
            temp = this.re.readLine();
            while (temp != null) {
                if (temp.startsWith(" ") || temp.startsWith("#") || temp.length() == 0) {
                    // Skip current line.
                    temp = re.readLine();
                }
                if (temp.startsWith("default")) {
                    this.defLine = temp;
                    this.df = new DefaultBlock(this.blockLineKeyMap(this.defLine));
                    this.df.defaultBlockMaker();
                }

                if (temp.startsWith("bdef")) {
                    this.blockLines.add(temp);
                    BlockFromString b = new BlockFromString(this.df, this.blockLineKeyMap(temp));
                    this.blockList.put(b.getSymbol(), b.getBlock());
                }
                if (temp.startsWith("sdef")) {
                    this.spacerLine.add(temp);
                }
                temp = re.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Something went wrong while reading!");
        }
        spacersKeyMap(spacerLine);
    }

    /**
     * Cuts a String in to a TreeMap of definitions of a block.
     *
     * @param def the string of a block.
     * @return TreeMap of block characteristic.
     */
    // returns a map of key values of the line.
    public TreeMap<String, String> blockLineKeyMap(String def) {
        String[] s = def.split(" ");
        TreeMap<String, String> defMap = new TreeMap<String, String>();
        for (int i = 1; i < s.length; i++) {
            String[] val = s[i].split(":");
            defMap.put(val[0], val[1]);
        }
        return defMap;
    }

    /**
     * Cuts a String in to a TreeMap of definitions of the block spaces.
     *
     * @param spacerList the string of the space definition.
     * @return reeMap of block space characteristic
     */
    public TreeMap<String, Integer> spacersKeyMap(List<String> spacerList) {
        TreeMap<String, Integer> spacerMapWidths = new TreeMap<String, Integer>();
        String[] sL;

        for (String s : spacerList) {
            sL = s.split(" ");
            String[] symbolSplit = sL[1].split(":");
            String key = symbolSplit[1];
            String[] widthSplit = sL[2].split(":");
            Integer value = Integer.parseInt(widthSplit[1]);
            spacerMapWidths.put(key, value);
        }
        this.spacersList = spacerMapWidths;
        return spacerMapWidths;
    }

    /**
     * Retuns the block list.
     *
     * @return the blocks.
     */
    public TreeMap<String, BlockCreator> getBlockList() {
        return this.blockList;
    }

    /**
     * Returns the space list.
     *
     * @return Returns the space list.
     */
    public TreeMap<String, Integer> getSpacersList() {
        return this.spacersList;
    }
}



