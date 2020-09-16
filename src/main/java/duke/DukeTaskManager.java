package duke;
import task.DeadLine;
import task.Event;
import task.Task;
import task.ToDo;

import java.util.ArrayList;

public class DukeTaskManager {
    private final ArrayList<Task> taskList;
    public DukeTaskManager(){
        this.taskList = new ArrayList<>();
    }
    public void addToDo(String name){
        taskList.add(new ToDo(name));
    }

    public void addEvent(String name, String time){
        taskList.add(new Event(name, time));
    }

    public void addDeadLine(String name, String due){
        taskList.add(new DeadLine(name, due));
    }

    public void markAsDone(Integer taskNumber){
        taskList.get(taskNumber).setDone(true);
    }

    public void markAsDone(Integer taskNumber, boolean done){
        taskList.get(taskNumber).setDone(done);
    }

    public ArrayList<Task> getTaskList(){
        return taskList;
    }
}
