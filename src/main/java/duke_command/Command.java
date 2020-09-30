package duke_command;

public enum Command {
    COMMAND_EXIT("bye", "exits the program"),
    COMMAND_LIST_STORED_TASKS("list", "list all stored tasks"),
    COMMAND_SET_TASK_DONE("done", "sets a task to done"),
    COMMAND_ADD_TODO("todo", "adds a todo"),
    COMMAND_ADD_DEADLINE("deadline", "adds a deadline"),
    COMMAND_ADD_EVENT("event", "adds a user event"),
    COMMAND_INSTRUCTIONS("instructions", "prints out the set of instructions for command"),
    COMMAND_NULL("null", "does nothing"),
    COMMAND_DELETE("delete", "deletes an entry"),
    COMMAND_SAVE("save","saves the task list");

    private final String userInput;
    private final String commandFunction;
    Command(String userInput, String commandFunction){
        this.userInput = userInput;
        this.commandFunction = commandFunction;
    }

    public String getUserInput(){
        return this.userInput;
    }

    @Override
    public String toString(){
        return "Duke Command : " + this.userInput + " : " + this.commandFunction;
    }
}
