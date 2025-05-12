package edu.grinnell.csc207.textadventure;

public class Noyce extends Room {
    private boolean hasConfronted = false;
    private boolean hasIgnored = false;
    private boolean canProceed = false;

    public Noyce() {
        this.description = "You enter Noyce Science Center. The scent of whiteboards and stress hangs in the air.";
    }

    @Override
    public void enter() {
        System.out.println(description);

        if (!hasConfronted && !hasIgnored) {
            System.out.println("Up ahead, you spot your lab partner from last week—the one who totally ghosted you on the project.");
            System.out.println("Will you 'confront' him or just 'ignore' and move on?");
        } else if (canProceed) {
            System.out.println("The hallway is clear now. You can go 'up' to take the elevator.");
        }
    }

    @Override
    public String handleCommand(Command cmd, Player player) {
        switch (cmd.getAction()) {
            case "confront":
                if (!hasConfronted && !hasIgnored) {
                    hasConfronted = true;
                    canProceed = true;
                    return "'Hey, about last week—' you start. He mumbles an apology and scurries off. You feel a bit better.";
                }
                return "You already dealt with that.";
            case "ignore":
                if (!hasConfronted && !hasIgnored) {
                    hasIgnored = true;
                    canProceed = true;
                    return "You sigh, look away, and keep walking. Some people just aren't worth the drama.";
                }
                return "You already made your choice.";
            case "go":
                if (cmd.getTarget().equals("up") && canProceed) {
                    Room next = exits.get("up");
                    if (next != null) {
                        player.moveTo(next);
                        return "You walk past the encounter zone and head for the elevator.";
                    }
                    return "There's no way up from here.";
                }
                return "You need to resolve the lab partner situation before continuing.";
            default:
                return "That doesn't help you here.";
        }
    }
}