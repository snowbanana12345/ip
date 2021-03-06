package main.duke;
import main.command.Command;
import main.command.CommandManager;
import main.command.FieldManager;
import main.command.DukeField;
import main.exception.*;

import java.util.Hashtable;

/***
 * This classes manages all of the smaller classes such as deciding when to call each of their functions
 * and pass data from each class to the other
 */
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

    /***
     * This function is called when duke is first turned on
     */
    public void start(){
        activate();
        messageCreater.startLoopMessage();
        messageCreater.greet();
        taskSaver.init();
        messageCreater.endLoopMessage();
    }

    /***
     * this function is called when an exit command is given to duke
     */
    private void stop(){
        deactivate();
        messageCreater.startLoopMessage();
        messageCreater.sayBye();
        messageCreater.endLoopMessage();
    }

    /***
     * Check if duke has been stopped
     * @return
     */
    public boolean isActive(){
        return this.active;
    }

    /***
     * Main control function for duke
     * This function does all the error handling
     * @param userInput the user input
     */
    public void receiveAndExecuteUserInput(String userInput){
        try {
            recieveUserInput(userInput);
            executeUserInput();
            clearUserInput();
        }
        catch (EmptyInputException | InvalidCommandException | NumberInputException
                | InvalidFieldException | EmptyFieldException | BadInputException e){
            messageCreater.describeException(e);
        }
    }

    /***
     * Clear the user input at the end of each execution of a userInput
     */
    private void clearUserInput(){
        inputFields = new Hashtable<>();
    }

    /***
     * takes in and handles the user input
     * @param userInput : the user input
     *  The user input is assumed to be in the format
     *  {user command} /{field 1} {input 1} /{field 2} {input 2} and so on
     */
    public void recieveUserInput(String userInput)
            throws EmptyInputException, InvalidFieldException {
        this.inputParser.parseUserInput(userInput, inputFields, fieldManager);
    }

    /***
     * Once the user input is received, this function then executes the input.
     */
    public void executeUserInput()
            throws InvalidCommandException, NumberInputException, EmptyFieldException, BadInputException {

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
            taskManager.deleteTask(inputFields.get(DukeField.INDEX));
            messageCreater.numberOfTasks(taskManager.getNumberOfTasks());
            break;
        case COMMAND_SAVE:
            taskSaver.save(taskManager.getTaskList(), inputFields.get(DukeField.NAME));
            messageCreater.save(inputFields.get(DukeField.NAME));
            break;
        case COMMAND_FIND:
            messageCreater.listTasksWithNameFilter(taskManager.getTaskList(), inputFields.get(DukeField.NAME));
            break;
        case COMMAND_LOAD:
            messageCreater.load(inputFields.get(DukeField.NAME));
            taskManager.load(taskSaver.load(inputFields.get(DukeField.NAME)));
            break;
        case COMMAND_LIST_BY_DATE:
            messageCreater.listTasks(taskManager.getFilteredTaskListByDate(inputFields.get(DukeField.TIME)));
            break;
        default:
            messageCreater.defaultMessage();
            break;
        }
        messageCreater.endLoopMessage();
    }


    // #### --------- Lower level implementations ----------- ####
    private void activate() {
        this.active = true;
    }

    private void deactivate() {
        this.active = false;
    }
}
