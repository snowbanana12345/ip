package main.command;

import main.exception.InvalidCommandException;

public abstract class CommandManager {
    /***
     * Takes in the string form of the user command and coverts it to the enum class
     * @param userCommand the string input command from the user
     * @return the enum class COMMAND
     */
    public abstract Command getCommand(String userCommand) throws InvalidCommandException;

}
