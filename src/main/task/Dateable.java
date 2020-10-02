package main.task;
import main.exception.BadInputException;

public interface Dateable {
    boolean isOnDate(String dateTime) throws BadInputException;
}
