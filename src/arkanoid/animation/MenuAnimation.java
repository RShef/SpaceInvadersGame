package arkanoid.animation;

import arkanoid.game.SelectionInfo;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.awt.Image;
import java.awt.Color;


/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 01/06/2016
 */

/**
 * The class.
 *
 * @param <T> the task.
 */
public class MenuAnimation<T> implements Menu {

    private String title;
    private List<SelectionInfo> selections;
    private KeyboardSensor sensor;
    private boolean stop;
    private T stats;
    private AnimationRunner r;

    /**
     * The constructor.
     *
     * @param sensor the keyboard sensor.
     * @param title  the name of the menu.
     * @param r      the animation runner.
     */
    public MenuAnimation(KeyboardSensor sensor, String title, AnimationRunner r) {
        this.sensor = sensor;
        this.title = title;
        this.selections = new LinkedList<>();
        this.stop = false;
        this.stats = null;
        this.r = r;
    }

    /**
     * Adds selection options to the menu.
     * <p/>
     *
     * @param key       the key to wait for
     * @param message   the message to display
     * @param rtrnVal the return value
     * @param subMenu   the sub menu.
     */

    @Override
    public void addSelection(String key, String message, Object rtrnVal, Menu subMenu) {
        this.selections.add(new SelectionInfo(key, message, rtrnVal, null));

    }

    /**
     * Return the status of the menu.
     * <p/>
     *
     * @return T
     */
    @Override
    public T getStatus() {
        this.stop = false;
        return this.stats;
    }

    /**
     * Sets a stop.
     *
     * @param stp a boolean.
     */
    @Override
    public void setStop(boolean stp) {
        this.stop = stp;
    }

    /**
     * Adds a sub menu to the selections options.
     *
     * @param key       a string key.
     * @param message   a message.
     * @param rtrnVal the value returned from the sub menu.
     * @param subMenu   a submenu.
     */
    @Override
    public void addSubMenu(String key, String message, Object rtrnVal, Menu subMenu) {
        this.selections.add(new SelectionInfo(key, message, rtrnVal, subMenu));

    }

    /**
     * Rest method.
     */
    public void rest() {
        this.stop = false;
        this.stats = null;
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
// Sets the back image of the menu.
        Image back = null;
        try {
            back = ImageIO.read(new File("resources/background_images/blurred.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        d.drawImage(0, 0, back);

        d.setColor(Color.white);
        d.drawText(290, 100, this.title, 50);
        d.drawText(110, 200, "Select one of the following options", 35);
        int y = 300;
        for (SelectionInfo selection : this.selections) {
            d.drawText(230, y, selection.toString(), 30);
            y += 80;
        }
        for (SelectionInfo selection : this.selections) {
            if (this.sensor.isPressed(selection.getKey())) {
                if (selection.getSubMenu() == null) {

                    this.stop = true;
                    this.stats = (T) selection.getReturnVal();
                } else {
                    // Opens the sub menu if selected.
                    this.r.run(selection.getSubMenu());
                    // Makes the sub menu work.
                    this.stats = (T) selection.getSubMenu().getStatus();
                    this.stop = true;
                    // Brings everything back to normal.
                    selection.getSubMenu().rest();
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
