package Casio;

import Casio.parser.CommandParser;
import Casio.tasks.Task;
import Casio.tasks.TaskList;
import Casio.tasks.TaskStorage;
import Casio.ui.UI;
import Casio.exceptions.CasioException;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * The main class of Casio the personal chatbot.
 * <p>
 * Records user inputs, processes commands via {@link CommandParser},
 * and manages tasks through {@link TaskList}.
 * Tasks are stored and retrieved using {@link TaskStorage}, and the user interface
 * interactions are handled by {@link UI}.
 * </p>
 */
public class Casio {

    private static final ArrayList<Task> savedTaskArray = TaskStorage.readFile();
    private static final TaskList taskList = new TaskList(savedTaskArray);

    /**
     * The entry point of the Casio chatbot.
     * <p>
     * Enters a loop to continuously read user input until the user chooses to exit.
     * @throws CasioException If command is invalid
     * </p>
     */
    public static void main(String[] args) throws CasioException {
        UI.greeting();
        Scanner scanner = new Scanner(System.in);
        int taskNumber;
        boolean hasQuit = false;

        //loop to get inputs
        while (!hasQuit) {
            taskNumber = TaskList.size();
            UI.printInputUI();
            String input = scanner.nextLine();
            hasQuit = CommandParser.parseInput(input, taskNumber, taskList);

        }
    }
}

