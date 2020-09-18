package duke;

import task.Task;

import java.util.List;

abstract class TaskManager {
    abstract void addToDo(String name);
    abstract public void addEvent(String name, String time);
    abstract public void addDeadLine(String name, String due);
    abstract public void markAsDone(Integer taskNumber);
    abstract public void markAsDone(Integer taskNumber, boolean done);
    public abstract List<Task> getTaskList();
}
