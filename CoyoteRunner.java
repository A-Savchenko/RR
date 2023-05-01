import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.Random;

/**
 * A class that creates and runs a simulation of a GridWorld game where two coyotes try to catch roadrunners.
 * It adds two coyotes and some boulders to a grid and displays the simulation.
 */
public class CoyoteRunner {
    /**
     * Creates and runs a simulation of a GridWorld game where two coyotes try to catch roadrunners.
     * It creates a new ActorWorld with a BoundedGrid of 10 rows and 10 columns, adds two coyotes to the grid,
     * and adds some boulders to the grid at random locations.
     * Finally, it shows the world in a new window.
     *
     * @param args the command-line arguments (not used)
     */
    public static void main(String[] args) {
        ActorWorld world = new ActorWorld(new BoundedGrid<>(10, 10));

        // create and add two Coyotes to the grid
        Coyote coyote1 = new Coyote();
        Coyote coyote2 = new Coyote();
        world.add(new Location(0, 0), coyote1);
        world.add(new Location(9, 9), coyote2);

        // add some Boulders to the grid
        Random rand = new Random();
        int numBoulders = 10;
        for (int i = 0; i < numBoulders; i++) {
            int x = rand.nextInt(10);
            int y = rand.nextInt(10);
            Boulder boulder = new Boulder();
            world.add(new Location(x, y), boulder);
        }

        // show the world
        world.show();
    }
}

