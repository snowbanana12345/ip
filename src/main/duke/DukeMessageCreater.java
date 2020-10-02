package main.duke;

import main.task.DeadLine;
import main.task.Event;
import main.task.Task;
import main.task.ToDo;

import java.util.List;

/***
 * This class handles the duke output to be seen by the user.
 * It can take in data from other parts of the programme via DukeManager to produce an output.
 */
public class DukeMessageCreater extends MessageCreater{
    static int horizontalLineLength = 50;
    public DukeMessageCreater(){
    }

    /***
     * Greets the user.
     */
    public void greet(){
        printGreet();
    }

    /***
     * says good bye to the user
     */
    public void sayBye(){
        printGoodbye();
    }

    /***
     * Tells the user that a todo task has been successfully added
     * @param name name of the todo task
     */
    public void addTodo(String name){
        printAddTodo(name);
    }

    /***
     * Tells the user that a event has been successfully added
     * @param name name of event
     * @param time time of event
     */
    public void addEvent(String name, String time){
        printAddEvent(name, time);
    }

    /***
     * Tells the user that a deadline has been successfully added
     * @param name name of event
     * @param time time of event
     */
    public void addDeadLine(String name, String time){
        printAddDeadLine(name, time);
    }

    /***
     * List out all of the tasks currently stored in duke
     * @param taskList
     */
    public void listTasks(List<Task> taskList){
        printTaskList(taskList);
    }

    /***
     * List out all tasks that only contains a given string
     * @param taskList the task list stored in duke
     * @param name the string provided by user
     */
    public void listTasksWithNameFilter(List<Task> taskList, String name){
        printTaskWithNameFilterMessage(name);
        printTaskListWithNameFilter(taskList, name);
    }

    /***
     * Tells the user that a task has been successfully marked as done
     * @param index the number of that task
     */
    public void markAsDone(String index){
        printDoneMessage(index);
    }

    /***
     * if no valid command is given, duke will output this default message
     */
    public void defaultMessage(){
        printDefaultMessage();
    }

    /**
     * For exceptions that is caused by bad user input, this function tells the user what is wrong
     * with the user input
     * @param e the exception that is thrown by DukeManager
     */
    public void describeException(Exception e){
        printException(e);
    }

    /***
     * output to indicate the start of a cycle which will then prompt the user to give a input
     */
    public void startLoopMessage(){
        printHorizontalLine(horizontalLineLength);
    }

    /***
     * output to indicate the end of an execution cycle
     */
    public void endLoopMessage(){
        printHorizontalLine(horizontalLineLength);
    }

    /***
     * Tells the user that the task list has been successfully saved
     * @param fileName the name of the save file
     */
    public void save(String fileName){
        printSave(fileName);
    }

    /***
     * Tells the user that a saved file has been successfully loaded
     * @param fileName the name of the save file
     */
    public void load(String fileName){
        printLoad(fileName);
    }

    
    public void deleteTask(String taskDescription) {
        printDeleteTask(taskDescription);
    }


    // -------- Lower level implementations -------------
    private void printDeleteTask(String taskDescription){
        System.out.println("Noted! i've removed this task:");
        System.out.println("\t" + taskDescription);
    }

    public void numberOfTasks(Integer numberOfTasks){
        printNumberOfTasks(numberOfTasks);
    }
    // #### --------- Lower level implementations ----------- ####

    private void printTaskList(List<Task> taskList) {
        for (int i = 0; i < taskList.size(); i++){
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
    }

    private void printTaskListWithNameFilter(List<Task> taskList, String name) {
        Task task;
        for (int i = 0; i < taskList.size(); i++){
            task = taskList.get(i);
            if (task.getName().contains(name)) {
                System.out.println((i + 1) + ". " + task);
            }
        }
    }

    private void printException(Exception e) {
        System.out.println("Ooops!!! Looks like we have a problem: " + e.getMessage());
    }

    private void printAddEvent(String name, String time){
        System.out.println("Added a event to the task list!");
        System.out.println("\t" + (new Event(name,time).toString()));
    }

    private void printAddDeadLine(String name, String time){
        System.out.println("\tAdded a deadline to the task list!");
        System.out.println("\t" + (new DeadLine(name,time).toString()));
    }


    private void printAddTodo(String name) {
        System.out.println("Added a todo to the task list!");
        System.out.println("\t" + (new ToDo(name).toString()));
    }

    private void printGoodbye() {
        System.out.println("GoodBye, Hope to see you again");
    }

    private void printGreet() {
        System.out.println("Hello, i am duke!");
    }

    private void printDoneMessage(String index) {
        System.out.println("Task " + index + " is now marked as done!");
    }

    private void printDefaultMessage() {
        System.out.println("Please give me a instruction! :)");
    }

    private void printHorizontalLine(int length){
        for (int i = 0; i < length; i++){
            System.out.print("_");
        }
        System.out.print("\n");
    }

    private void printNumberOfTasks(Integer numberOfTasks){
        System.out.println("\tNow you have " + numberOfTasks + " tasks on your list!");
    }

    private void printTaskWithNameFilterMessage(String name) {
        System.out.println("Printing only tasks that contains: " + name);
    }

    private void printSave(String fileName) {
        System.out.println("Current task list is saved as :" + fileName + "!");
    }

    private void printLoad(String fileName) {
        System.out.println("Loading task list from file :" + fileName);
    }
}
