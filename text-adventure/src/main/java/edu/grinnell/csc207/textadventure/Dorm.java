package edu.grinnell.csc207.textadventure;

public class Dorm extends Room {
    private boolean tookHairTie = false;
    private boolean didReading = false;
    private boolean readyToLeave = false;
    private boolean left = false;

    public Dorm() {
        this.description = "You're in your dorm. It's almost time to head to class.";
    }

    @Override
    public void enter() {
        if (!left) {
            System.out.println(description);
            System.out.println("On your desk: a hair tie and your reading for class.");
            System.out.println("Type 'pick up hair tie' or 'read' before you 'go north' to leave.");
        } else {
            System.out.println("You're no longer in the dorm.");
        }
    }

    @Override
    public String handleCommand(Command cmd, Player player) {
        switch (cmd.getAction()) {
            case "pick":
                if (cmd.getTarget().contains("hair tie") && !tookHairTie) {
                    Item tie = new Item("hair tie", "Keeps your hair out of your face in wind.");
                    player.addItem(tie);
                    tookHairTie = true;
                    return "You pick up the hair tie and slip it on your wrist.";
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
                if (cmd.getTarget().equals("north")) {
                    left = true;
                    if (tookHairTie) player.setFlag("hasHairTie", true);
                    player.moveTo(exits.get("north"));
                    return "You leave your dorm, heading toward class.";
                }
                return "You can only go north from here.";
            default:
                return "That doesn't do much in your dorm.";
        }
    }
}