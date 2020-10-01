package main.duke;

import main.duke_exception.BadInputException;
import main.task.Task;

import java.util.List;

abstract class TaskSaver {
    public abstract void save(List<Task> taskList, String s);
    public abstract void init();
    public abstract List<Task> load(String s) throws BadInputException;
}
