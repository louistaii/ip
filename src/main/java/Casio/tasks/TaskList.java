package Casio.tasks;
import Casio.ui.UI;
import Casio.exceptions.CasioException;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.Comparator;


/**
 * Manages an ArrayList of tasks and allows operations such as adding, deleting, marking, unmarking and sorting tasks.
 * <p>
 * The class supports different types of tasks, including {@link Todo} , {@link Event}, and {@link Deadline}.
 * Interacts with TaskStorage to ensure updates to tasks list are saved locally.
 * </p>
 */
public class TaskList {
    private static ArrayList<Task> taskArray;

    public TaskList(ArrayList<Task> taskArray) {
        this.taskArray = taskArray;
    }

    public static int size() {
        return taskArray.size();
    }

    public static Task get(int index) {
        return taskArray.get(index);
    }

    /**
     * Sorts the array list by date and time.
     * Earliest on top, latest on the bottom.
     * Todo tasks without date and time are sorted to the bottom of the list.
     */
    public static void sortByTime() {
        taskArray.sort(Comparator.comparing(task -> {
            if (task instanceof Event eventTask) {
                return eventTask.getFrom();
            } else if (task instanceof Deadline deadlineTask) {
                return deadlineTask.getBy();
            }
            return LocalDateTime.MAX;
        }));
        TaskStorage.overwriteSaveFile(taskArray);
        UI.printSortedByTime();
    }

    /**
     * Adds Todo task type to the task list.
     * @param taskName The name of the Todo task.
     */
    public static void addTodo(String taskName) {
        Todo task = new Todo(taskName);
        taskArray.add(task);
        TaskStorage.appendTaskToFile(task);
        UI.printAddedTaskUI(task,taskArray.size());
    }

    /**
     * Adds Event task type to the task list.
     * @param name The name of the Event task.
     * @param from Event start date and time.
     * @param to Event end date and time.
     */
    public static void addEvent(String name, LocalDateTime from, LocalDateTime to){
        Event task = new Event(name, from, to);
        taskArray.add(task);
        TaskStorage.appendTaskToFile(task);
        UI.printAddedTaskUI(task,taskArray.size());
    }

    /**
     * Adds Deadline task type to the task list.
     * @param name The name of the deadline task.
     * @param by deadline for task.
     */
    public static void addDeadline(String name, LocalDateTime by){
        Deadline task = new Deadline(name, by);
        taskArray.add(task);
        TaskStorage.appendTaskToFile(task);
        UI.printAddedTaskUI(task,taskArray.size());
    }

    /**
     * Marks a task in the list as done.
     * @param index Index of the selected task (1st task is indexed 1).
     */
    public static void markTask(int index) {
        Task task = taskArray.get(index);
        task.setDone(true);
        TaskStorage.overwriteSaveFile(taskArray);
        UI.printMarkedTaskUI(task);
    }

    /**
     * Unmarks a task in the list as done.
     * @param index Index of the selected task (1st task is indexed 1).
     */
    public static void unmarkTask(int index) {
        Task task = taskArray.get(index);
        task.setDone(false);
        TaskStorage.overwriteSaveFile(taskArray);
        UI.printUnmarkedTaskUI(task);
    }

    /**
     * Deletes a task in the list as done.
     * @param index Index of the selected task (1st task is indexed 1).
     */
    public static void deleteTask(int index) {
        Task task = taskArray.get(index);
        UI.printDeleteTaskUI(task, index);
        taskArray.remove(index);
        TaskStorage.overwriteSaveFile(taskArray);
        UI.printTaskNumber(taskArray.size());
    }

    /**
     * Finds tasks with names containing the specified keyword.
     * @param taskName The keyword to search for in task name.
     */
    public static void findTask(String taskName) {
        ArrayList<Task> searchResults = new ArrayList<>();
        for (Task task : taskArray) {
            if (task.getDescription().toLowerCase().contains(taskName.toLowerCase())) {
                searchResults.add(task);
            }
        }
        UI.printSearchResults(searchResults);
    }


}
