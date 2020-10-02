package main.duke;

import main.command.DukeField;
import main.command.FieldManager;
import main.exception.EmptyInputException;
import main.exception.InvalidFieldException;

import java.util.Hashtable;

public class DukeInputParser extends InputParser{
    public DukeInputParser(){
    }

    public void parseUserInput(String userInput, Hashtable<DukeField, String> targetInputFields, FieldManager fieldManager)
            throws EmptyInputException, InvalidFieldException {
        try {
            String[] userInputs = userInput.split(" ");
            targetInputFields.put(DukeField.COMMAND, userInputs[0]);
            DukeField currentField = null;
            StringBuilder currentInput = new StringBuilder();
            for (String input : userInputs) {
                Character c = input.charAt(0);
                if (c.equals('/')) {
                    if (!(currentField == null)) {
                        targetInputFields.put(currentField, currentInput.substring(1));
                    }
                    currentField = fieldManager.getField(input.substring(1));
                    currentInput = new StringBuilder();
                } else {
                    currentInput.append(" ").append(input);
                }
            }
            if (!(currentField == null)) {
                targetInputFields.put(currentField, currentInput.substring(1));
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new EmptyInputException("The input is empty!");
        } catch (InvalidFieldException e) {
            throw e;
        }
    }
}
