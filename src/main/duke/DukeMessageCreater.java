package main.duke;

import main.task.DeadLine;
import main.task.Event;
import main.task.Task;
import main.task.ToDo;

import java.util.List;

public class DukeMessageCreater extends MessageCreater{
    static int horizontalLineLength = 50;
    public DukeMessageCreater(){
    }

    public void greet(){
        printGreet();
    }

    public void sayBye(){
        printGoodbye();
    }

    public void addTodo(String name){
        printAddTodo(name);
    }

    public void addEvent(String name, String time){
        printAddEvent(name, time);
    }

    public void addDeadLine(String name, String time){
        printAddDeadLine(name, time);
    }

    public void listTasks(List<Task> taskList){
        printTaskList(taskList);
    }

    public void listTasksWithNameFilter(List<Task> taskList, String name){
        printTaskWithNameFilterMessage(name);
        printTaskListWithNameFilter(taskList, name);
    }

    public void markAsDone(String index){
        printDoneMessage(index);
    }

    public void defaultMessage(){
        printDefaultMessage();
    }

    public void describeException(Exception e){
        printException(e);
    }

    public void startLoopMessage(){
        printHorizontalLine(horizontalLineLength);
    }

    public void endLoopMessage(){
        printHorizontalLine(horizontalLineLength);
    }


    @Override
    public void deleteTask(String taskDescription) {
        printDeleteTask(taskDescription);
    }

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
}
