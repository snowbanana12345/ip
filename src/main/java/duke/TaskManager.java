package duke;

import duke_exception.BadInputException;
import duke_exception.EmptyFieldException;
import duke_exception.NumberInputException;
import task.Task;

import java.util.List;

abstract class TaskManager {
    abstract void addToDo(String name) throws EmptyFieldException;
    abstract public void addEvent(String name, String time) throws EmptyFieldException;
    abstract public void addDeadLine(String name, String due) throws EmptyFieldException;
    abstract public void markAsDone(String taskNumber) throws NumberInputException, EmptyFieldException, BadInputException;
    public abstract List<Task> getTaskList();
    public abstract Integer getNumberOfTasks();
}
