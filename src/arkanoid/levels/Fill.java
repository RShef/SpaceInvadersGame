package arkanoid.levels;

import javax.imageio.ImageIO;

import java.io.IOException;
import java.awt.Image;
import java.awt.Color;
import java.io.InputStream;
import java.lang.reflect.Field;


/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 3/30/2016
 */

public class Fill {
    private Image im;
    private Color color;
    private boolean image = false;

    /**
     * The constructor.
     */
    public Fill() {
        this.color = Color.black;
        this.image = false;
        this.im = null;
    }

    /**
     * Fills from a string the color.
     *
     * @param s thr string.
     * @return a Fill object that has the string color/ image in it.
     */
    public static Fill fillFS(String s) {
        Fill f = new Fill();
        Color temp;
        if (s == null) {
            return null;
        }
        if (s.startsWith("image")) {
            try {
                int start = s.indexOf("(") + 1;
                int end = s.indexOf(")");
                String t = s.substring(start, end);
                InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(t);
                f.im = ImageIO.read(is);
            } catch (IOException e) {
                e.printStackTrace();
            }
            f.image = true;
        } else if (s.startsWith("color")) {
            if (s.contains("RGB")) {
                int start = s.indexOf("B") + 2;
                int end = s.indexOf(")");
                String t = s.substring(start, end);
                String[] b = t.split(",");
                f.color = new Color(Integer.parseInt(b[0]), Integer.parseInt(b[1]), Integer.parseInt(b[2]));
            } else {
                int start = s.indexOf("(") + 1;
                int end = s.indexOf(")");
                String c = s.substring(start, end);

                try {
                    Field field2 = Class.forName("java.awt.Color").getField(c);
                    temp = (Color) field2.get(null);
                } catch (Exception e) {
                    temp = null;
                }
                f.color = temp;
            }
        }
        return f;
    }

    /**
     * The constructor.
     *
     * @param c the color.
     */
    public Fill(Color c) {
        this.color = c;
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
     * @return true if there is an image (and no color).
     */
    public boolean isImage() {
        return this.image;
    }

}
