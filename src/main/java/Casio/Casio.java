package Casio;

import Casio.parser.Parser;
import Casio.ui.UI;
import Casio.exceptions.CasioException;
import Casio.tasks.Deadline;
import Casio.tasks.Event;
import Casio.tasks.Task;
import Casio.tasks.Todo;

import java.util.Scanner;
import java.util.ArrayList;


public class Casio{


    private static int taskNumber = 0;
    private static ArrayList<Task> taskArray = new ArrayList<> ();


    public static void addTodo(String name){
        Todo t = new Todo(name);
        taskArray.add(t);
        System.out.println("Added todo: "+ name);
    }

    public static void addEvent(String name, String from, String to){
        Event t = new Event(name, from, to);
        taskArray.add(t);
        System.out.println("Added event: "+ name);
    }

    public static void addDeadline(String name, String by){
        Deadline t = new Deadline(name, by);
        taskArray.add(t);
        System.out.println("Added deadline: "+ name);
    }


    public static void markTask(int index){
        taskArray.get(index).setDone(true);
        String taskDescription = taskArray.get(index).getDescription();
        System.out.println(taskDescription + " marked as done.");
    }

    public static void unmarkTask(int index){
        taskArray.get(index).setDone(false);
        String taskDescription = taskArray.get(index).getDescription();
        System.out.println(taskDescription + " marked as undone.");
    }

    public static void deleteTask(int index){
        String status_icon = taskArray.get(index).getStatusIcon();
        String type_icon = taskArray.get(index).getTypeIcon();
        String output = (index+1) + ". " +
                type_icon +
                status_icon;
        System.out.println("Deleted " + output + taskArray.get(index));
        taskArray.remove(index);
        taskNumber = taskArray.size();
        System.out.println("You now have " + taskNumber + " task(s).");
    }

    public static void main(String[] args) throws CasioException {
        UI.greeting();
        Scanner scanner = new Scanner(System.in);

        //loop to get inputs
        while (true) {
            String input = scanner.nextLine();
            taskNumber = Parser.parseInput(input, taskNumber, taskArray);
        }
    }

}
