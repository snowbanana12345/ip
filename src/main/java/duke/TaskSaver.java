package duke;

import task.Task;

import java.util.List;

abstract class TaskSaver {
    public abstract void save(List<Task> taskList, String s);
}
