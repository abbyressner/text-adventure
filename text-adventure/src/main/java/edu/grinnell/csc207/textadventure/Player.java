package edu.grinnell.csc207.textadventure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player {

    private Room currentRoom;
    private List<Item> inventory = new ArrayList<>();
    private Map<String, Boolean> flags = new HashMap<>();
    private boolean won = false;
    private boolean lost = false;

    public Player(Room startRoom) {
        this.currentRoom = startRoom;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void moveTo(Room room) {
        this.currentRoom = room;
    }

    public void addItem(Item item) {
        inventory.add(item);
    }

    public boolean hasItem(String name) {
        return inventory.stream().anyMatch(i -> i.getName().equalsIgnoreCase(name));
    }

    public void setWon(boolean won) {
        this.won = won;
    }

    public void setLost(boolean lost) {
        this.lost = lost;
    }

    public boolean hasWon() {
        return won;
    }

    public boolean hasLost() {
        return lost;
    }

    public void setFlag(String key, boolean value) {
        flags.put(key, value);
    }

    public boolean getFlag(String key) {
        return flags.getOrDefault(key, false);
    }
}
