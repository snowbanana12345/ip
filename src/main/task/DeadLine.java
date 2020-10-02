package main.task;

import main.exception.BadInputException;

public class DeadLine extends Task implements Dateable{
    private final String deadline;
    private boolean hasValidDateTime;
    private DateTime dateTime;
    public DeadLine(String task, String deadline){
        super(task);
        this.deadline = deadline;
        this.hasValidDateTime = false;
        this.dateTime = new DateTime(0,0,0,0);

        try {
            this.dateTime = DateTimeParser.parseDateTime(deadline);
            this.hasValidDateTime = true;
        }
        catch (BadInputException e){
            System.out.println(e);
        }
    }

    public String getBy(){
        return this.deadline;
    }

    public boolean isOnDate(String dateTime) throws BadInputException {
        if (!(hasValidDateTime)){
            return false;
        }
        try {
            DateTime date = DateTimeParser.parseDateTime(dateTime);
            return DateTime.isSameDate(this.dateTime, date);
        }
        catch (BadInputException e){
            throw new BadInputException("The date time entered has a problem:" + e.getMessage());
        }
    }

    @Override
    public String toString(){
        if (hasValidDateTime){
            return "[E]" + super.toString() + "(at: " + this.dateTime + ")";
        }
        else {
            return "[E]" + super.toString() + "(at: " + this.deadline + ")";
        }
    }
}
