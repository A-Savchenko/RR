import java.awt.Color;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

/**
 * A SickCoyote is an Actor that can recover from sickness over time, and eventually turn into a Coyote.
 * It is green when its lifetime is less than or equal to its threshold, and black otherwise.
 */
public class SickCoyote extends Actor {
    private int lifetime;
    private final int threshold = 10;

    /**
     * Constructs a SickCoyote with a default lifetime of 10.
     */
    public SickCoyote() {
        this.lifetime = threshold;
        setColor(null);
    }

    /**
     * Constructs a SickCoyote with a given lifetime.
     * @param lifetime the initial lifetime of the SickCoyote
     */
    public SickCoyote(int lifetime) {
        this.lifetime = lifetime;
        setColor(null);
    }

    /**
     * Moves the SickCoyote to its next location in the grid. If the SickCoyote's lifetime is 0,
     * it turns into a Coyote. Otherwise, it decrements its lifetime and changes color to green
     * if its remaining lifetime is less than or equal to its threshold.
     */
    public void act() {
        if (lifetime == 0) {
            Grid<Actor> gr = getGrid();
            Location loc = getLocation();
            if (gr != null) {
                this.removeSelfFromGrid();
                Coyote coyote = new Coyote();
                coyote.putSelfInGrid(gr, loc);
            } else {
                this.removeSelfFromGrid();
            }
        } else {
            lifetime--;
            if (lifetime <= threshold) {
                setColor(Color.GREEN);
            }
        }
    }
}
