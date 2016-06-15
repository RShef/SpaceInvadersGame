package arkanoid.levels;

import arkanoid.sprites.Block;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.util.TreeMap;


/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 05/06/2016
 */
public class LevelSpecificationReader {
    /**
     * Reads the file, splits it and delegates to other classes the file translation process.
     *
     * @param reader the reader of the level definitions file.
     * @return the levels from the fills in a list of level information objects.
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) {
        List<String> levelInformation = new ArrayList<String>();
        List<LevelInformation> levels = new ArrayList<LevelInformation>();
        BufferedReader re = (BufferedReader) reader;
        String temp;
        String[] blockDefFile;
        BlocksDefinitionReader br = new BlocksDefinitionReader();
        BlocksFromSymbolsFactory factory = null;

        int y = 0;
        int x = 0;
        int blockNum = 0;
        String runningChar = null;
        try {
            temp = re.readLine();
            while (temp != null) {
                if (temp.startsWith(" ") || temp.startsWith("#") || temp.length() == 0) {
                    // Skip current line.
                    temp = re.readLine();
                    // There is a level def.

                } else if (temp.startsWith("START_LEVEL")) {
                    do {
                        if (temp.startsWith("block_definitions")) {
                            blockDefFile = temp.split(":");
                            // Getting the block file "on stream".
                            try {
                                InputStream is =
                                        ClassLoader.getSystemClassLoader().getResourceAsStream(blockDefFile[1]);
                                // Reading the blocks.
                                factory =
                                        BlocksDefinitionReader.fromReader(new BufferedReader(
                                                new InputStreamReader(is)));
                            } catch (Exception e) {
                                System.out.println("fuck2!");
                                e.printStackTrace();
                            }

                        } else if (temp.contains(":")) {
                            levelInformation.add(temp);
                        }
                        // Read a new line.
                        temp = re.readLine();
                    } while (!temp.startsWith("START_BLOCKS"));
                    // check.
                    if (levelInformation.size() < 9) {
                        throw new RuntimeException("Missing level fields");
                    }
                    // Extra check.
                    if (temp.startsWith("START_BLOCKS")) {
                        // This list will contain all the blocks of the level.
                        ArrayList<Block> blockL = new ArrayList<Block>();
                        // Has all the background information.
                        BackGround bG = (BackGround) backSpecifirefire(levelInformation);
                        // The factory.

                        // Initiating y and x.
                        y = bG.getStartingY();
                        x = bG.getStartingX();
                        temp = re.readLine();
                        while (!temp.startsWith("END_BLOCKS")) {
                            // The x value needs to return to default after each row.
                            for (int i = 0; i < temp.length(); i++) {
                                runningChar = String.valueOf(temp.charAt(i));
                                if (factory.isBlockSymbol(runningChar)) {
                                    int x1 = x + blockNum;
                                    int t = factory.getBlockCreators().get(runningChar).getWidth();
                                    blockL.add(factory.getBlock(runningChar,
                                            x + (blockNum * (factory.getBlockCreators().get(runningChar)
                                                    .getWidth())), y));
                                    blockNum++;
                                } else if (factory.isSpaceSymbol(runningChar)) {
                                    x += factory.getSpaceWidth(runningChar);
                                }
                            }
                            // Next row.
                            y += bG.getRowHeight();
                            // X is back to starting value.
                            x = bG.getStartingX();
                            // Spaces initialized.
                            blockNum = 0;
                            // Read next line.
                            temp = re.readLine();
                        }
                        // Add all the blocks to the background list.
                        bG.setBlocks(blockL);
                        // 'Saving' the level read.
                        levels.add(bG);
                        temp = re.readLine();

                        if (temp.startsWith("END_LEVEL")) {
                            levelInformation.removeAll(levelInformation);
                            temp = re.readLine();
                            continue;
                        }
                        bG.initialBallVelocities();
                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Something went wrong while reading!");
        }
        return levels;
    }

    /**
     * A help function to receive the background.
     *
     * @param tokens the level definitions from the fille.
     * @return a level information object of the level from file.
     */
    public LevelInformation backSpecifirefire(List<String> tokens) {
        TreeMap m = new TreeMap();
        String[] sepTokens;
        for (String token : tokens) {
            sepTokens = token.split(":");
            m.put(sepTokens[0], sepTokens[1]);
        }
        return new BackGround(m);
    }
}

