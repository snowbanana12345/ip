package main.task;


/***
 * Task class,
 * keeps track of it's description and done status
 */
abstract public class Task{
    private final String taskName;
    private boolean done;

    protected Task(String task){
        this.taskName = task;
        this.done = false;
    }

    public boolean isDone() {
        return this.done;
    }

    public void setDone(boolean isdone) {
        this.done = isdone;
    }

    public String getName(){
        return this.taskName;
    }

    public String toString(){
        return ((this.done) ? "[" + "\u2713"+"]" : "[" + "\u2717" + "]") + " " + this.taskName;
    }
}