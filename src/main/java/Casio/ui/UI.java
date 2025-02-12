package Casio.ui;

import Casio.tasks.Task;

public class UI {


    public static void greeting(){
        String greeting = "____________________________________________________________"
                + "\nHello! I'm Casio\n"
                + "What can I do for you?\n";
        System.out.println(greeting);
    }

    public static void exit(){
        String bye = "____________________________________________________________"
                + "\nBye. Hope to see you again soon!\n\n"
                +"____________________________________________________________";
        System.out.println(bye);
    }


    public static void printTasks(int taskNumber, Task[] taskArray) {
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

}
