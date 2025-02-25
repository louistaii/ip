package Casio.tasks;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;


public class TaskStorage {
    private static final String FILE_PATH = "data/casio.txt";

    public static ArrayList<Task> readFile(){
        File taskFile = new File(FILE_PATH); // create a File for the given file path
        ArrayList<Task> taskArray = new ArrayList<Task>();

        if (!taskFile.exists()) {
            taskFile.getParentFile().mkdirs();
            return taskArray;
        }

        try (Scanner fileScanner = new Scanner(taskFile)) {
            while (fileScanner.hasNext()) {
                String output = fileScanner.nextLine();
                String[] details = getDetails(output);

                if (details == null || details.length < 3) {
                    continue; // skip invalid lines instead of throwing an error
                }

                String taskType = details[0];
                String taskStatus = details[1];
                String taskName = details[2];
                String taskDetails = details[3];
                String from = details[4];
                String to = details[5];

                Task T = null;
                switch (taskType) {
                case "[T]":
                    T = new Todo(taskName);
                    break;
                case "[D]":
                    T = new Deadline(taskName, taskDetails);
                    break;
                case "[E]":
                    T = new Event(taskName, from, to);
                    break;
                default:
                    System.out.println("Unknown task type: " + taskType);
                    continue;
                }

                if (taskStatus.equals("[X]")) {
                    T.setDone(true);
                }

                if (T != null) {
                    taskArray.add(T);
                }
            }
        }

        catch (FileNotFoundException e) {
            System.out.println("File not found at: " + FILE_PATH);
        }

        return taskArray;
    }

    private static String[] getDetails(String output) {
        String[] parts = output.split(" \\| ");

        if (parts.length < 3) {
            return new String[0];
        }

        String taskDetails = "";
        String from = "";
        String to = "";

        String taskType = parts[0];
        String taskStatus = parts[1];
        String taskName = parts[2];

        if (parts.length == 4) {
            taskDetails = parts[3];
        }

        if (parts.length == 5) {
            from = parts[3];
            to = parts[4];
        }

        return new String[]{taskType, taskStatus, taskName, taskDetails, from, to};
    }

    public static String formatString(Task task){
        String taskName = task.getDescription();
        String taskType = task.getTypeIcon();
        String taskStatus = task.getStatusIcon();
        String taskDetails = "";
        if (task instanceof Event eventTask) {
            taskDetails = " | " + eventTask.getFrom() + " | " + eventTask.getTo();
        } else if (task instanceof Deadline deadlineTask) {
            taskDetails = " | " + deadlineTask.getBy();
        }

        return taskType + " | " + taskStatus +" | "+ taskName + taskDetails;
    }

    public static void overwriteSaveFile(ArrayList<Task> taskArray) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            for (Task task : taskArray) {
                writer.write(formatString(task) + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    public static void appendTaskToFile(Task task) {
        try(FileWriter writer = new FileWriter(FILE_PATH, true)) { // create a FileWriter in append mode
            writer.write(formatString(task)+ "\n");
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

}
