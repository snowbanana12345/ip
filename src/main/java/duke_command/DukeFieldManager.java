package duke_command;

import java.util.Hashtable;

public class DukeFieldManager extends FieldManager{
    private final Hashtable<String, DukeField> fieldTable;
    private final Hashtable<DukeField,String> reverseFieldTable;
    public DukeFieldManager(){
        this.fieldTable = new Hashtable<>();
        fieldTable.put("command", DukeField.COMMAND);
        fieldTable.put("name", DukeField.NAME);
        fieldTable.put("time", DukeField.TIME);
        fieldTable.put("index", DukeField.INDEX);
        this.reverseFieldTable = new Hashtable<>();
        reverseFieldTable.put(DukeField.COMMAND, "command");
        reverseFieldTable.put(DukeField.NAME, "name");
        reverseFieldTable.put(DukeField.TIME, "time");
        reverseFieldTable.put(DukeField.INDEX, "index");
    }

    public DukeField getField(String field){
        return fieldTable.get(field);
    }

    public boolean isValidCommand(String field){
        return fieldTable.containsKey(field);
    }

    public String getUserField(DukeField field){
        return reverseFieldTable.get(field);
    }
}
