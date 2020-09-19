import duke.DukeManager;
import duke.DukeMessageCreater;
import duke.DukeTaskManager;
import duke_command.DukeCommandManager;
import duke_command.DukeFieldManager;

import java.util.Scanner;

/**
 * main duke chat bot main function
 */
public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        DukeManager duke = new DukeManager(new DukeCommandManager(), new DukeFieldManager()
                , new DukeTaskManager(), new DukeMessageCreater());
        String userInput;
        duke.start();
        while (duke.isActive()){
            userInput = in.nextLine();
            duke.receiveAndExecuteUserInput(userInput);
        }
    }
}