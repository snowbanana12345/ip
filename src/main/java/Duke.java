import duke_command.Command;
import duke_command.dukeCommandManager;
import duke_exception.BadInputException;
import duke_exception.EmptyInputException;
import duke_exception.InvalidCommandException;
import task.DeadLine;
import task.Event;
import task.Task;
import task.ToDo;

import java.util.Scanner;

/**
 * main duke chat bot main function
 */
public class Duke {
    static Task[] storedUserTasks = new Task[100];
    static int numberStoredTasks = 0;
    static boolean dukeActive = false;
    static String[] currentUserInput;
    static Command currentUserCommand;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        dukeGreet();
        dukeStart();
        dukePrintInstructions();

        while (dukeActive){
            try {
                dukeCollectUserInput(in);
                switch (dukeProvideUserCommand()) {
                case COMMAND_EXIT:
                    dukeStop();
                    break;
                case COMMAND_LIST_STORED_TASKS:
                    dukeReadStoredTasks();
                    break;
                case COMMAND_SET_TASK_DONE:
                    dukeSetDone(dukeProvideUserInput());
                    break;
                case COMMAND_ADD_TODO:
                    dukeAddToDo(dukeProvideUserInput());
                    break;
                case COMMAND_ADD_DEADLINE:
                    dukeAddDeadLine(dukeProvideUserInput());
                    break;
                case COMMAND_ADD_EVENT:
                    dukeAddEvent(dukeProvideUserInput());
                    break;
                case COMMAND_INSTRUCTIONS:
                    dukePrintInstructions();
                    break;
                default:
                    dukeDefaultResponse();
                    break;
                }
            }
            catch (InvalidCommandException | EmptyInputException | BadInputException e){
                System.out.println(e);
                dukeDefaultResponse();
            }
        }
        dukeGoodBye();
    }



    /* ##################################
     * ------- List of duke functions -----------
     * dukeStart
     * dukeStop
     * dukeBeforeActionResponse
     * dukePrintInstructions
     * dukeAddTask
     * dukeGreet
     * dukeGoodBye
     * dukeEcho
     * dukeReadStoredTasks
     * dukeSetDone
     ###################################
     */

    private static void dukeStart(){
        dukeActive = true;
    }
    private static void dukeStop(){
        dukeActive = false;
    }

    private static void dukeDefaultResponse() {
        System.out.println("To see the list of commands, type: " + dukeCommandManager.getUserCommand(Command.COMMAND_INSTRUCTIONS));
    }

    private static void dukeCollectUserInput(Scanner in)
            throws InvalidCommandException{
        String userInput = in.nextLine();
        currentUserInput = userInput.split(" ");
        if (currentUserInput.length == 0){
            throw new InvalidCommandException("Please provide me with some command and not just silence");
        }
        if (!(dukeCommandManager.isValidCommand(currentUserInput[0]))){
            throw new InvalidCommandException("i don't understand what you're saying");
        }
        currentUserCommand = dukeCommandManager.getCommand((currentUserInput[0]));
    }
    private static String[] dukeProvideUserInput(){
        return currentUserInput;
    }
    private static Command dukeProvideUserCommand(){
        return currentUserCommand;
    }

    private static void dukePrintInstructions(){
        System.out.print("Here is NOT how you should ever use me\n" +
                "-------- List of commands -------\n" +
                dukeCommandManager.getUserCommand(Command.COMMAND_EXIT) + ": this will exit the programme\n" +
                dukeCommandManager.getUserCommand(Command.COMMAND_LIST_STORED_TASKS)+ ": List out the set of tasks stored\n" +
                dukeCommandManager.getUserCommand(Command.COMMAND_SET_TASK_DONE) + ": set the task with a provided task number to done\n" +
                dukeCommandManager.getUserCommand(Command.COMMAND_ADD_TODO) + " {Name of to do} : Adds a new undone todo to the list\n" +
                dukeCommandManager.getUserCommand(Command.COMMAND_ADD_DEADLINE) + " {Name of dead line} /by {date or time of deadline} : Adds a new undone deadline to the list\n" +
                dukeCommandManager.getUserCommand(Command.COMMAND_ADD_EVENT) + " {Name of event} /at {date or time of event} : Adds a new undone event to the list\n" +
                "GOT IT????\n"
        );
    }

    private static void dukeAddToDo(String[] userInputs)
            throws EmptyInputException{
        ToDo new_task = createToDo(userInputs);
        addTask(new_task);
        printAddTaskMessage(new_task);
    }

    private static void dukeAddDeadLine(String[] userInputs)
            throws EmptyInputException, BadInputException{
        DeadLine new_task = createDeadLine(userInputs);
        addTask(new_task);
        printAddTaskMessage(new_task);
    }

    private static void dukeAddEvent(String[] userInputs)
            throws EmptyInputException, BadInputException{
        Event new_task = createEvent(userInputs);
        addTask(new_task);
        printAddTaskMessage(new_task);
    }

    /**
     * prints a greeting to the user
     */
    public static void dukeGreet(){
        printHorizontalLine(50);
        System.out.println("Hello! I'm Duke, not that anybody cares.");
        System.out.println("Make it quick, im busy!");
        System.out.println();
    }

    /**
     * prints a goodbye message to the user
     */
    public static void dukeGoodBye(){
        printHorizontalLine(50);
        System.out.println("Bye. Hope to see you again never!");
        System.out.println("Now get out of my sight!");
        System.out.println();
        printHorizontalLine(50);
    }

    /**
     * repeats whatever the user said
     * @param userInput the user message
     */
    public static void dukeEcho(String userInput){
        printHorizontalLine(50);
        System.out.println("added: " + userInput);
        printHorizontalLine(50);
    }

    /**
     * Ask duke to read out the user's list of tasks it stored
     */
    public static void dukeReadStoredTasks(){
        printHorizontalLine(50);
        for (int i = 0; i < numberStoredTasks; i++){
            System.out.println((i+1) + ". " + storedUserTasks[i]);
        }
        printHorizontalLine(50);
    }

    public static void dukeSetDone(String[] userInputs)
            throws BadInputException{
        try {
            int taskNumber = Integer.parseInt(userInputs[1]);
            setUserTaskToDone(storedUserTasks[taskNumber - 1]);
            printSetDoneMessage(storedUserTasks[taskNumber - 1]);
        }
        catch (NullPointerException e){
            throw new BadInputException("You tried to access a task that does not exist!");
        }
        catch (ArrayIndexOutOfBoundsException e){
            throw new BadInputException("You either did not provide a number or an astronomical number");
        }
        catch (NumberFormatException e){
            throw new BadInputException("The task must be specificed by it's number!");
        }
    }


    /*
    ----------- Second level of abstraction ------------------
     */
    public static void printHorizontalLine(int length){
        for (int i = 0; i < length; i++){
            System.out.print("_");
        }
        System.out.print("\n");
    }


    private static void printAddTaskMessage(Task new_task) {
        printHorizontalLine(50);
        System.out.println("Got it. i've added this task\n  " + new_task);
        System.out.println("Now you have " + numberStoredTasks + " tasks in the list.");
        printHorizontalLine(50);
    }

    private static void printSetDoneMessage(Task storedUserTask) {
        printHorizontalLine(50);
        System.out.println("Does the completion of such mundane tasks elicit joy in you?");
        System.out.println("Or does the emptiness that ensues it's completion fill you with regret?");
        System.out.println(storedUserTask);
        printHorizontalLine(50);
    }


    private static void addTask(Task new_task) {
        storedUserTasks[numberStoredTasks] = new_task;
        numberStoredTasks++;
    }

    private static Event createEvent(String[] userInputs)
            throws EmptyInputException, BadInputException{
        if (userInputs.length <= 1){
            throw new EmptyInputException("The description of a Event cannot be empty!");
        }
        StringBuilder taskName = new StringBuilder();
        StringBuilder at = new StringBuilder();
        boolean isBuildingName = true;
        for (int i = 1; i < userInputs.length; i++){
            if (userInputs[i].equals("/at")){
                isBuildingName = false;
                continue;
            }
            if (isBuildingName) {
                taskName.append(userInputs[i]).append(" ");
            }
            else{
                at.append(userInputs[i]).append(" ");
            }
        }
        if (at.length() == 0){
            throw new BadInputException("Event time cannot be empty!");
        }
        return new Event(taskName.toString(), at.toString());
    }

    private static DeadLine createDeadLine(String[] userInputs)
            throws EmptyInputException, BadInputException{
        if (userInputs.length <= 1){
            throw new EmptyInputException("The description of a DeadLine cannot be empty!");
        }
        StringBuilder taskName = new StringBuilder();
        StringBuilder by = new StringBuilder();
        boolean isBuildingName = true;
        for (int i = 1; i < userInputs.length; i++){
            if (userInputs[i].equals("/by")){
                isBuildingName = false;
                continue;
            }
            if (isBuildingName) {
                taskName.append(userInputs[i]).append(" ");
            }
            else{
                by.append(userInputs[i]).append(" ");
            }
        }
        if (by.length() == 0){
            throw new BadInputException("DeadLine due date cannot be empty!");
        }
        return new DeadLine(taskName.toString(), by.toString());
    }

    private static ToDo createToDo(String[] userInputs)
            throws EmptyInputException{
        if (userInputs.length <= 1){
            throw new EmptyInputException("The description of a ToDo cannot be empty!");
        }
        StringBuilder taskName = new StringBuilder();
        for (int i = 1; i < userInputs.length; i++){
            taskName.append(userInputs[i]).append(" ");
        }
        return new ToDo(taskName.toString());
    }

    private static void setUserTaskToDone(Task storedUserTask) {
        storedUserTask.setDone(true);
    }

    private static boolean isValidTaskNumber(int taskNumber) {
        boolean isValid = !(taskNumber > numberStoredTasks || taskNumber <= 0);
        if (!(isValid)) {
            System.out.println("Are you stupid? There is no such Task");
        }
        return isValid;
    }
}
