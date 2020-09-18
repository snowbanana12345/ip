package duke;

import task.Task;

import java.util.List;

abstract class MessageCreater {
    abstract public void greet();
    abstract public void sayBye();
    abstract public void addTodo(String name);
    abstract public void addEvent(String name, String time);
    abstract public void addDeadLine(String name, String time);
    abstract public void listTasks(List<Task> taskList);
}
