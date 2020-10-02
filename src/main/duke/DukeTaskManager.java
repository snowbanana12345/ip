package main.duke;
import main.exception.BadInputException;
import main.exception.EmptyFieldException;
import main.exception.NumberInputException;
import main.task.Task;
import main.task.*;

import java.util.ArrayList;
import java.util.List;

public class DukeTaskManager extends TaskManager{
    private static final Integer INDEX_OFF_SET = 1;
    private final ArrayList<Task> taskList;
    public DukeTaskManager(){
        this.taskList = new ArrayList<>();
    }

    /***
     * Adds a todo to the task list
     * @param name name of the todo
     * @throws EmptyFieldException the name is empty
     */
    public void addToDo(String name)
        throws EmptyFieldException {
        if (name == null || name.equals("")){
            throw new EmptyFieldException("please input a name!");
        }
        taskList.add(new ToDo(name));
    }

    /***
     * Adds a event to the task list
     * @param name name of the event
     * @param time time of the event
     * @throws EmptyFieldException name or time is empty
     */
    public void addEvent(String name, String time)
            throws EmptyFieldException {
        if (name == null || name.equals("")){
            throw new EmptyFieldException("please input a name!");
        }
        if (time == null || time.equals("")){
            throw new EmptyFieldException("please input a time!");
        }
        taskList.add(new Event(name, time));
    }

    /***
     * Adds a deadline to the task list
     * @param name name of deadline
     * @param due due date of deadline
     * @throws EmptyFieldException name or due is empty
     */
    public void addDeadLine(String name, String due)
            throws EmptyFieldException {
        if (name == null || name.equals("")){
            throw new EmptyFieldException("please input a name!");
        }
        if (due == null || due.equals("")){
            throw new EmptyFieldException("please input a time!");
        }
        taskList.add(new DeadLine(name, due));
    }

    /***
     * Set a task with a task number specified by the user to done
     * @param taskNumber the index of the task
     * @throws NumberInputException the user did not specify a number for the index
     * @throws EmptyFieldException the index cannot be empty
     * @throws BadInputException the user did not provide a valid index, e.g. it is more than the number of tasks
     * or a negative number
     */
    public void markAsDone(String taskNumber)
            throws NumberInputException, EmptyFieldException, BadInputException {
        try {
            if (taskNumber == null){
                throw new EmptyFieldException("a number is not provided!");
            }
            if (Integer.parseInt(taskNumber) < 0){
                throw new BadInputException("Please input a valid task number");
            }
            taskList.get(Integer.parseInt(taskNumber) - INDEX_OFF_SET).setDone(true);
        }
        catch (NumberFormatException e){
            throw new NumberInputException("The task number provided is not a number! : " + taskNumber);
        }
        catch (IndexOutOfBoundsException e){
            throw new BadInputException("The task you are looking for does not exist!");
        }
    }

    /***
     * deletes a task with a task number specified by the user
     * @param taskNumber the task index
     */
    public void deleteTask(String taskNumber){
        taskList.remove(Integer.parseInt(taskNumber) - INDEX_OFF_SET);
    }

    /***
     * other parts of the programme may want to know just the number of tasks stored
     * @return the number of tasks stored
     */
    public Integer getNumberOfTasks(){
        return taskList.size();
    }

    /***
     * other parts of the programme may want to get the description of a particular task
     * @param taskNumber the index of the task
     * @return the string representation of that task
     */
    public String getTaskDescription(String taskNumber){
        return taskList.get(Integer.parseInt(taskNumber) - INDEX_OFF_SET).toString();
    }

    /***
     * other parts of the programme may want all of the tasks stored
     * @return the whole list of tasks
     */
    public List<Task> getTaskList(){
        return taskList;
    }


    /***
     * recieves a list of tasks loaded from a save file and resets the task list to that new loaded set of tasks
     * @param newTaskList newly loaded task list
     */
    public void load (List<Task> newTaskList) {
        this.taskList.clear();
        this.taskList.addAll(newTaskList);
    }

    /***
     * Filters the task by it's date
     * @param date date given by user
     * @return a list of tasks with the specified date time
     * @throws BadInputException the user did not provide a valid date
     */
    public List<Task> getFilteredTaskListByDate(String date) throws BadInputException {
        List<Task> newTaskList = new ArrayList<>();
        Dateable dateable;
        for (Task task : taskList){
            if (task instanceof Dateable){
                dateable = (Dateable) task;
                if (dateable.isOnDate(date)){
                    newTaskList.add(task);
                }
            }
        }
        return newTaskList;
    }
}
