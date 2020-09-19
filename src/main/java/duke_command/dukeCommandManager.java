package duke_command;
import duke_exception.InvalidCommandException;

import java.util.Hashtable;
import java.util.Map;

/***
 * This purpose of this class is to avoid magic strings
 *
 */
public class DukeCommandManager extends CommandManager{
    private static final String COMMAND_EXIT = "exit";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_INSTRUCTIONS = "help";
    private static final String COMMAND_DELETE = "delete";

    private static final String ERROR_NO_SUCH_COMMAND = "There is no such command!";

    private final Hashtable<String,Command> commandTable;
    private final Hashtable<Command, String> userCommandTable;

    public DukeCommandManager(){
        this.commandTable = new Hashtable<>();
        commandTable.put(COMMAND_EXIT ,Command.COMMAND_EXIT);
        commandTable.put(COMMAND_LIST, Command.COMMAND_LIST_STORED_TASKS);
        commandTable.put(COMMAND_DEADLINE,Command.COMMAND_ADD_DEADLINE);
        commandTable.put(COMMAND_TODO, Command.COMMAND_ADD_TODO);
        commandTable.put(COMMAND_EVENT, Command.COMMAND_ADD_EVENT);
        commandTable.put(COMMAND_DONE,Command.COMMAND_SET_TASK_DONE);
        commandTable.put(COMMAND_INSTRUCTIONS, Command.COMMAND_INSTRUCTIONS);
        commandTable.put(COMMAND_DELETE, Command.COMMAND_DELETE);

        this.userCommandTable = new Hashtable<>();
        for(Map.Entry<String, Command> entry : commandTable.entrySet()){
            userCommandTable.put(entry.getValue(), entry.getKey());
        }
    }

    public Command getCommand(String userCommand)
        throws InvalidCommandException{
        Command command = commandTable.get(userCommand);
        if (command == null) {
            throw new InvalidCommandException(ERROR_NO_SUCH_COMMAND);
        }
        return command;
    }

    public boolean isValidCommand(String userCommand){
        return commandTable.containsKey(userCommand);
    }

    public  String getUserCommand(Command command){
        return userCommandTable.get(command);
    }
}
