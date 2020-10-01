package main.duke;

import main.duke_command.DukeField;
import main.duke_command.FieldManager;
import main.duke_exception.EmptyInputException;
import main.duke_exception.InvalidFieldException;

import java.util.Hashtable;

abstract class InputParser {
    abstract public void parseUserInput (String userInput, Hashtable<DukeField, String> targetInputFields, FieldManager fieldManager)
            throws EmptyInputException, InvalidFieldException;

}
