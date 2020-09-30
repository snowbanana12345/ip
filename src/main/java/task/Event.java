package task;

public class Event extends Task {
    private final String at;
    public Event(String task, String at){
        super(task);
        this.at = at;
    }

    public String getAt(){
        return this.at;
    }
    @Override
    public String toString(){
        return "[E]" + super.toString() + "(at: " + this.at + ")";
    }
}
