package edu.grinnell.csc207.textadventure;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Driver class for a text adventure game.
 */
public class TextAdventure {

    public static final String C = "\u001B[38;5;147m";
    public static final String R = "\u001B[0m";
    public static final String B = "\u001B[1m";

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
        rooms = new HashMap<>();

        Room dorm = new Dorm();
        Room outside = new Outside();
        Room elevator = new Elevator();
        Room noyce = new Noyce();
        Room classroom = new Classroom();

        dorm.setExit("forward", outside);
        outside.setExit("forward", noyce);
        noyce.setExit("up", elevator);
        elevator.setExit("forward", classroom);

        rooms.put("Dorm", dorm);
        rooms.put("Outside", outside);
        rooms.put("Elevator", elevator);
        rooms.put("Noyce", noyce);
        rooms.put("Classroom", classroom);

        player = new Player(rooms.get("Dorm"));

        System.out.println();
        System.out.println();
        System.out.println(C + "* * * * * * * * * * * * * * * * * * * * * *" + R);
        System.out.println(C + "*" + R + B + "    Welcome to Abby's Adventure Game!    " + R + C + "*");
        System.out.println(C + "* * * * * * * * * * * * * * * * * * * * * *" + R);
        System.out.println(C + "*" + R + "                                         " + C + "*");
        System.out.println(C + "*" + R + "   Your goal is to make it to class      " + C + "*");
        System.out.println(C + "*" + R + "   on time. Sounds pretty easy, right?   " + C + "*");
        System.out.println(C + "*" + R + "   Well, it's not always that simple.    " + C + "*");
        System.out.println(C + "*" + R + "   I know that because the obstacles     " + C + "*");
        System.out.println(C + "*" + R + "   you are about to face are based on    " + C + "*");
        System.out.println(C + "*" + R + "   my real-life experiences!             " + C + "*");
        System.out.println(C + "*" + R + "                                         " + C + "*");
        System.out.println(C + "*" + R + "   Good luck! :)                         " + C + "*");
        System.out.println(C + "*" + R + "    — Abby                               " + C + "*");
        System.out.println(C + "*" + R + "                                         " + C + "*");
        System.out.println(C + "* * * * * * * * * * * * * * * * * * * * * *" + R);
        System.out.println();

        while (!player.hasWon() && !player.hasLost()) {
            player.getCurrentRoom().enter();
            System.out.print("> ");
            String input = scanner.nextLine();
            Command cmd = parser.parse(input);
            String result = player.getCurrentRoom().handleCommand(cmd, player);
            System.out.println(result);
        }

        if (player.hasWon()) {
            System.out.println("You made it to class!");
        } else if (player.hasLost()) {
            System.out.println("You're late. Game over.");
        }
        System.out.println();
        scanner.close();
    }
}
