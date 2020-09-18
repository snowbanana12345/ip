package duke;
import duke_command.Command;
import duke_command.CommandManager;
import duke_command.FieldManager;
import duke_command.DukeField;

import java.util.Hashtable;

public class DukeManager {
    private final TaskManager taskManager;
    private final CommandManager commandManager;
    private final FieldManager fieldManager;
    private final MessageCreater messageCreater;
    private boolean active;
    private Hashtable<DukeField, String> inputFields;
    public DukeManager(CommandManager commandManager, FieldManager fieldManager
            , TaskManager taskManager, MessageCreater messageCreater){
        this.taskManager = taskManager;
        this.commandManager = commandManager;
        this.fieldManager = fieldManager;
        this.messageCreater = messageCreater;
    }

    public void start(){
        activate();
        messageCreater.greet();
    }


    private void stop(){
        deactivate();
        messageCreater.sayBye();
    }



    public boolean isActive(){
        return this.active;
    }

    /***
     *
     * @param userInput : the user input
     *  The user input is assumed to be in the format
     *  {user command} /{field 1} {input 1} /{field 2} {input 2} and so on
     */

    public void recieveUserInput(String userInput){
        inputFields = new Hashtable<>();
        String[] userInputs = userInput.split(" ");
        inputFields.put(DukeField.COMMAND, userInputs[0]);
        DukeField currentField = null;
        StringBuilder currentInput = new StringBuilder();
        for (String input : userInputs) {
            Character c = input.charAt(0);
            if (c.equals('/')) {
                if (!(currentField == null)) {
                    inputFields.put(currentField, currentInput.toString());
                    currentInput = new StringBuilder();
                }
                currentField = fieldManager.getField(input.substring(1));
            } else {
                currentInput.append(input);
            }
        }
        if (!(currentField == null)){
            inputFields.put(currentField, currentInput.toString());
        }
    }

    public void executeUserInput() {
        Command userCommand = commandManager.getCommand(inputFields.get(DukeField.COMMAND));
        switch (userCommand) {
        case COMMAND_EXIT:
            stop();
            break;
        case COMMAND_ADD_DEADLINE:
            taskManager.addDeadLine(inputFields.get(DukeField.NAME),inputFields.get(DukeField.TIME));
            messageCreater.addDeadLine(inputFields.get(DukeField.NAME),inputFields.get(DukeField.TIME));
            break;
        case COMMAND_ADD_TODO:
            taskManager.addToDo(inputFields.get(DukeField.NAME));
            messageCreater.addTodo(inputFields.get(DukeField.NAME));
            break;
        case COMMAND_ADD_EVENT:
            taskManager.addEvent(inputFields.get(DukeField.NAME),inputFields.get(DukeField.TIME));
            messageCreater.addEvent(inputFields.get(DukeField.NAME),inputFields.get(DukeField.TIME));
        case COMMAND_LIST_STORED_TASKS:
            messageCreater.listTasks(taskManager.getTaskList());
            break;
        default:
            System.out.println("im not sure what you want me to do");
            break;
        }
    }


    // #### --------- Lower level implementations ----------- ####
    private void activate() {
        this.active = true;
    }

    private void deactivate() {
        this.active = false;
    }
}
