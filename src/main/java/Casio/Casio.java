package Casio;

import Casio.parser.Parser;
import Casio.ui.UI;
import Casio.exceptions.CasioException;
import Casio.tasks.Deadline;
import Casio.tasks.Event;
import Casio.tasks.Task;
import Casio.tasks.Todo;

import java.util.Scanner;

public class Casio{

    private static final int MAX_SIZE = 100;
    private static int taskNumber = 0;
    private static Task[] taskArray =  new Task[MAX_SIZE];



    public static void addTodo(String name){
        Todo t = new Todo(name);
        taskArray[taskNumber] = t;
        System.out.println("added todo: "+ name);
    }

    public static void addEvent(String name, String from, String to){
        Event t = new Event(name, from, to);
        taskArray[taskNumber] = t;
        System.out.println("added event: "+ name);
    }

    public static void addDeadline(String name, String by){
        Deadline t = new Deadline(name, by);
        taskArray[taskNumber] = t;
        System.out.println("added deadline: "+ name);
    }


    public static void markTask(int index){
        taskArray[index].setDone(true);
        String taskDescription = taskArray[index].getDescription();
        System.out.println(taskDescription + " marked as done.");
    }

    public static void unmarkTask(int index){
        taskArray[index].setDone(false);
        String taskDescription = taskArray[index].getDescription();
        System.out.println(taskDescription + " marked as undone.");
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
