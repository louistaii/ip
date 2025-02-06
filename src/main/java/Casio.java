import java.util.Scanner;

public class Casio{

    private static final int MAX_SIZE = 100;
    private static int taskNumber = 0;

    public static void main(String[] args) {
        String greeting = "____________________________________________________________"
                + "\nHello! I'm Casio\n"
                + "What can I do for you?\n";
        System.out.println(greeting);
        getInput();
    }

    public static void exit(){
        String bye = "____________________________________________________________"
                + "\nBye. Hope to see you again soon!\n\n"
                +"____________________________________________________________";
        System.out.println(bye);
    }


    public static void addTask(Task[] taskArray, String name){
        Task t = new Task(name);
        taskArray[taskNumber] = t;
        System.out.println("added task: "+ name);
    }

    public static void addTodo(Task[] taskArray, String name){
        Todo t = new Todo(name);
        taskArray[taskNumber] = t;
        System.out.println("added todo: "+ name);
    }

    public static void addEvent(Task[] taskArray, String name, String from, String to){
        Event t = new Event(name, from, to);
        taskArray[taskNumber] = t;
        System.out.println("added event: "+ name);
    }

    public static void addDeadline(Task[] taskArray, String name, String by){
        Deadline t = new Deadline(name, by);
        taskArray[taskNumber] = t;
        System.out.println("added deadline: "+ name);
    }

    public static void printTasks(Task[] taskArray){
        for (int i = 0; i < taskNumber; i++) {
            if (taskArray[i]!=null) {
                String status_icon = taskArray[i].getStatusIcon();
                String type_icon = taskArray[i].getTypeIcon();
                int index = i+1;
                String output = index + ". " +
                        type_icon +
                        status_icon;
                System.out.println(output + taskArray[i]);
            }
        }
        System.out.println("You have "+ taskNumber +" tasks.");
    }


    public static void markTask(Task[] taskArray, int index){
        if (index >= 0 && index < taskNumber) {
            taskArray[index].isDone = true;
            System.out.println(taskArray[index].description + " marked as done.");
        } else {
            System.out.println("Invalid task index.");
        }
    }

    public static void unmarkTask(Task[] taskArray, int index){
        if (index >= 0 && index < taskNumber) {
            taskArray[index].isDone = false;
            System.out.println(taskArray[index].description + " marked as undone.");
        } else {
            System.out.println("Invalid task index.");
        }
    }

    public static void getInput(){
        Scanner scanner = new Scanner(System.in);
        Task[] taskArray =  new Task[MAX_SIZE]; //array to keep track of tasks

        //loop to get inputs
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                exit();
                break;
            }
            if (input.equalsIgnoreCase("list")){
                printTasks(taskArray);
            } else if (input.startsWith("mark ")){
                int index = Integer.parseInt(input.substring(5)) - 1;
                markTask(taskArray, index);
            } else if (input.startsWith("unmark ")){
                int index = Integer.parseInt(input.substring(7)) - 1;
                unmarkTask(taskArray, index);
            } else if (input.startsWith("todo ")){
                String name = input.substring(5);
                addTodo(taskArray, name);
                taskNumber++;
                System.out.println("Now you have "+ taskNumber +" tasks in the list.");
            } else if (input.startsWith("event ")){
                String[] parts = input.substring(6).split("/from");
                String name = parts[0].trim();
                String eventDetails = "";

                if (parts.length > 1) {
                    eventDetails = parts[1].trim();
                }

                if (eventDetails.contains("/to")) {
                    String[] timeParts = eventDetails.split("/to");
                    addEvent(taskArray, name, timeParts[0], timeParts[1]);
                    taskNumber++;
                    System.out.println("Now you have "+ taskNumber +" tasks in the list.");
                }else{
                    System.out.println("Please insert a valid end name.");
                }
            } else if (input.startsWith("deadline ")){
                String[] parts = input.substring(9).split("/by");
                String name = parts[0].trim();
                String deadline = "";
                if (parts.length > 1) {
                    deadline = parts[1].trim();
                }
                addDeadline(taskArray, name, deadline);
                taskNumber++;
                System.out.println("Now you have "+ taskNumber +" tasks in the list.");
            } else {
                addTask(taskArray, input);
                taskNumber++;
                System.out.println("Now you have "+ taskNumber +" tasks in the list.");
            }
        }
    }
}
