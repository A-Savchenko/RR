/**
 * The Stone class represents a rock that has a limited lifetime, after which it transforms into a Boulder.
 * The lifetime is set randomly between 1 and 200, and when it falls below a certain threshold (50), the Stone turns green.
 * Once the lifetime of the Stone reaches 0, it turns into a Boulder.
 */

import java.awt.Color;
import java.util.Random;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

public class Stone extends Rock {
    private static final int DEFAULT_LIFETIME = (int)(Math.random() *200 + 1);
    private static final int THRESHOLD = 50;
    private int lifetime;
    private Color originalColor;

    /**
     * Constructs a Stone with a random lifetime and sets its color to null.
     */
    public Stone() {
        this(DEFAULT_LIFETIME);
    }

    /**
     * Constructs a Stone with the specified lifetime and sets its color to null.
     *
     * @param lifetime the lifetime of the Stone
     */
    public Stone(int lifetime) {
        super();
        this.lifetime = lifetime;
        this.originalColor = getColor();
        setColor(null);
    }

    /**
     * Overrides the act method in Actor. If the lifetime of the Stone falls below the threshold, it changes its color to green.
     * If the lifetime of the Stone reaches 0, it turns into a Boulder.
     */
    public void act() {
        if (lifetime <= THRESHOLD) {
            setColor(Color.GREEN);
        }

        if (lifetime == 0) {
            Grid<Actor> gr = getGrid();
            Location loc = getLocation();
            if (gr != null) {
                this.removeSelfFromGrid();
                Boulder boulder = new Boulder(THRESHOLD);
                boulder.putSelfInGrid(gr, loc);
            } else {
                this.removeSelfFromGrid();
            }
        }

        lifetime--;
    }

    /**
     * Sets the lifetime of the Stone to the specified value, but only if the Stone is already in a grid.
     *
     * @param lifetime the lifetime to set
     */
    public void setLifetime(int lifetime) {
        if (this.getGrid() != null) {
            this.lifetime = lifetime;
        }
    }
}
