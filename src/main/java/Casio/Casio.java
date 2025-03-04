package Casio;

import Casio.parser.Parser;
import Casio.tasks.Task;
import Casio.tasks.TaskList;
import Casio.tasks.TaskStorage;
import Casio.ui.UI;
import Casio.exceptions.CasioException;

import java.util.Scanner;
import java.util.ArrayList;


public class Casio{

    private static final ArrayList<Task> savedTaskArray = TaskStorage.readFile();
    private static final TaskList taskList = new TaskList(savedTaskArray);

    public static void main(String[] args) throws CasioException {
        UI.greeting();
        Scanner scanner = new Scanner(System.in);
        int taskNumber;
        boolean hasQuit = false;

        //loop to get inputs
        while (!hasQuit) {
            taskNumber = taskList.size();
            UI.printInputUI();
            String input = scanner.nextLine();
            hasQuit = Parser.parseInput(input, taskNumber, taskList);
        }
    }

}
