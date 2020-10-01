package duke;

import duke_exception.BadInputException;
import task.Task;

import java.util.List;

abstract class TaskSaver {
    public abstract void save(List<Task> taskList, String s);
    public abstract void init();
    public abstract List<Task> load(String s) throws BadInputException;
}
