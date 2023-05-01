import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.*;

/**
 * An actor representing a boulder that will explode after a certain amount of time.
 * When it explodes, it damages any Critter actors in its location.
 */
public class Boulder extends Actor {
    private int lifetime;
    private final int threshold = 50;

    /**
     * Constructs a boulder with a random lifetime between 1 and 200.
     */
    public Boulder() {
        setColor(null);
        lifetime = (int) (Math.random() * 200) + 1;
    }

    /**
     * Constructs a boulder with the specified lifetime.
     * @param lifetime the number of steps the boulder will exist before exploding
     */
    public Boulder(int lifetime) {
        setColor(null);
        this.lifetime = lifetime;
    }

    /**
     * Causes the boulder to act. If the boulder's lifetime is below a certain threshold,
     * it changes color to red. If the boulder's lifetime reaches 0, it explodes.
     */
    public void act() {
        if (lifetime <= threshold) {
            setColor(Color.RED);
        }

        if (lifetime == 0) {
            explode();
        } else {
            lifetime--;
        }
    }

    /**
     * Explodes the boulder, damaging any Critter actors in its location and removing the boulder from the grid.
     */
    private void explode() {
        Kaboom kaboom = new Kaboom();
        Location loc = getLocation();
        kaboom.putSelfInGrid(getGrid(), loc);
        Actor actor = null;
        Location loca = getLocation();
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return;
        }
        if (gr.isValid(loca) && gr.get(loca) instanceof Critter) {
            actor = gr.get(loca);
        }
    }
}
