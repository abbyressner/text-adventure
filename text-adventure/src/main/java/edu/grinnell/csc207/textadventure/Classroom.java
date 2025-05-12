package edu.grinnell.csc207.textadventure;

public class Classroom extends Room {

    public Classroom() {
        this.description = "You walk into the CS classroom .";
    }

    @Override
    public void enter() {
        System.out.println(description);
    }

    @Override
    public String handleCommand(Command cmd, Player player) {
        if (!player.getFlag("didReading")) {
            player.setWon(true);
            return "You awkwardly sit down, feeling unprepared. You technically win, but good luck with the lab...";
        } else {
            player.setWon(true);
            return "You sit down, confident and ready. You win!";
        }
    }
}