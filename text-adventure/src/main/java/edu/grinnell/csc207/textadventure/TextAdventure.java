package edu.grinnell.csc207.textadventure;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/**
 * Driver class for a text adventure game.
 */
public class TextAdventure {
    private Player player;
    private Parser parser;
    private Map<String, Room> rooms;

    public TextAdventure() {
        rooms = new HashMap<>();
    }

    public static void main(String[] args) {
        TextAdventure game = new TextAdventure();
        game.run();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        parser = new Parser();
        setupRooms();
        player = new Player(rooms.get("Dorm"));

        System.out.println("* * * * * * * * * * * * * * * * * * * * * *");
        System.out.println("*    Welcome to Abby's Adventure Game!    *");
        System.out.println("* * * * * * * * * * * * * * * * * * * * * *");
        System.out.println("*                                         *");
        System.out.println("*   Your goal is to make it to class");
        System.out.println("*   on time. Sounds pretty easy, right?");
        System.out.println("*   Well, it's not always that simple.");
        System.out.println("*   I know that because the obstacles");
        System.out.println("*   you are about to face are based on");
        System.out.println("*   my real-life experiences!");
        System.out.println("*                                         *");
        System.out.println("*   Good luck! :)");
        System.out.println("*    \u2014 Abby");
        System.out.println("*                                         *");


        while (!player.hasWon() && !player.hasLost()) {
            player.getCurrentRoom().enter();
            System.out.print("> ");
            String input = scanner.nextLine();
            Command cmd = parser.parse(input);
            String result = player.getCurrentRoom().handleCommand(cmd, player);
            System.out.println(result);
            
        }

        System.out.println(player.hasWon() ? "You made it to class!" : "You’re late. Game over.");
    }

    private void setupRooms() {
        rooms = new HashMap<>();

        Room dorm = new Dorm();
        Room outside = new Outside();
        Room elevator = new Elevator();
        Room noyce = new Noyce();
        Room classroom = new Classroom();

        dorm.setExit("north", outside);
        outside.setExit("north", noyce);
        noyce.setExit("up", elevator);
        elevator.setExit("north", classroom);

        rooms.put("Dorm", dorm);
        rooms.put("Outside", outside);
        rooms.put("Elevator", elevator);
        rooms.put("Noyce", noyce);
        rooms.put("Classroom", classroom);
    }
}