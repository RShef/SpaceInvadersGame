package arkanoid.animation;

import arkanoid.game.SelectionInfo;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 01/06/2016
 */

public class MenuAnimation<T> implements Menu {

    private String title;
    private List<SelectionInfo> selections;
    private Object returnVal;
    private KeyboardSensor sensor;
    private boolean stop;
    private boolean isAlreadyPressed;

    public MenuAnimation(KeyboardSensor sensor, String title) {
        this.sensor = sensor;
        this.title = title;
        this.selections = new LinkedList<>();
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    /**
     * Adds selection options to the menu.
     * <p/>
     * @param key       the key to wait for
     * @param message   the message to display
     * @param returnVal the return value
     */
    @Override
    public void addSelection(String key, String message, Object returnVal) {
        this.selections.add(new SelectionInfo(key, message, returnVal));
    }

    /**
     * Return the status of the menu.
     * <p/>
     *
     * @return T
     */
    @Override
    public Object getStatus() {
        return this.returnVal;
    }

    @Override
    public void setStop(boolean stop) {
        this.stop = stop;
    }

    /**
     * Displays the animation.
     * <p/>
     *
     * @param d  is the draw surface.
     * @param dt is the time passed from previous frame.
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        d.drawText(300, 100, this.title, 50);
        d.drawText(150, 200, "Select one of the following options", 35);
        int y = 300;
        for (SelectionInfo selection : this.selections) {
            d.drawText(250, y, selection.toString(), 30);
            y += 80;
        }
        for (SelectionInfo selection: this.selections) {
            if (this.sensor.isPressed(selection.getKey())) {
                if (!this.isAlreadyPressed) {
                    this.stop = true;
                    this.returnVal = selection.getReturnVal();
                } else {
                    this.isAlreadyPressed = false;
                }

            }
        }
    }

    /**
     * When to stop.
     * <p/>
     *
     * @return if it reached zero.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }

}
