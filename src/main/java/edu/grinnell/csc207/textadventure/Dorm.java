package edu.grinnell.csc207.textadventure;

/**
 * the Dorm class
 */
public class Dorm extends Room {

    private boolean tookHairTie = false;
    private boolean tookMints = false;
    private boolean didReading = false;
    private boolean readyToLeave = false;
    private boolean left = false;

    /**
     * Constructor for the Dorm class
     */
    public Dorm() {
        this.description = "You're in your dorm. It's almost time to head to class.";
    }

    @Override
    public void enter() {
        if (!left) {
            System.out.println(description);
            System.out.println("On your desk: a hair tie, a pack of mints, and your reading for class.");
            if (!didReading) {
                System.out.println("\"$#@%, I forgot to do the reading\", you say to yourself.");
            }
            System.out.println("Type 'pick up hair tie', 'pick up mints', or 'read' before you 'go forward' to leave.");
        } else {
            System.out.println("You're no longer in the dorm.");
        }
    }

    /**
     * Handles the command given by the player.
     *
     * @param cmd the command to handle.
     * @param player the player who issued the command.
     * @return message indicating the result of the command.
     */
    @Override
    public String handleCommand(Command cmd, Player player) {
        switch (cmd.getAction()) {
            case "pick":
                if (cmd.getTarget().contains("hair tie") && !tookHairTie) {
                    Item tie = new Item("hair tie",
                            "Keeps your hair out of your face in wind.");
                    player.addItem(tie);
                    tookHairTie = true;
                    return "You pick up the hair tie and slip it on your wrist.";
                }
                if (cmd.getTarget().contains("mints") && !tookMints) {
                    Item mints 
                        = new Item("mints",
                            "A pack of mints (that'll be more helpful than you think).");
                    player.addItem(mints);
                    tookMints = true;
                    return "You pick up the mints and put it in your bag.";
                }
                return "There's nothing like that here.";
            case "read":
                if (!didReading) {
                    didReading = true;
                    player.setFlag("didReading", true);
                    return "You skim through the reading and attempt the reading question in Canvas. \n It's probably wrong...whatever.";
                }
                return "You've already done the reading.";
            case "go":
                if (cmd.getTarget().equals("forward")) {
                    left = true;
                    if (tookHairTie) {
                        player.setFlag("hasHairTie", true);
                    }
                    player.moveTo(exits.get("forward"));
                    return "You leave your dorm and head toward class.";
                }
                return "You can only go forward from here.";
            default:
                return "That doesn't do much in your dorm.";
        }
    }
}
