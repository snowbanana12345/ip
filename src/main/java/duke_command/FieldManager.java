package duke_command;

public abstract class FieldManager {
    public abstract DukeField getField(String field);
    public abstract boolean isValidCommand(String field);
    public abstract String getUserField(DukeField field);


}
