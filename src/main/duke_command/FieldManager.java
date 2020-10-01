package main.duke_command;

import main.duke_exception.InvalidFieldException;

public abstract class FieldManager {
    public abstract DukeField getField(String field) throws InvalidFieldException;
    public abstract boolean isValidCommand(String field);
    public abstract String getUserField(DukeField field);


}
