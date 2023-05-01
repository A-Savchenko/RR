import info.gridworld.actor.*;
import info.gridworld.grid.*;
import java.util.*;
/**
 * Coyote is a critter that moves randomly, occasionally leaving behind stones and exploding boulders.
 * If the Coyote encounters a Boulder, it explodes into a Kaboom, destroying the Coyote and the Boulder.
 **/
public class Coyote extends Critter {
    private int steps;
    private int direction;
    /**
     *Constructs a new Coyote with a random direction and null color.
     **/
    public Coyote() {
        setColor(null);
        direction = getRandomDirection();
    }
    /**
     *This method is called by the GridWorld framework to perform an action for the Coyote.
     *If the Coyote has taken the specified number of steps in a particular direction, it will change
     *direction, put a Stone and reset the steps counter. If the Coyote can move in the current
     *direction, it will move and decrement the steps counter. If the Coyote hits a wall, it will
     *change direction and reset the steps counter.
     **/
    public void act() {
        if (steps == 0) {
            // time to change direction
            direction = getRandomDirection();
            steps = 5;
            putStone();
        }
        else if (canMove()) {
            // keep moving in current direction
            move();
            steps--;
        }
        else {
            // hit a wall, change direction
            direction = getRandomDirection();
            steps = 5;
        }
    }
    /**
     * Returns a random direction for the Coyote to move in.
     *
     * @return a random direction for the Coyote to move in
     */
    private int getRandomDirection() {
        Random rand = new Random();
        int angle = rand.nextInt(8) * 45;
        return getDirection() + angle;
    }
    /**
     * Determines if the Coyote can move to the next location in its current direction.
     * @return true if the Coyote can move to the next location, false otherwise.
     **/
    private boolean canMove() {
        Grid<Actor> grid = getGrid();
        Location currentLoc = getLocation();
        Location nextLoc = currentLoc.getAdjacentLocation(direction);
        if (grid.isValid(nextLoc)) {
            Actor neighbor = grid.get(nextLoc);
            if (neighbor == null || neighbor instanceof Flower) {
                return true;
            }
            else if (neighbor instanceof Boulder) {
                Kaboom kaboom = new Kaboom();
                kaboom.putSelfInGrid(grid, nextLoc);
                Location l = getLocation().getAdjacentLocation(getDirection());
                if(getGrid().isValid(l))
                {
                    Actor neighbor1 = getGrid().get(l);
                }
                else
                {
                    l = getLocation();
                }

                removeSelfFromGrid();
                return false;
            }
            else if (neighbor instanceof RR) {
                return false; // coyote cannot move onto a RoadRunner
            }
        }
        return false;
    }

    /**
     * Move the Coyote to the next location in the grid in the current direction. If the next location
     * is not valid, then remove the Coyote from the grid.
     */
    private void move() {
        Grid<Actor> grid = getGrid();
        Location currentLoc = getLocation();
        Location nextLoc = currentLoc.getAdjacentLocation(direction);
        if (grid.isValid(nextLoc)) {
            moveTo(nextLoc);
        }
        else {
            removeSelfFromGrid();
        }
    }
    /**
     * This method creates a Stone and puts it in an adjacent location of the Coyote.
     * If an adjacent location is not available, the method will not create a Stone.
     * The lifetime of the Stone is set to 50.
     **/
    private void putStone() {
        Grid<Actor> grid = getGrid();
        Location currentLoc = getLocation();
        for (int i = 0; i < 8; i++) {
            int angle = i * 45;
            Location adjacentLoc = currentLoc.getAdjacentLocation(angle);
            if (grid.isValid(adjacentLoc) && grid.get(adjacentLoc) == null) {
                Stone stone = new Stone();
                stone.putSelfInGrid(grid, adjacentLoc);
                stone.setLifetime(50); // change lifetime as desired
                break;
            }
        }
    }
}
