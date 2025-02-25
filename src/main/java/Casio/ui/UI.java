package Casio.ui;

import Casio.tasks.Task;

import java.util.ArrayList;

public class UI {
    public static final String RESET = "\u001B[0m";
    public static final String CYAN = "\u001B[36m";
    public static final String GREEN = "\u001B[32m";
    public static final String BOLD = "\u001B[1m";

    public static void greeting(){
        System.out.println(CYAN
                + "\n   ██████╗  █████╗ ███████╗██╗ ██████╗  "
                + "\n  ██╔════╝ ██╔══██╗██╔════╝██║██╔═══██╗ "
                + "\n  ██║      ███████║███████╗██║██║   ██║ "
                + "\n  ██║      ██╔══██║╚════██║██║██║   ██║ "
                + "\n  ╚██████╗ ██║  ██║███████║██║╚██████╔╝ "
                + "\n   ╚═════╝ ╚═╝  ╚═╝╚══════╝╚═╝ ╚═════╝  "
                + "\n★彡★彡★彡★彡★彡★彡★彡★★彡★彡★彡★彡★彡★彡★"
                + RESET );

        String greeting = "Hello!! I'm Casio, your personal chat bot. ⊂(◉‿◉)つ"
                + "\nWhat can I do for you?\n";
        printOutputUI();
        System.out.println(greeting);
    }

    public static void printOutputUI(){
        System.out.println(CYAN + BOLD +"\nCasio:" + RESET);
    }

    public static void printInputUI(){
        System.out.println(GREEN + BOLD + "You:" + RESET);
    }

    public static void exit(){
        System.out.println("Bye bye! Hope to see you again soon! ( ╥﹏╥) ノシ");
    }

    public static void printTaskNumber(int taskNumber){
        System.out.println("You now have " + taskNumber + " task(s) in the list.\n");
    }

    public static void printTasks(ArrayList<Task> taskArray) {
        for (int i = 0; i < taskArray.size(); i++) {
            if (taskArray.get(i) != null) {
                String status_icon = taskArray.get(i).getStatusIcon();
                String type_icon = taskArray.get(i).getTypeIcon();
                int index = i + 1;
                String output = index + ". " +
                        type_icon +
                        status_icon;
                System.out.println(output + taskArray.get(i));
            }
        }
        System.out.println("You have " + taskArray.size() + " task(s).\n");
    }

}
