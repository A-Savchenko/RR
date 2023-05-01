import info.gridworld.actor.*;
import info.gridworld.grid.*;

import java.util.ArrayList;

/**
 * The RR class represents a critter that moves in a straight line, changing
 * direction every three steps. If an adjacent location contains a coyote, the
 * coyote is removed from the grid and replaced with a sick coyote in a
 * randomly-selected adjacent location. If an adjacent location contains a
 * boulder, the boulder is removed from the grid and replaced with a kaboom.
 * Otherwise, the critter moves to a randomly-selected adjacent location.
 */
public class RR extends Critter {

    private int steps;
    private boolean foundLoc = false;
    private Location next;
    /**
     * Constructs an RR critter with no color and a default direction of NORTH.
     */
    public RR() {
        setColor(null);
        setDirection(Location.NORTH);
        steps = 0;
    }

    /**
     * Moves the critter to a randomly-selected adjacent location, unless the
     * adjacent location contains a stone. If the critter moves, it changes
     * direction every three steps.
     */
    public void act() {
        ArrayList<Location> possibleLocs = getMoveLocations();

        Location nextLoc = selectMoveLocation(possibleLocs);
        if (nextLoc == null) {
            removeSelfFromGrid();
            return;
        }

        if (getGrid().get(nextLoc) instanceof Stone) {
            return;
        }

        moveTo(nextLoc);
        steps++;
        if (steps % 3 == 0) {
            setDirection(getDirection() + Location.HALF_LEFT + Location.HALF_LEFT + Location.HALF_LEFT);
        }
    }

    /**
     * Returns a list of all empty and non-stone adjacent locations, except for
     * those that contain a sick coyote, a kaboom, or a critter that is not a
     * coyote.
     * @return a list of all valid move locations for the critter
     */
    public ArrayList<Location> getMoveLocations() {
        foundLoc = false;
        ArrayList<Location> locs = new ArrayList<Location>();
        Grid<Actor> grid = getGrid();
        if (grid == null) {
            return locs;
        }
        Location loc = getLocation();
        for (int d = 0; d < 360; d += 45) {
            next = loc.getAdjacentLocation(d);
            if (grid.isValid(next)) {
                Actor neighbor = grid.get(next);
                if (neighbor instanceof Coyote) {
                    Coyote coyote = (Coyote) neighbor;
                    Location coyoteLoc = coyote.getLocation();
                    ArrayList<Location> adjLocs = grid.getValidAdjacentLocations(coyoteLoc);
                    Location nextLoc = (adjLocs.get((int)(Math.random()*adjLocs.size())));
                    if (grid.isValid(nextLoc) && grid.get(nextLoc) == null) {
                        coyote.removeSelfFromGrid();
                        SickCoyote sickCoyote = new SickCoyote();
                        sickCoyote.putSelfInGrid(grid, nextLoc);
                        locs.clear();
                        locs.add(next);
                        return locs;
                    }
                }
                else if(neighbor instanceof RR)
                {
                    setDirection(getDirection()+90 );
                }
                else if (neighbor instanceof Boulder) {
                    neighbor.removeSelfFromGrid();
                    Kaboom k = new Kaboom();
                    k.putSelfInGrid(grid, next);
                }
                else if (!(neighbor instanceof Stone) && !(neighbor instanceof SickCoyote) && !(neighbor instanceof Kaboom)) {
                    locs.add(next);
                }
            }
        }
        return locs;
    }

    /**
     * Selects a move location at random from a list of possible locations.
     * @param locs the list of possible locations to move to
     * @return a randomly-selected location from the list, or null if the list
     * is empty
     */

    public Location selectMoveLocation(ArrayList<Location> locs) {
        if (locs.size() == 0) {
            return null;
        }
        int r = (int) (Math.random() * locs.size());
        return locs.get(r);
    }


    /**
     *  Returns the current direction of this critter.
     *  @return North
     */
    @Override
    public int getDirection() {
        return Location.NORTH;
    }
}
