package main.duke;

import main.command.DukeField;
import main.command.FieldManager;
import main.exception.EmptyInputException;
import main.exception.InvalidFieldException;

import java.util.Hashtable;

public class DukeInputParser extends InputParser{
    public DukeInputParser(){
    }

    /***
     * This function takes in the string user input and seperate the different input strings by their field
     * and put them into the Hashtable which is then used by the DukeManager to execute the commands
     * The format of the user input is assumed to be {command} /field1 {string input for field 1}
     * /field 2 {string input for field 2} etc.
     *
     * @param userInput a string line of user input
     * @param targetInputFields an empty hashtable to be filled up
     * @param fieldManager converts the string tag for the field into the Enum field
     * @throws EmptyInputException the string is empty
     * @throws InvalidFieldException a string tag for the field is invalid
     */
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
