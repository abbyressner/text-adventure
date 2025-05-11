package edu.grinnell.csc207.textadventure;

import java.awt.im.InputContext;
import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;


/**
 * Main class for the text adventure game "You're Late".
 * The player must navigate through rooms to reach their class on time.
 */
public class TextAdventure {
    private Player player;
    private Parser parser;
    private Map<String, Room> rooms;

    public static void main(String[] args) {
        TextAdventure game = new TextAdventure();
        game.run();
    }

    public void run() {
        parser = new Parser();
        setupRooms();
        player = new Player(rooms.get("DormRoom"));

        System.out.println();
        System.out.println("Welcome to the 'You're Late' Adventure Game!");
        System.out.println();


        while (!player.hasWon() && !player.hasLost()) {
            player.getCurrentRoom().enter();
            System.out.print("> ");
            String input = new Scanner(System.in).nextLine();
            Command command = parser.parse(input);
            String result = player.getCurrentRoom().handleCommand(command, player);
            System.out.println(result);
            
        }

        System.out.println(player.hasWon() ? "You made it to class!" : "You’re late. Game over.");
    }

    private void setupRooms() {
        rooms = new HashMap<>();

        Room dorm = new Dorm();
        Room outside = new Outside();
        Room elevator = new Elevator();
        Room noyce = new NoyceEntry();
        Room class = new Classroom();

        dorm.setExit("north", outside);
        outside.setExit("north", noyce);
        noyce.setExit("up", elevator);
        elevator.setExit("north", class);

        rooms.put("DormRoom", dorm);
        rooms.put("Outside", outside);
        rooms.put("Elevator", elevator);
        rooms.put("NoyceEntry", noyce);
        rooms.put("Classroom", class);
    }
}