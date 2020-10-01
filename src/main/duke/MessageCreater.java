package main.duke;

import main.task.Task;

import java.util.List;

abstract class MessageCreater {
    abstract public void greet();
    abstract public void sayBye();
    abstract public void addTodo(String name);
    abstract public void addEvent(String name, String time);
    abstract public void addDeadLine(String name, String time);
    abstract public void listTasks(List<Task> taskList);
    abstract public void markAsDone(String index);
    abstract public void defaultMessage();
    abstract public void describeException(Exception e);
    public abstract void numberOfTasks(Integer numberOfTasks);

    public abstract void startLoopMessage();
    public abstract void endLoopMessage();

    public abstract void deleteTask(String taskDescription);
    public abstract void listTasksWithNameFilter(List<Task> taskList, String s);

    public abstract void save(String s);
}
