package task;

import duke_exception.BadInputException;

public class Event extends Task implements Dateable{
    private final String at;
    private boolean hasValidDateTime;
    private DateTime dateTime;
    public Event(String task, String at){
        super(task);
        this.at = at;
        this.hasValidDateTime = false;
        this.dateTime = new DateTime(0,0,0,0);

        try {
            this.dateTime = DateTimeParser.parseDateTime(at);
            this.hasValidDateTime = true;
        }
        catch (BadInputException e){
            System.out.println(e);
        }
    }

    public String getAt(){
        return this.at;
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
            return "[E]" + super.toString() + " (at: " + this.dateTime + ")";
        }
        else {
            return "[E]" + super.toString() + " (at: " + this.at + ")";
        }
    }
}
