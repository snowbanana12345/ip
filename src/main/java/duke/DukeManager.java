package duke;
import duke_command.Command;
import duke_command.CommandManager;
import duke_command.FieldManager;
import duke_command.DukeField;

import java.util.Hashtable;

public class DukeManager {
    private final DukeTaskManager taskManager;
    private final CommandManager commandManager;
    private final FieldManager fieldManager;
    private boolean active;
    private Hashtable<DukeField, String> inputFields;
    public DukeManager(CommandManager commandManager, FieldManager fieldManager){
        this.taskManager = new DukeTaskManager();
        this.commandManager = commandManager;
        this.fieldManager = fieldManager;
    }

    public void start(){
        this.active = true;
    }

    private void stop(){
        this.active = false;
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
            active = false;
            break;
        default:
            System.out.println("im not sure what you want me to do");
            break;
        }
    }
}
