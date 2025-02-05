import java.util.Scanner;

public class casio{

    public static void main(String[] args) {
        String greeting = "____________________________________________________________"
                + "\nHello! I'm Casio\n"
                + "What can I do for you?\n";
        System.out.println(greeting);
        get_input();
    }

    public static void exit(){
        String bye = "____________________________________________________________"
                + "\nBye. Hope to see you again soon!\n\n"
                +"____________________________________________________________";
        System.out.println(bye);
    }

    public static void add_task(Task[] task_array, String name, String type, int task_number, String details){
        Task t = new Task(name, type);
        t.date = details;
        task_array[task_number] = t;
        System.out.println("added: "+ name);
    }

    public static void print_tasks(Task[] task_array, int task_number){
        for (int i = 0; i < task_array.length; i++) {
            if (task_array[i]!=null) {
                String status_icon = task_array[i].getStatusIcon();
                String type_icon = task_array[i].getTypeIcon();
                int index = i+1;
                String output = index+". " +
                        "[" + type_icon +"]" +
                        "[" + status_icon + "] " +
                        task_array[i].description +
                        " " + task_array[i].date;
                System.out.println(output);
            }
        }
        System.out.println("Now you have "+ task_number +" tasks in the list.");
    }


    public static void mark_tasks(Task[] task_array, int index, int task_number){
        if (index >= 0 && index < task_number) {
            task_array[index].isDone = true;
            System.out.println(task_array[index].description + " marked as done.");
        } else {
            System.out.println("Invalid task index.");
        }
    }

    public static void unmark_tasks(Task[] task_array, int index, int task_number){
        if (index >= 0 && index < task_number) {
            task_array[index].isDone = false;
            System.out.println(task_array[index].description + " marked as undone.");
        } else {
            System.out.println("Invalid task index.");
        }
    }

    public static void get_input(){
        Scanner scanner = new Scanner(System.in);
        Task[] task_array =  new Task[100]; //array to keep track of tasks
        int task_number = 0;

        //loop to get inputs
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                exit();
                break;
            }
            if (input.equalsIgnoreCase("list")){
                print_tasks(task_array, task_number);
            }
            else if (input.startsWith("mark ")){
                int index = Integer.parseInt(input.substring(5)) - 1;
                mark_tasks(task_array, index, task_number);
            }
            else if (input.startsWith("unmark ")){
                int index = Integer.parseInt(input.substring(7)) - 1;
                unmark_tasks(task_array, index, task_number);
            }

            else if (input.startsWith("todo ")){
                String name = input.substring(5);
                add_task(task_array, name, "todo", task_number, "");
                task_number++;
            }

            else if (input.startsWith("event ")){
                String[] parts = input.substring(6).split("/from");
                String name = parts[0].trim();
                String eventDetails = "";
                if (parts.length > 1) {
                    eventDetails = parts[1].trim();
                }

                if (eventDetails.contains("/to")) {
                    String[] timeParts = eventDetails.split("/to");
                    eventDetails = "(from: " + timeParts[0] + " to:" + timeParts[1]+")";
                }
                add_task(task_array, name, "event", task_number, eventDetails);
                task_number++;
            }

            else if (input.startsWith("deadline ")){
                String[] parts = input.substring(9).split("/by");
                String name = parts[0].trim();
                String deadline = "";
                if (parts.length > 1) {
                    deadline = parts[1].trim();
                }
                add_task(task_array, name, "deadline", task_number, "(by: "+ deadline+")");
                task_number++;
            }
            else{
                add_task(task_array, input, "", task_number,"");
                task_number++;
            }
        }
        scanner.close();
    }

}
