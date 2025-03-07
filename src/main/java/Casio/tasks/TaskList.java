package Casio.tasks;
import Casio.exceptions.CasioException;
import Casio.ui.UI;
import java.util.ArrayList;

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

    public static void addTodo(String taskName) {
        Todo task = new Todo(taskName);
        taskArray.add(task);
        TaskStorage.appendTaskToFile(task);
        UI.printAddedTaskUI(task,taskArray.size());
    }

    public static void addEvent(String name, String from, String to){
        Event task = new Event(name, from, to);
        taskArray.add(task);
        TaskStorage.appendTaskToFile(task);
        UI.printAddedTaskUI(task,taskArray.size());
    }

    public static void addDeadline(String name, String by){
        Deadline task = new Deadline(name, by);
        taskArray.add(task);
        TaskStorage.appendTaskToFile(task);
        UI.printAddedTaskUI(task,taskArray.size());
    }


    public static void markTask(int index) {
        Task task = taskArray.get(index);
        task.setDone(true);
        TaskStorage.overwriteSaveFile(taskArray);
        UI.printMarkedTaskUI(task);
    }

    public static void unmarkTask(int index) {
        Task task = taskArray.get(index);
        task.setDone(false);
        TaskStorage.overwriteSaveFile(taskArray);
        UI.printUnmarkedTaskUI(task);
    }

    public static void deleteTask(int index) {
        Task task = taskArray.get(index);
        UI.printDeleteTaskUI(task, index);
        taskArray.remove(index);
        TaskStorage.overwriteSaveFile(taskArray);
        UI.printTaskNumber(taskArray.size());
    }

    public static void findTask(String taskName) throws CasioException {
        ArrayList<Task> searchResults = new ArrayList<>();
        for (Task task : taskArray) {
            if (task.getDescription().toLowerCase().contains(taskName.toLowerCase())) {
                searchResults.add(task);
            }
        }
        UI.printSearchResults(searchResults);
    }


}
