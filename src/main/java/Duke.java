import java.util.Scanner;

/**
 * main duke chat bot main function
 */
public class Duke {
    static Task[] storedUserTasks = new Task[100];
    static int numberStoredTasks = 0;
    static boolean dukeActive = false;

    static final String COMMAND_EXIT = "bye";
    static final String COMMAND_LIST_STORED_TASKS = "list";
    static final String COMMAND_SET_TASK_DONE = "done";
    static final String COMMAND_ADD_TASK = "task";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        dukeGreet();
        dukeStart();
        dukePrintInstructions();

        String userInput;
        while (dukeActive){
            userInput = in.nextLine();
            if (!(dukeCheckInput(userInput))){
                continue;
            }
            dukeBeforeActionResponse(userInput);
            switch (userInput) {
            case COMMAND_EXIT:
                dukeStop();
                break;
            case COMMAND_LIST_STORED_TASKS:
                dukeReadStoredTasks();
                break;
            case COMMAND_SET_TASK_DONE:
                dukeReadStoredTasks();
                dukeSetDone(Integer.parseInt(in.nextLine()));
                break;
            case COMMAND_ADD_TASK:
                dukeAddTask(in.nextLine());
                break;
            default:
                dukePrintInstructions();
                break;
            }

        }
        dukeGoodBye();
    }

    /* ##################################
     * ------- List of duke functions -----------
     * dukeStart
     * dukeStop
     * dukeBeforeActionResponse
     * dukeCheckInput
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

    private static void dukeBeforeActionResponse(String inputCommand){
        switch (inputCommand) {
        case COMMAND_EXIT:
            break;
        case COMMAND_LIST_STORED_TASKS:
            break;
        case COMMAND_SET_TASK_DONE:
            System.out.println("Please enter the number of the task you wish to mark as done:");
            break;
        case COMMAND_ADD_TASK:
            System.out.println("Please enter the task you wish you add:");
            break;
        default:
            break;
        }
    }

    private static boolean dukeCheckInput(String userInput){
        boolean isValid = true;
        if (userInput.length() == 0){
            isValid = false;
        }
        return isValid;
    }
    private static void dukePrintInstructions(){
        System.out.print("Here is NOT how you should ever use me\n" +
                "-------- List of commands -------\n" +
                COMMAND_EXIT + ": this will exit the programme\n" +
                COMMAND_LIST_STORED_TASKS+ ": List out the set of tasks stored\n" +
                COMMAND_SET_TASK_DONE + ": set the task with a provided task number to done\n\n" +
                COMMAND_ADD_TASK + ": Adds a new undone task to the list\n" +
                "GOT IT????\n"
        );
    }

    private static void dukeAddTask(String taskName) {
        storedUserTasks[numberStoredTasks] = new Task(taskName);
        numberStoredTasks++;
        System.out.println("Added task: " + taskName);
    }

    /**
     * prints a horizontal line with the specified length
     *
     * @param length the length of horizontal line
     */


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

    /**
     * ask duke to set a certain task to be done. duke will complain if you don't provide a valid task number!
     * @param taskNumber the task number of the task in order the task was given to duke e.g. 10th task is 10
     */
    public static void dukeSetDone(int taskNumber){
        if (taskNumber > numberStoredTasks || taskNumber <= 0){
            System.out.println("Are you stupid? There is no such Task");
            return;
        }
        storedUserTasks[taskNumber-1].done();
        System.out.println("Does the completion of such mundane tasks elicit joy in you?");
        System.out.println("Or does the emptiness that ensues it's completion fill you with regret?");
        System.out.println(storedUserTasks[taskNumber-1]);
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
}
