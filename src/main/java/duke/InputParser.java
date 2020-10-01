package duke;

import duke_command.DukeField;
import duke_command.FieldManager;
import duke_exception.EmptyInputException;
import duke_exception.InvalidFieldException;

import java.util.Hashtable;

abstract class InputParser {
    abstract public void parseUserInput (String userInput, Hashtable<DukeField, String> targetInputFields, FieldManager fieldManager)
            throws EmptyInputException, InvalidFieldException;

}
