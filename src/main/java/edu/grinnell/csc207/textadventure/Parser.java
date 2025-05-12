package edu.grinnell.csc207.textadventure;

/**
 * the Parser class
 */
public class Parser {

    /**
     * Parses the input string and returns a Command object.
     *
     * @param input the input string to parse
     * @return a command object representing the action and target
     */
    public Command parse(String input) {
        input = input.trim().toLowerCase();
        String[] words = input.split(" ", 2);
        String action = words[0];
        String target = (words.length > 1) ? words[1] : "";
        return new Command(action, target);
    }
}
