package task;
import duke_exception.BadInputException;

public interface Dateable {
    boolean isOnDate(String dateTime) throws BadInputException;
}
