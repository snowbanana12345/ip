package main.duke;
import main.duke_exception.BadInputException;
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

public class DukeTaskSaver extends TaskSaver{
    private static final String TODO_SYMBOL = "T";
    private static final String DEADLINE_SYMBOL = "D";
    private static final String EVENT_SYMBOL = "E";

    private static final String DONE_SYMBOL = "D";
    private static final String NOT_DONE_SYMBOL = "N";

    private static final String SEPERATOR_SYMBOL = "&&%%&&";

    private static String root_directory = System.getProperty("user.home");
    private static String duke_folder_name =  root_directory + File.separator + "duke";
    private static String default_file_path = root_directory + File.separator + "duke" + File.separator + "saves";
    private String filePath;
    public DukeTaskSaver(){
        this.filePath = default_file_path;
    }

    public void init(){
        File dir1 = new File(duke_folder_name);
        dir1.mkdir();
        File dir = new File(default_file_path);
        dir.mkdir();
    }

    public void save(List<Task> tasks, String fileName) {
        try {
            File file = new File(default_file_path + File.separator + fileName + ".txt");
            boolean success = file.createNewFile();
            FileWriter writer = new FileWriter( default_file_path + File.separator + fileName + ".txt");

            Task task;
            ToDo todo;
            Event event;
            DeadLine deadline;
            for (int i = 0; i < tasks.size(); i++){
                task = tasks.get(i);
                writer.write((i+1) + SEPERATOR_SYMBOL);
                if (task instanceof ToDo){
                    todo = (ToDo) task;
                    writer.write(TODO_SYMBOL + SEPERATOR_SYMBOL);
                    writer.write((todo.isDone() ? DONE_SYMBOL : NOT_DONE_SYMBOL) + SEPERATOR_SYMBOL);
                    writer.write(todo.getName());
                    writer.write("\n");
                }
                if (task instanceof Event){
                    event = (Event) task;
                    writer.write(EVENT_SYMBOL + SEPERATOR_SYMBOL);
                    writer.write((event.isDone() ? DONE_SYMBOL : NOT_DONE_SYMBOL) + SEPERATOR_SYMBOL);
                    writer.write(event.getName() + SEPERATOR_SYMBOL);
                    writer.write(event.getAt());
                    writer.write("\n");
                }
                if (task instanceof DeadLine) {
                    deadline = (DeadLine) task;
                    writer.write(DEADLINE_SYMBOL + SEPERATOR_SYMBOL);
                    writer.write((deadline.isDone() ? DONE_SYMBOL : NOT_DONE_SYMBOL) + SEPERATOR_SYMBOL);
                    writer.write(deadline.getName() + SEPERATOR_SYMBOL);
                    writer.write(deadline.getBy());
                    writer.write("\n");
                }
            }
            writer.close();
        }
        catch (IOException e){
            System.out.println(e);
        }
    }
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
                splitedLine = line.split(SEPERATOR_SYMBOL);
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
            return taskList;
        }
        catch (FileNotFoundException e){
            throw new BadInputException(fileName + " does not exist! Please enter a valid file name!");
        }
    }
}
