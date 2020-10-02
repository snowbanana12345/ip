package main.duke;

import main.exception.BadInputException;
import main.exception.EmptyFieldException;
import main.exception.NumberInputException;
import main.task.Task;

import java.util.List;

abstract class TaskManager {
    abstract void addToDo(String name) throws EmptyFieldException;
    abstract public void addEvent(String name, String time) throws EmptyFieldException;
    abstract public void addDeadLine(String name, String due) throws EmptyFieldException;
    abstract public void markAsDone(String taskNumber) throws NumberInputException, EmptyFieldException, BadInputException;
    public abstract List<Task> getTaskList();
    public abstract Integer getNumberOfTasks();
    public abstract void deleteTask(String s);
    public abstract String getTaskDescription(String s);
    public abstract void load(List<Task> taskList);
    public abstract List<Task> getFilteredTaskListByDateTime(String s) throws BadInputException;
}
