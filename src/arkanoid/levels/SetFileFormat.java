package arkanoid.levels;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 11/06/2016
 */


public class SetFileFormat {
    private String key;
    private String levelName;
    private List<LevelInformation> levelsList;

    /**
     * SetFileFormat() constructor.
     */
    public SetFileFormat() {
        this.levelsList = new ArrayList<LevelInformation>();
    }

    /**
     * addKeyAndName().
     *
     * @param sKey       - String.
     * @param levName = String.
     */
    public void addKeyAndName(String sKey, String levName) {
        this.key = sKey;
        this.levelName = levName;
    }

    /**
     * addDefi().
     *
     * @param defi a String.
     */
    public void addDefi(String defi) {
        LevelSpecificationReader lsfr = new LevelSpecificationReader();
        BufferedReader fr = null;
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(defi);
        try {
            fr = new BufferedReader(new InputStreamReader(is));
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.levelsList = lsfr.fromReader(fr);
    }

    /**
     * getKey().
     *
     * @return String - the key.
     */
    public String getKey() {
        return this.key;
    }

    /**
     * getName().
     *
     * @return String - the name.
     */
    public String getName() {
        return this.levelName;
    }

    /**
     * getLevelList().
     *
     * @return List of LevelInformation.
     */
    public List<LevelInformation> getLevelList() {
        return this.levelsList;
    }
}
