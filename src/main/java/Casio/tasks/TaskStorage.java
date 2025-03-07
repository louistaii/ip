package Casio.tasks;
import Casio.exceptions.CasioException;
import Casio.parser.DateTimeParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDateTime;


/**
 * Provides persistent storage operations for Task objects.
 * <p>
 * This class handles reading tasks from a local save file, writing tasks to a file,
 * and formatting task data for storage.
 * </p>
 */
public class TaskStorage {

    /**
     * The file path where task data is stored.
     */
    private static final String FILE_PATH = "data/casio.txt";

    /**
     * Reads all tasks from the save file.
     * <p>
     * This method parses the save file and converts each line into the appropriate
     * Task object (Todo, Deadline, or Event).
     * If the save file doesn't exist, it creates the necessary directories and returns an empty ArrayList.
     * </p>
     * @return An ArrayList containing all tasks read from the storage file.
     */
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
                String fromString = details[4];
                String toString = details[5];

                Task T = null;
                switch (taskType) {
                case "[T]":
                    T = new Todo(taskName);
                    break;
                case "[D]":
                    LocalDateTime by = DateTimeParser.parseDateTime(taskDetails);
                    T = new Deadline(taskName, by);
                    break;
                case "[E]":
                    LocalDateTime from = DateTimeParser.parseDateTime(fromString);
                    LocalDateTime to = DateTimeParser.parseDateTime(toString);
                    T = new Event(taskName, from, to);
                    break;
                default:
                    System.out.println("Unknown task type: " + taskType);
                    continue;
                }

                if (taskStatus.equals("[✔]")) {
                    T.setDone(true);
                }

                taskArray.add(T);
            }
        }

        catch (FileNotFoundException e) {
            System.out.println("File not found at: " + FILE_PATH);
        } catch (CasioException e) {
            throw new RuntimeException(e);
        }

        return taskArray;
    }


    /**
     * Extracts task details from a string from the save file.
     * <p>
     * Extracts the task type, status, name, date and time.
     * </p>
     *
     * @param output The formatted string containing task information.
     * @return String array containing parsed task details:
     *<ul>
     * <li>[0] - Task type ("[T]", "[D]", or "[E]")</li>
     * <li>[1] - Task status ("[✔]" or "[ ]")</li>
     * <li>[2] - Task name</li>
     * <li>[3 - 4] - Task details ( deadline date time (by),  event date time (from, to) )</li>
     * </ul>
     * Returns an empty array if input is invalid.
     */
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

    /**
     * Formats a Task object into a string for storage.
     * <p>
     * String format: [task type] | [task status] | task name | Date and time
     * </p>
     *
     * @param task The Task object to format.
     * @return Formatted string for save file.
     * @throws CasioException If there is an error formatting the task.
     */
    public static String formatString(Task task) throws CasioException {
        String taskName = task.getDescription();
        String taskType = task.getTypeIcon();
        String taskStatus = task.getStatusIcon();
        String taskDetails = "";
        if (task instanceof Event eventTask) {
            taskDetails = " | " + eventTask.getFromSaveFormat() + " | " + eventTask.getToSaveFormat();
        } else if (task instanceof Deadline deadlineTask) {
            taskDetails = " | " + deadlineTask.getBySaveFormat();
        }

        return taskType + " | " + taskStatus +" | "+ taskName + taskDetails;
    }


    /**
     * Overwrites the storage file with the current ArrayList of tasks.
     * @param taskArray The ArrayList of Task objects to replace the save file with.
     */
    public static void overwriteSaveFile(ArrayList<Task> taskArray) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            for (Task task : taskArray) {
                writer.write(formatString(task) + "\n");
            }
        } catch (IOException | CasioException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Appends a single task to the storage file.
     * <p>
     * Does not modify existing content.
     * </p>
     * @param task Task object to append to the storage file.
     */
    public static void appendTaskToFile(Task task) {
        try(FileWriter writer = new FileWriter(FILE_PATH, true)) { // create a FileWriter in append mode
            writer.write(formatString(task)+ "\n");
        } catch (IOException | CasioException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

}
