import java.util.Scanner;

public class casio{
    public static void main(String[] args) {
        String greeting = "____________________________________________________________"
                + "\nHello! I'm Casio\n"
                + "What can I do for you?\n";
        System.out.println(greeting);
        get_task();
    }

    public static void exit(){
        String bye = "____________________________________________________________"
                + "\nBye. Hope to see you again soon!\n\n"
                +"____________________________________________________________";
        System.out.println(bye);
    }


    public static void print_tasks(Task[] task_array){
        for (int i = 0; i < task_array.length; i++) {
            if(task_array[i]!=null) {
                String status_icon = task_array[i].getStatusIcon();
                int index = i+1;
                System.out.println(index+". " + " [" + status_icon + "] " + task_array[i].description);
            }
        }
    }

    public static void get_task(){
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
                print_tasks(task_array);
            }
            else if (input.startsWith("mark ")){
                int index = Integer.parseInt(input.substring(5)) - 1;
                if (index >= 0 && index < task_number) {
                    task_array[index].isDone = true;
                    System.out.println(task_array[index].description + " marked as done.");
                } else {
                    System.out.println("Invalid task index.");
                }
            }
            else if (input.startsWith("unmark ")){
                int index = Integer.parseInt(input.substring(7)) - 1;
                if (index >= 0 && index < task_number) {
                    task_array[index].isDone = false;
                    System.out.println(task_array[index].description + " marked as undone.");
                } else {
                    System.out.println("Invalid task index.");
                }
            }
            else{
                Task t = new Task(input);
                task_array[task_number] = t;
                System.out.println("added: "+ input);
                task_number++;
            }
        }
        scanner.close();
    }

}
