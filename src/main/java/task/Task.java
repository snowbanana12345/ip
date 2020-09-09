package task;

public class Task{
    private final String task;
    private boolean done;
    protected Task(String task){
        this.task = task;
        this.done = false;
    }

    public boolean isDone() {
        return this.done;
    }

    public void setDone(boolean isdone) {
        this.done = isdone;
    }

    public String toString(){
        return ((this.done) ? "[" + "\u2713"+"]" : "[" + "\u2717" + "]") + " " + this.task;
    }
}