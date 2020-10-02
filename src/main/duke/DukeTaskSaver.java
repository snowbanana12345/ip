package main.duke;
import main.exception.BadInputException;
import main.task.DeadLine;
import main.task.Event;
import main.task.Task;
import main.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/***
 * This class handles saving and loading to and from a text file
 * The text file save format used, with | indicating a separator whose symbol can be adjusted
 * task index | task type | done status | class name | time
 */
public class DukeTaskSaver extends TaskSaver{
    private static final String TODO_SYMBOL = "T";
    private static final String DEADLINE_SYMBOL = "D";
    private static final String EVENT_SYMBOL = "E";

    private static final String DONE_SYMBOL = "D";
    private static final String NOT_DONE_SYMBOL = "N";

    private static final String SEPARATOR_SYMBOL = "&&%%&&";

    private static final String root_directory = System.getProperty("user.home");
    private static final String duke_folder_name =  root_directory + File.separator + "duke";
    private static final String default_file_path = root_directory + File.separator + "duke" + File.separator + "saves";

    public DukeTaskSaver(){
    }

    /***
     * if the user is running duke for the first time, this function creates the necessary folders
     */
    public void init(){
        File dir1 = new File(duke_folder_name);
        dir1.mkdir();
        File dir = new File(default_file_path);
        dir.mkdir();
    }

    /***
     * Creates the save file
     * @param tasks the task list
     * @param fileName the name of the save file specified by the user
     */
    public void save(List<Task> tasks, String fileName) {
        try {
            File file = new File(default_file_path + File.separator + fileName + ".txt");
            boolean success = file.createNewFile();
            FileWriter writer = new FileWriter( default_file_path + File.separator + fileName + ".txt");

            Task task;
            for (int i = 0; i < tasks.size(); i++){
                task = tasks.get(i);
                writer.write((i+1) + SEPARATOR_SYMBOL);
                if (task instanceof ToDo){
                    writeTodoToFile(writer, (ToDo) task);
                }
                if (task instanceof Event){
                    writeEventToFile(writer, (Event) task);
                }
                if (task instanceof DeadLine) {
                    writeDeadLineToFile(writer, (DeadLine) task);
                }
            }
            writer.close();
        }
        catch (IOException e){
            System.out.println(e);
        }
    }

    private void writeDeadLineToFile(FileWriter writer, DeadLine task) throws IOException {
        DeadLine deadline;
        deadline = task;
        writer.write(DEADLINE_SYMBOL + SEPARATOR_SYMBOL);
        writer.write((deadline.isDone() ? DONE_SYMBOL : NOT_DONE_SYMBOL) + SEPARATOR_SYMBOL);
        writer.write(deadline.getName() + SEPARATOR_SYMBOL);
        writer.write(deadline.getBy());
        writer.write("\n");
    }

    private void writeEventToFile(FileWriter writer, Event task) throws IOException {
        Event event;
        event = task;
        writer.write(EVENT_SYMBOL + SEPARATOR_SYMBOL);
        writer.write((event.isDone() ? DONE_SYMBOL : NOT_DONE_SYMBOL) + SEPARATOR_SYMBOL);
        writer.write(event.getName() + SEPARATOR_SYMBOL);
        writer.write(event.getAt());
        writer.write("\n");
    }

    private void writeTodoToFile(FileWriter writer, ToDo task) throws IOException {
        ToDo todo;
        todo = task;
        writer.write(TODO_SYMBOL + SEPARATOR_SYMBOL);
        writer.write((todo.isDone() ? DONE_SYMBOL : NOT_DONE_SYMBOL) + SEPARATOR_SYMBOL);
        writer.write(todo.getName());
        writer.write("\n");
    }

    /***
     * reads a save file and converts it to a list of tasks
     * @param fileName the file name of the file to read from
     * @return the list of tasks
     * @throws BadInputException name of file must be an existing saved file
     */
    public List<Task> load(String fileName) throws BadInputException{
        try {
            File file = new File(default_file_path + File.separator + fileName + ".txt");
            Scanner reader = new Scanner(file);
            List<Task> taskList = new ArrayList<>();
            String line;
            String[] splitedLine;
            ToDo todo;
            Event event;
            DeadLine deadline;
            while (reader.hasNext()){
                line = reader.nextLine();
                splitedLine = line.split(SEPARATOR_SYMBOL);
                if (splitedLine[1].equals(TODO_SYMBOL)){
                    todo = new ToDo(splitedLine[3]);
                    if (splitedLine[2].equals(DONE_SYMBOL)){
                        todo.setDone(true);
                    }
                    taskList.add(todo);
                }
                if (splitedLine[1].equals(EVENT_SYMBOL)){
                    event = new Event(splitedLine[3],splitedLine[4]);
                    if (splitedLine[2].equals(DONE_SYMBOL)){
                        event.setDone(true);
                    }
                    taskList.add(event);
                }
                if (splitedLine[1].equals(DEADLINE_SYMBOL)){
                    deadline = new DeadLine(splitedLine[3],splitedLine[4]);
                    if (splitedLine[2].equals(DONE_SYMBOL)){
                        deadline.setDone(true);
                    }
                    taskList.add(deadline);
                }
            }
            System.out.println("File :" + fileName + " is loaded successfully!");
            return taskList;
        }
        catch (FileNotFoundException e){
            throw new BadInputException(fileName + " does not exist! Please enter a valid file name!");
        }
    }
}
