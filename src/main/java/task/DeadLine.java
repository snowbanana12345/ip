package task;

public class DeadLine extends Task {
    private final String deadline;
    public DeadLine(String task, String deadline){
        super(task);
        this.deadline = deadline;
    }
    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}
