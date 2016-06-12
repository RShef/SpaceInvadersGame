package arkanoid.levels;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 11/06/2016
 */


public class LevelsReaderForSub {

    private List<SetFileFormat> sffList;

    /**
     * readLevels() read the levels from BufferedReader.
     *
     * @param reader the BufferedReader.
     * @return List of SetFileFormat.
     */
    public List<SetFileFormat> readLevels(BufferedReader reader) {
        BufferedReader br = reader;
        this.sffList = new ArrayList<SetFileFormat>();
        String tempLine;
        String[] line;
        int count = 1;
        try {
            tempLine = br.readLine();
            System.out.println(tempLine);
            SetFileFormat sff = null;
            while (tempLine != null) {
                if (count % 2 == 1) {
                    line = tempLine.split(":");
                    sff = new SetFileFormat();
                    sff.addKeyAndName(line[0], line[1]);
                } else if (count % 2 == 0) {
                    sff.addDefi(tempLine);
                    sffList.add(sff);
                }
                count++;
                tempLine = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.sffList;
    }

    /**
     * getSff().
     *
     * @return List of SetFileFormat.
     */
    public List<SetFileFormat> getSff() {
        return this.sffList;
    }
}

