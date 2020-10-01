package duke;
import duke_command.Command;
import duke_command.CommandManager;
import duke_command.FieldManager;
import duke_command.DukeField;
import duke_exception.*;

import java.util.Hashtable;

public class DukeManager {
    private final TaskManager taskManager;
    private final CommandManager commandManager;
    private final FieldManager fieldManager;
    private final MessageCreater messageCreater;
    private final TaskSaver taskSaver;
    private final InputParser inputParser;
    private boolean active;
    private Hashtable<DukeField, String> inputFields;
    public DukeManager(CommandManager commandManager, FieldManager fieldManager
            , TaskManager taskManager, MessageCreater messageCreater, TaskSaver taskSaver, InputParser inputParser){
        this.taskManager = taskManager;
        this.commandManager = commandManager;
        this.fieldManager = fieldManager;
        this.messageCreater = messageCreater;
        this.taskSaver = taskSaver;
        this.inputParser = inputParser;
        this.active = false;
        this.inputFields = new Hashtable<>();
    }

    public void start(){
        activate();
        messageCreater.startLoopMessage();
        messageCreater.greet();
        this.taskSaver.init();
        messageCreater.endLoopMessage();
    }

    private void stop(){
        deactivate();
        messageCreater.startLoopMessage();
        messageCreater.sayBye();
        messageCreater.endLoopMessage();
    }

    public boolean isActive(){
        return this.active;
    }

    /***
     * Main control function for duke
     * This function also does all the error handling
     * @param userInput the user input
     */
    public void receiveAndExecuteUserInput(String userInput){
        try {
            recieveUserInput(userInput);
            executeUserInput();
            clearUserInput();
        }
        catch (EmptyInputException e){
            messageCreater.describeException(e);
        }
        catch (InvalidCommandException e){
            messageCreater.describeException(e);
        }
        catch (NumberInputException e){
            messageCreater.describeException(e);
        }
        catch (InvalidFieldException e){
            messageCreater.describeException(e);
        }
        catch (EmptyFieldException e){
            messageCreater.describeException(e);
        }
        catch (BadInputException e){
            messageCreater.describeException(e);
        }
    }

    private void clearUserInput(){
        inputFields = new Hashtable<>();
    }

    /***
     *
     * @param userInput : the user input
     *  The user input is assumed to be in the format
     *  {user command} /{field 1} {input 1} /{field 2} {input 2} and so on
     */
    public void recieveUserInput(String userInput)
            throws EmptyInputException, InvalidFieldException {
        this.inputParser.parseUserInput(userInput, inputFields, fieldManager);
    }

    /***
     * Main place where the functions are done
     */
    public void executeUserInput()
            throws InvalidCommandException, NumberInputException, EmptyFieldException, BadInputException {
        try {
            messageCreater.startLoopMessage();
            Command userCommand = commandManager.getCommand(inputFields.get(DukeField.COMMAND));
            switch (userCommand) {
            case COMMAND_EXIT:
                stop();
                break;
            case COMMAND_ADD_DEADLINE:
                taskManager.addDeadLine(inputFields.get(DukeField.NAME), inputFields.get(DukeField.TIME));
                messageCreater.addDeadLine(inputFields.get(DukeField.NAME), inputFields.get(DukeField.TIME));
                messageCreater.numberOfTasks(taskManager.getNumberOfTasks());
                break;
            case COMMAND_ADD_TODO:
                taskManager.addToDo(inputFields.get(DukeField.NAME));
                messageCreater.addTodo(inputFields.get(DukeField.NAME));
                messageCreater.numberOfTasks(taskManager.getNumberOfTasks());
                break;
            case COMMAND_ADD_EVENT:
                taskManager.addEvent(inputFields.get(DukeField.NAME), inputFields.get(DukeField.TIME));
                messageCreater.addEvent(inputFields.get(DukeField.NAME), inputFields.get(DukeField.TIME));
                messageCreater.numberOfTasks(taskManager.getNumberOfTasks());
                break;
            case COMMAND_LIST_STORED_TASKS:
                messageCreater.listTasks(taskManager.getTaskList());
                break;
            case COMMAND_SET_TASK_DONE:
                taskManager.markAsDone(inputFields.get(DukeField.INDEX));
                messageCreater.markAsDone(inputFields.get(DukeField.INDEX));
                break;
            case COMMAND_DELETE:
                messageCreater.deleteTask(taskManager.getTaskDescription(inputFields.get(DukeField.INDEX)));
                messageCreater.numberOfTasks(taskManager.getNumberOfTasks());
                taskManager.deleteTask(inputFields.get(DukeField.INDEX));
            case COMMAND_SAVE:
                taskSaver.save(taskManager.getTaskList(), inputFields.get(DukeField.NAME));
                break;
            case COMMAND_LIST_BY_DATE:
                messageCreater.listTasks(taskManager.getFilteredTaskListByDateTime(inputFields.get(DukeField.TIME)));
            case COMMAND_LOAD:
                taskManager.load(taskSaver.load(inputFields.get(DukeField.NAME)));
                break;
            default:
                messageCreater.defaultMessage();
                break;
            }
            messageCreater.endLoopMessage();
        }
        catch (InvalidCommandException e){
            throw e;
        }
        catch (NumberInputException e){
            throw e;
        }
        catch (EmptyFieldException e){
            throw e;
        }
        catch (BadInputException e){
            throw e;
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
