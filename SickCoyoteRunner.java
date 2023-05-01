import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

public class SickCoyoteRunner {
    public static void main(String[] args) {
        ActorWorld world = new ActorWorld();

        // create and add SickCoyotes to the world
        SickCoyote sickCoyote1 = new SickCoyote(5);
        SickCoyote sickCoyote2 = new SickCoyote(8);
        SickCoyote sickCoyote3 = new SickCoyote(3);
        SickCoyote sickCoyote4 = new SickCoyote(6);

        world.add(new Location(0, 0), sickCoyote1);
        world.add(new Location(0, 4), sickCoyote2);
        world.add(new Location(4, 0), sickCoyote3);
        world.add(new Location(4, 4), sickCoyote4);

        world.show();
    }
}
