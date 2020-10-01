package duke_command;

import duke_exception.InvalidCommandException;

public abstract class CommandManager {
    /***
     * Takes in the string form of the user command and coverts it to the enum class
     * @param userCommand the string input command from the user
     * @return the enum class COMMAND
     */
    public abstract Command getCommand(String userCommand) throws InvalidCommandException;

    /***
     * Checks if the userCommand is one of the valid commands set by the CommandManager
     * @param userCommand the string input command from user
     * @return true if the string from user command is in the list of commands in the CommandManager
     */
    public abstract boolean isValidCommand(String userCommand);
}
