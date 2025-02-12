import java.util.Scanner;

public class Casio{

    private static final int MAX_SIZE = 100;
    private static int taskNumber = 0;
    private static Task[] taskArray =  new Task[MAX_SIZE];

    public static void addTask(Task[] taskArray, String name){
        Task t = new Task(name);
        taskArray[taskNumber] = t;
        System.out.println("added task: "+ name);
    }

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
        if (index >= 0 && index < taskNumber) {
            taskArray[index].isDone = true;
            System.out.println(taskArray[index].description + " marked as done.");
        } else {
            System.out.println("Invalid task index.");
        }
    }

    public static void unmarkTask(int index){
        if (index >= 0 && index < taskNumber) {
            taskArray[index].isDone = false;
            System.out.println(taskArray[index].description + " marked as undone.");
        } else {
            System.out.println("Invalid task index.");
        }
    }

    public static void main(String[] args) {
        UI.greeting();
        Scanner scanner = new Scanner(System.in);

        //loop to get inputs
        while (true) {
            String input = scanner.nextLine();
            taskNumber = Parser.parseInput(input, taskNumber, taskArray);
        }

    }

}
