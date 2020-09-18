package duke;

import task.Task;

import java.util.List;

public class DukeMessageCreater extends MessageCreater{
    static int horizontalLineLength = 50;
    public DukeMessageCreater(){
    }

    public void greet(){
        printHorizontalLine(horizontalLineLength);
        printGreet();
        printHorizontalLine(horizontalLineLength);
    }

    public void sayBye(){
        printHorizontalLine(horizontalLineLength);
        printGoodbye();
        printHorizontalLine(horizontalLineLength);
    }

    public void addTodo(String name){
        printHorizontalLine(horizontalLineLength);
        printAddTodo(name);
        printHorizontalLine(horizontalLineLength);
    }

    public void addEvent(String name, String time){
        printHorizontalLine(horizontalLineLength);
        printAddEvent(name, time);
        printHorizontalLine(horizontalLineLength);
    }

    public void addDeadLine(String name, String time){
        printHorizontalLine(horizontalLineLength);
        printAddDeadLine(name, time);
        printHorizontalLine(horizontalLineLength);
    }

    public void listTasks(List<Task> taskList){
        printHorizontalLine(horizontalLineLength);
        printTaskList(taskList);
        printHorizontalLine(horizontalLineLength);
    }

    // #### --------- Lower level implementations ----------- ####
    private void printTaskList(List<Task> taskList) {
        for (int i = 0; i < taskList.size(); i++){
            System.out.println(i + ". " + taskList.get(i));
        }
    }

    private void printAddEvent(String name, String time){
        System.out.println("Added event : " + name + "(at: " + time + " )");
    }

    private void printAddDeadLine(String name, String time){
        System.out.println("Added event : " + name + "(at: " + time + " )");
    }


    private void printAddTodo(String name) {
        System.out.println("Added todo : " + name);
    }


    private void printGoodbye() {
        System.out.println("GoodBye, Hope to see you again");
    }

    private void printGreet() {
        System.out.println("Hello, i am duke!");
    }

    private void printHorizontalLine(int length){
        for (int i = 0; i < length; i++){
            System.out.print("_");
        }
        System.out.print("\n");
    }
}
