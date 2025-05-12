package edu.grinnell.csc207.textadventure;

/**
 * the Classroom class 
 */
public class Classroom extends Room {

    /**
     * Constructor for the Classroom class.
     */
    public Classroom() {
        this.description = "You walk into the CS classroom.";
    }

    /**
     * Displays the description of the classroom when the player enters.
     */
    @Override
    public void enter() {
        System.out.println(description);
    }

    /**
     * Handles the command given by the player.
     * 
     * @param cmd  the command to handle.
     * @param player  the player who issued the command.
     * @return  message indicating the result of the command.
     */
    @Override
    public String handleCommand(Command cmd, Player player) {
        if (!player.getFlag("didReading")) {
            player.setWon(true);
            return """
                    You awkwardly sit down, feeling unprepared. 
                    You technically win, but good luck with the lab...
                   """;
        } else {
            player.setWon(true);
            return "You sit down, confident and ready. You win!";
        }
    }
}