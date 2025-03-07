package Casio.ui;

import Casio.tasks.Task;
import Casio.tasks.TaskList;

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

    public static void printAddedTaskUI(Task task, int taskNumber){
        String taskType = task.getType();
        System.out.println("Added " + taskType + ":" + task + " into the list.");
        printTaskNumber(taskNumber);
    }

    public static void printMarkedTaskUI(Task task){
        String taskDescription = task.getDescription();
        System.out.println(taskDescription + " marked as done. Good job!!\n");
    }

    public static void printUnmarkedTaskUI(Task task){
        String taskDescription = task.getDescription();
        System.out.println(taskDescription + " marked as undone.\n");
    }

    public static void printDeleteTaskUI(Task task, int index){
        String status_icon = task.getStatusIcon();
        String type_icon = task.getTypeIcon();
        String output = (index+1) + ". " +
                type_icon +
                status_icon;
        System.out.println("Deleted " + output + task);
    }

    public static void printSearchResults(ArrayList<Task> searchResults) {
        System.out.println("You have " + searchResults.size() + " search result(s):");
        int index = 1;
        for (Task task : searchResults) {
            String status_icon = searchResults.get(index-1).getStatusIcon();
            String type_icon = searchResults.get(index-1).getTypeIcon();
            System.out.println(index + "."
                    + type_icon
                    + status_icon
                    + task);
            index++;
        }
        System.out.println();
    }
    public static void printTasks(TaskList taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i) != null) {
                String status_icon = taskList.get(i).getStatusIcon();
                String type_icon = taskList.get(i).getTypeIcon();
                int index = i + 1;
                String output = index + ". " +
                        type_icon +
                        status_icon;
                System.out.println(output + taskList.get(i));
            }
        }
        System.out.println("You have " + taskList.size() + " task(s).\n");
    }

}
