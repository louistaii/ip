package Casio;

import Casio.parser.Parser;
import Casio.ui.UI;
import Casio.exceptions.CasioException;
import Casio.tasks.Deadline;
import Casio.tasks.Event;
import Casio.tasks.Task;
import Casio.tasks.Todo;
import Casio.tasks.TaskStorage;

import java.util.Scanner;
import java.util.ArrayList;


public class Casio{

    private static final ArrayList<Task> taskArray = TaskStorage.readFile();

    public static void addTodo(String name){
        Todo t = new Todo(name);
        taskArray.add(t);
        TaskStorage.appendTaskToFile(t);

        System.out.println("Added todo: " + name);
        UI.printTaskNumber(taskArray.size());
    }

    public static void addEvent(String name, String from, String to){
        Event t = new Event(name, from, to);
        taskArray.add(t);
        TaskStorage.appendTaskToFile(t);

        System.out.println("Added event: " + name);
        UI.printTaskNumber(taskArray.size());
    }

    public static void addDeadline(String name, String by){
        Deadline t = new Deadline(name, by);
        taskArray.add(t);
        TaskStorage.appendTaskToFile(t);

        System.out.println("Added deadline: " + name);
        UI.printTaskNumber(taskArray.size());
    }

    public static void markTask(int index){
        taskArray.get(index).setDone(true);
        TaskStorage.overwriteSaveFile(taskArray);

        String taskDescription = taskArray.get(index).getDescription();
        System.out.println(taskDescription + " marked as done. Good job!!");
        UI.printTaskNumber(taskArray.size());
    }

    public static void unmarkTask(int index){
        taskArray.get(index).setDone(false);
        TaskStorage.overwriteSaveFile(taskArray);

        String taskDescription = taskArray.get(index).getDescription();
        System.out.println(taskDescription + " marked as undone.");
        UI.printTaskNumber(taskArray.size());
    }

    public static void deleteTask(int index){
        String status_icon = taskArray.get(index).getStatusIcon();
        String type_icon = taskArray.get(index).getTypeIcon();
        String output = (index+1) + ". " +
                type_icon +
                status_icon;
        System.out.println("Deleted " + output + taskArray.get(index));

        taskArray.remove(index);
        TaskStorage.overwriteSaveFile(taskArray);

        UI.printTaskNumber(taskArray.size());
    }

    public static void main(String[] args) throws CasioException {
        UI.greeting();
        Scanner scanner = new Scanner(System.in);
        int taskNumber;
        boolean hasQuit = false;

        //loop to get inputs
        while (!hasQuit) {
            taskNumber = taskArray.size();
            UI.printInputUI();
            String input = scanner.nextLine();
            hasQuit = Parser.parseInput(input, taskNumber, taskArray);
        }
    }

}
