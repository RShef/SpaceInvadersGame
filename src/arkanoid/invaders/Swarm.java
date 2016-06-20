package arkanoid.invaders;

import arkanoid.game.GameLevel;
import arkanoid.geometry.Rectangle;
import arkanoid.invaders.Alien;
import arkanoid.geometry.Point;

import arkanoid.listeners.AlienRemover;
import arkanoid.listeners.BallRemover;
import arkanoid.listeners.BlockRemover;
import arkanoid.listeners.ScoreTrackingListener;
import arkanoid.sprites.Ball;
import arkanoid.sprites.Sprite;
import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 15/06/2016
 */

public class Swarm implements Sprite {

    private double speed;
    private double startingSpeed;
    private int rows;
    private int cols;
    private Point startingPoint;
    private Alien[][] swarmGrid;
    private TreeMap<Alien,Point> alienList;

    /**
     * Instantiates a new swarm.
     *
     * @param x the x
     * @param y the y
     * @param givenCols the given cols
     * @param givenRows the given rows
     * @param givenSpeed the given speed
     */
    public Swarm(int x, int y, int givenCols, int givenRows, double givenSpeed) {
        this.startingPoint = new Point(x, y);
        this.startingSpeed = givenSpeed;
        this.speed = givenSpeed;
        this.cols = givenCols;
        this.rows = givenRows;
        this.alienList = new TreeMap<>();
        this.swarmGrid = makeSwarm(x, y);
    }

    /**
     * Makes a swarm grid.
     * <p>
     * @param x first alien start x
     * @param y first alien start y
     * @return the swarm grid
     */
    public Alien[][] makeSwarm(int x, int y) {
        Alien[][] swarm = new Alien[this.rows][this.cols];
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                swarm[i][j] = new Alien(new Point(x, y), 40, 30);
                x += 50;
            }
            x = (int) this.startingPoint.getX();
            y += 40;
        }
        return swarm;
    }

    /**
     * Gets the right most.
     *
     * @return the right most
     */
    public Alien getRightMost() {
        for (int i = this.cols - 1; i >= 0; i--) {
            for (int j = 0; j < this.rows; j++) {
                if (swarmGrid[j][i] != null && swarmGrid[j][i].getHits() != 0) {
                    return swarmGrid[j][i];
                }
            }
        }
        return null;
    }

    /**
     * Gets the left most.
     *
     * @return the left most
     */
    public Alien getLeftMost() {
        for (int i = 0; i < this.cols; i++) {
            for (int j = 0; j < this.rows; j++) {
                if (swarmGrid[j][i] != null&& swarmGrid[j][i].getHits() != 0) {
                    return swarmGrid[j][i];
                }
            }
        }
        return null;
    }

    /**
     * Gets the lowest alien.
     *
     * @return the lowest alien
     */
    public Alien getLowest() {
        for (int i = this.rows - 1; i >= 0; i--) {
            for (int j = 0; j < this.cols; j++) {
                if (swarmGrid[i][j] != null && swarmGrid[i][j].getHits() != 0) {
                    return swarmGrid[i][j];
                }
            }
        }
        return null;
    }

    /**
     * Draws the sprite on the screen.
     * <p/>
     *
     * @param d the draw surface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                swarmGrid[i][j].drawOn(d);
            }
        }
    }

    /**
     * Notify the Sprite that time has passed.
     *
     * @param dt is the time passed from previous frame.
     *           <p/>
     */
    @Override
    public void timePassed(double dt) {
        int verticalChange = 20;
        // aliens at the right side of the screen
        if (this.getRightMost() == null) {
            return;
        }
        if (this.getRightMost().getCollisionRectangle().getUpperLeft().getX()
                + this.getRightMost().getCollisionRectangle().getWidth() >= 800) {
            this.speed *= -1.1;
            moveOneStep(0, verticalChange);
        }
        // aliens at the left side of the screen
        if (this.getLeftMost() == null) {
            return;
        }
        if (this.getLeftMost().getCollisionRectangle().getUpperLeft().getX() <= 0) {
            this.speed *= -1.1;
            moveOneStep(0, verticalChange);
        }
        moveOneStep(this.speed * dt, 0);
    }

    /**
     * Moves the swarm one step in a given direction.
     * <p>
     * @param dx the x change
     * @param dy the y change
     */
    public void moveOneStep(double dx, double dy) {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                if (swarmGrid[i][j] != null) {
                    swarmGrid[i][j].moveOneStep(dx, dy);
                }
            }
        }
    }

    /**
     * Resets swarms's position and speed
     * <p>
     */
    public final void resetPosAndSpeed() {
        double x = this.startingPoint.getX();
        double y = this.startingPoint.getY();

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                Rectangle enemyRect = swarmGrid[i][j].getCollisionRectangle();
                swarmGrid[i][j].setCollisionRectangle(new Rectangle(new Point(x, y), enemyRect.getWidth(), enemyRect
                        .getHeight()));
                x += 50;
            }
            x = (int) this.startingPoint.getX();
            y += 40;
        }
        this.speed = this.startingSpeed;
    }

    /**
     * Adds the swarm grid to the game.
     * <p/>
     * @param g the game environment.
     */
    public void addToGame(GameLevel g) {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                swarmGrid[i][j].addToGame(g);
            }
        }
    }

    /**
     * Removes the swarm grid from the game.
     * <p/>
     * @param g the game.
     */
    public void removeFromGame(GameLevel g) {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                swarmGrid[i][j].removeFromGame(g);
            }
        }
    }

    public void addHitListeners(AlienRemover ar, ScoreTrackingListener stl, BallRemover bar) {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.swarmGrid[i][j].addHitListener(ar);
                this.swarmGrid[i][j].addHitListener(stl);
                this.swarmGrid[i][j].addHitListener(bar);
            }
        }
    }

    public boolean isClear (Alien a) {
        for (int i = 4; i >= 0; i--) {
            for (int j = 0; j < 9; j++) {
                if (this.swarmGrid[i][j] != null) {
                    if (this.swarmGrid[i][j] == a) {
                        if (i == 4 ) { return true;}
                        for (int k = i + 1; k < 5; k++) {
                            if (this.swarmGrid[k][j] != null) {
                                return false;
                            }
                        }
                    }
                }
                else { break;}
            }
        }
        return true;
    }

    public TreeMap getAllienMap () { return this.alienList;}
public Alien[][] getSwarmGrid() { return this.swarmGrid;}
    public void changeGrid(Alien[][] a) { this.swarmGrid = a;}
}