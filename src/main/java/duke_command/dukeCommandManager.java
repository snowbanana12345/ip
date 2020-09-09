package duke_command;
import java.util.Hashtable;


public abstract class dukeCommandManager {
    static Hashtable<String,Command> commandTable = new Hashtable<>();
    static {
        commandTable.put("bye",Command.COMMAND_EXIT);
        commandTable.put("list", Command.COMMAND_LIST_STORED_TASKS);
        commandTable.put("deadline",Command.COMMAND_ADD_DEADLINE);
        commandTable.put("todo", Command.COMMAND_ADD_TODO);
        commandTable.put("event", Command.COMMAND_ADD_EVENT);
        commandTable.put("done",Command.COMMAND_SET_TASK_DONE);
        commandTable.put("instructions", Command.COMMAND_INSTRUCTIONS);
    }
    static Hashtable<Command,String> userCommandTable = new Hashtable<>();
    static {
        userCommandTable.put(Command.COMMAND_EXIT,"bye");
        userCommandTable.put(Command.COMMAND_LIST_STORED_TASKS,"list");
        userCommandTable.put(Command.COMMAND_ADD_DEADLINE,"deadline");
        userCommandTable.put(Command.COMMAND_ADD_TODO,"todo");
        userCommandTable.put(Command.COMMAND_ADD_EVENT,"event");
        userCommandTable.put(Command.COMMAND_SET_TASK_DONE,"done");
        userCommandTable.put(Command.COMMAND_INSTRUCTIONS,"instructions");
    }

    public static Command getCommand(String userCommand){
        return commandTable.get(userCommand);
    }

    public static boolean isValidCommand(String userCommand){
        return commandTable.containsKey(userCommand);
    }

    public static String getUserCommand(Command command){
        return userCommandTable.get(command);
    }
}
