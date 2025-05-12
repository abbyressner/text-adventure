package edu.grinnell.csc207.textadventure;

/* 
 * the Elevator class
 */
public class Elevator extends Room {

    private int smellLevel = 0;
    private boolean doorOpen = false;

    public Elevator() {
        this.description = "You step into the Noyce elevator. It smells... wrong.";
    }

    @Override
    public void enter() {
        System.out.println(description);
        if (!doorOpen) {
            System.out.println("The elevator doors slowly close.");
        } else {
            System.out.println("The elevator doors are open. You may go forward.");
        }
    }

    @Override
    public String handleCommand(Command cmd, Player player) {
        switch (cmd.getAction()) {
            case "wait":
                smellLevel++;
                if (smellLevel > 2) {
                    player.setLost(true);
                    return "The smell overwhelms you. You pass out. Game over.";
                }
                return "You wait. It's getting worse...";
            case "use":
                if (cmd.getTarget().contains("mints") && player.hasItem("mints")) {
                    smellLevel = Math.max(0, smellLevel - 1);
                    return "You pop a mint and feel slightly better.";
                }
                return "That doesn't help right now.";
            case "go":
                if (!doorOpen && cmd.getTarget().equals("forward")) {
                    doorOpen = true;
                    return "The elevator reaches your floor. The door opens. You may go forward.";
                } else if (doorOpen && cmd.getTarget().equals("forward")) {
                    Room next = exits.get("forward");
                    if (next != null) {
                        player.moveTo(next);
                        return "You rush out of the elevator.";
                    }
                }
                return "You can't go that way.";
            default:
                return "Nothing happens.";
        }
    }
}
