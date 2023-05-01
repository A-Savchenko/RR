/**
 * Kaboom is an actor that explodes after a certain number of turns.
 * It disappears from the grid when it explodes.
 **/
import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;

public class Kaboom extends Actor {
    private int lifetime;
    private final int threshold = 3;

    /**
     * Constructs a Kaboom object with initial lifetime set to the threshold value.
     * It also sets the color of the Kaboom object to null.
     */
    public Kaboom() {
        setColor(null);
        lifetime = threshold;
    }

    /**
     * Decreases the lifetime of the Kaboom object by 1 at each step.
     * If the lifetime of the object is 0, it removes itself from the grid.
     */
    public void act() {
        lifetime--;
        if (lifetime == 0) {
            Grid<Actor> gr = getGrid();
            if (gr != null) {
                removeSelfFromGrid();
            }
        }
    }

}
