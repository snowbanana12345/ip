package main.command;

import main.exception.InvalidFieldException;

import java.util.Hashtable;

public class DukeFieldManager extends FieldManager{
    private static final String FIELD_COMMAND = "command";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_TIME = "time";
    private static final String FIELD_INDEX = "number";

    private static final String ERROR_NO_SUCH_FIELD = "There is no such input field!";

    private final Hashtable<String, DukeField> fieldTable;

    public DukeFieldManager(){
        this.fieldTable = new Hashtable<>();
        fieldTable.put(FIELD_COMMAND, DukeField.COMMAND);
        fieldTable.put(FIELD_NAME, DukeField.NAME);
        fieldTable.put(FIELD_TIME, DukeField.TIME);
        fieldTable.put(FIELD_INDEX, DukeField.INDEX);
    }

    public DukeField getField(String field)
        throws InvalidFieldException {
        DukeField dukefield= fieldTable.get(field);
        if (dukefield == null){
            throw new InvalidFieldException(ERROR_NO_SUCH_FIELD);
        }
        return fieldTable.get(field);
    }
}
