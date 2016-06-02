package arkanoid.game;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 01/06/2016
 */

public class SelectionInfo {

    private String key;
    private String message;
    private Object returnVal;

    public SelectionInfo (String key, String message, Object returnVal) {
        this.key = key;
        this.message = message;
        this.returnVal = returnVal;
    }

    public String getKey() {
        return key;
    }

    public String getMessage() {
        return message;
    }

    public Object getReturnVal() {
        return returnVal;
    }

    @Override
    public String toString() {
        return "Press " + this.key + " to " + this.message;
    }
}
