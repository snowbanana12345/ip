package duke_command;

public abstract class CommandManager {
    /***
     * Takes in the string form of the user command and coverts it to the enum class
     * @param userCommand the string input command from the user
     * @return the enum class COMMAND
     */
    public abstract Command getCommand(String userCommand);

    /***
     * Checks if the userCommand is one of the valid commands set by the CommandManager
     * @param userCommand the string input command from user
     * @return true if the string from user command is in the list of commands in the CommandManager
     */
    public abstract boolean isValidCommand(String userCommand);

    /***
     * This allows command to be converted from the enum class command back to the string.
     * @param command the enum class of the command
     * @return the string version of the command
     */
    public abstract String getUserCommand(Command command);
}
