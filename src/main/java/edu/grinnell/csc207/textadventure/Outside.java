package edu.grinnell.csc207.textadventure;

import java.util.Random;

/**
 * the Outside class
 */
public class Outside extends Room {

    private String weather;
    private boolean canProceed = false;
    private boolean processed = false;

    public Outside() {
        this.description = "You step outside the dorm. Iowa weather greets you.";
        this.weather = randomWeather();
    }

    private String randomWeather() {
        String[] types = {"wind", "pleasant"};
        return types[new Random().nextInt(types.length)];
    }

    @Override
    public void enter() {
        if (!processed) {
            System.out.println(description);
            switch (weather) {
                case "wind":
                    System.out.println("A brutal wind hits you head-on. You can barely see.");
                    break;
                case "pleasant":
                    System.out.println("Shockingly, it's sunny and mild. You can walk forward easily.");
                    canProceed = true;
                    break;
            }
            processed = true;
        } else {
            System.out.println("You're still outside. Weather unchanged: " + weather + ".");
        }
    }

    @Override
    public String handleCommand(Command cmd, Player player) {
        switch (cmd.getAction()) {
            case "go":
                if (!canProceed && weather.equals("wind")) {
                    if (player.getFlag("hasHairTie")) {
                        canProceed = true;
                        return "You tie your hair up. Now you can see! You may proceed forward.";
                    } else {
                        player.setLost(true);
                        return "Your hair blows into your face. You trip, fall, and decide to give up. Game over.";
                    }
                }

                if (canProceed && cmd.getTarget().equals("forward")) {
                    Room next = exits.get("forward");
                    if (next != null) {
                        player.moveTo(next);
                        return "You move forward into Noyce.";
                    }
                }
                return "You can't go that way right now.";
            default:
                return "That doesn't help here.";
        }
    }
}
