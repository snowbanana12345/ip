package duke;
import duke_exception.BadInputException;
import duke_exception.EmptyFieldException;
import duke_exception.NumberInputException;
import task.DeadLine;
import task.Event;
import task.Task;
import task.ToDo;

import java.util.ArrayList;
import java.util.List;

public class DukeTaskManager extends TaskManager{
    private final ArrayList<Task> taskList;
    public DukeTaskManager(){
        this.taskList = new ArrayList<>();
    }
    public void addToDo(String name)
        throws EmptyFieldException {
        if (name == null || name.equals("")){
            throw new EmptyFieldException("please input a name!");
        }
        taskList.add(new ToDo(name));
    }

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

    public void markAsDone(String taskNumber)
            throws NumberInputException, EmptyFieldException, BadInputException {
        try {
            if (taskNumber == null){
                throw new EmptyFieldException("a number is not provided!");
            }
            if (Integer.parseInt(taskNumber) < 0){
                throw new BadInputException("Please input a valid task number");
            }
            taskList.get(Integer.parseInt(taskNumber) - 1).setDone(true);
        }
        catch (NumberFormatException e){
            throw new NumberInputException("The task number provided is not a number! : " + taskNumber);
        }
        catch (IndexOutOfBoundsException e){
            throw new BadInputException("The task you are looking for does not exist!");
        }
    }

    public void deleteTask(String taskNumber){
        taskList.remove(Integer.parseInt(taskNumber));
    }

    public Integer getNumberOfTasks(){
        return taskList.size();
    }

    public List<Task> getTaskList(){
        return taskList;
    }
}
