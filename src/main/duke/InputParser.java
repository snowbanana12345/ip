package main.duke;

import main.command.DukeField;
import main.command.FieldManager;
import main.exception.EmptyInputException;
import main.exception.InvalidFieldException;

import java.util.Hashtable;

abstract class InputParser {
    abstract public void parseUserInput (String userInput, Hashtable<DukeField, String> targetInputFields, FieldManager fieldManager)
            throws EmptyInputException, InvalidFieldException;

}
