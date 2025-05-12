package edu.grinnell.csc207.textadventure;
import java.util.HashMap;
import java.util.Map;

/**
 * the Room abstract class represents rooms in the text adventure game.
 */
public abstract class Room {
    protected Map<String, Room> exits;
    protected String description;

    public Room() {
        this.exits = new HashMap<>();
    }

    public void setExit(String direction, Room room) {
        exits.put(direction, room);
    }
    
    public Room getExit(String direction) {
        return exits.get(direction);
    }

    public abstract void enter();
    public abstract String handleCommand(Command cmd, Player player);
}
