package Casio.parser;

import Casio.tasks.TaskList;
import Casio.ui.UI;
import Casio.exceptions.CasioException;

import java.time.LocalDateTime;


/**
 * Parses user input strings to determine the command type
 * and execute the appropriate action on the task list.
 */
public class CommandParser {

    /**
     * Supported commands include:
     * <ul>
     *     <li><code>bye</code> - Exit the application</li>
     *     <li><code>list</code> - Display all tasks</li>
     *     <li><code>sort /by[method]</code> - Sort tasks by specified method</li>
     *     <li><code>find [keyword]</code> - Find tasks containing keyword</li>
     *     <li><code>mark [index]</code> - Mark a task as done</li>
     *     <li><code>unmark [index]</code> - Mark a task as not done</li>
     *     <li><code>todo [description]</code> - Add a new todo task</li>
     *     <li><code>deadline [description] /by[datetime]</code> - Add a deadline task</li>
     *     <li><code>event [description] /from[datetime] /to[datetime]</code> - Add an event</li>
     *     <li><code>delete [index]</code> - Delete a task</li>
     * </ul>
     * @param input The user's input string to be parsed.
     * @param taskNumber The current number of tasks in the task list.
     * @param taskList The list of tasks.
     * @return <code>true</code> if the application should exit (bye command),
     *         <code>false</code> for all other commands.
     * @throws CasioException If there is an error executing the command, such as:
     *         <ul>
     *         <li>Invalid command format</li>
     *         <li>Missing required parameters</li>
     *         <li>Invalid task index</li>
     *         <li>Attempting invalid operations (i.e. marking already marked tasks)</li>
     *         </ul>
     */
    public static boolean parseInput(String input, int taskNumber, TaskList taskList) throws CasioException {

        String[] splitInput = input.split(" ", 2);
        String taskType = splitInput[0];
        String taskName = "";

        if (splitInput.length > 1) {
            taskName = splitInput[1];
        }

        int taskIndex;

        switch (taskType) {

        case "bye":
            UI.printOutputUI();
            UI.exit();
            return true;

        case "list":
            UI.printOutputUI();
            UI.printTasks(taskList);
            break;

        case "sort":

            if (taskName.isEmpty()){
                CasioException.missingSortBy();
            }

            String[] splittingMethodParts = taskName.split("/by");
            if (splittingMethodParts[1] == null || splittingMethodParts[1].isEmpty()){
                CasioException.missingSortBy();
            }
            String splittingMethod = splittingMethodParts[1].trim();

            if (TaskList.size() == 0){
                CasioException.emptyList();
            }
            if (splittingMethod.equals("time")){
                UI.printOutputUI();
                TaskList.sortByTime();
            }
            break;

        case "find":
            if (taskList.size()==0) {
                CasioException.emptyList();
            }
            if (taskName.isEmpty()) {
                CasioException.missingTaskName("find");
            }
            UI.printOutputUI();
            TaskList.findTask(taskName);
            break;

        case "mark":
            taskIndex = Integer.parseInt(taskName);

            taskIndex--;
            if (taskIndex <0 || taskIndex >= taskNumber){
                CasioException.invalidIndex(taskIndex, taskNumber);
            }
            if (taskList.get(taskIndex).getDone()){
                throw new CasioException("OOPS! Tried to mark a marked task!");
            }
            UI.printOutputUI();
            TaskList.markTask(taskIndex);
            break;

        case "unmark":
            taskIndex = Integer.parseInt(taskName);
            taskIndex--;
            if (taskIndex <0 || taskIndex >= taskNumber){
                CasioException.invalidIndex(taskIndex, taskNumber);
            }
            if (!taskList.get(taskIndex).getDone()){
                throw new CasioException("OOPS! Tried to unmark an unmarked task!");
            }
            UI.printOutputUI();
            TaskList.unmarkTask(taskIndex);
            break;

        case "todo":
            if (taskName.isEmpty()){
                CasioException.missingTaskName("todo");
            }
            UI.printOutputUI();
            TaskList.addTodo(taskName);
            break;

        case "deadline":
            if (taskName.isEmpty()){
                CasioException.missingTaskName("deadline");
            }
            String[] deadlineParts = taskName.split("/by");
            String deadlineName = deadlineParts[0].trim();

            if (deadlineParts.length == 1) {
                CasioException.missingDeadlineBy(deadlineName);
            }

            String deadlineDetails = deadlineParts[1].trim();
            LocalDateTime by = DateTimeParser.parseDateTime(deadlineDetails);

            UI.printOutputUI();
            TaskList.addDeadline(deadlineName, by);
            break;

        case "event":
            if (taskName.isEmpty()){
                CasioException.missingTaskName("event");
            }
            String[] eventParts = taskName.split("/from");
            String eventName = eventParts[0].trim();
            String eventDetails;

            if (eventParts.length == 1) {
                CasioException.missingEventFrom(eventName);
            }
            eventDetails = eventParts[1].trim();

            if (eventDetails.contains("/to")) {
                UI.printOutputUI();
                String[] timeParts = eventDetails.split("/to");
                LocalDateTime from = DateTimeParser.parseDateTime(timeParts[0].trim());
                LocalDateTime to = DateTimeParser.parseDateTime(timeParts[1].trim());
                TaskList.addEvent(eventName, from, to);
            } else {
                CasioException.missingEventTo(eventName);
            }
            break;

        case "delete":
            if (taskName.isEmpty()){
                CasioException.missingTaskName("deadline");
            }

            taskIndex = Integer.parseInt(taskName);
            taskIndex--;
            if (taskIndex <0 || taskIndex >= taskNumber){
                CasioException.invalidIndex(taskIndex, taskNumber);
            }
            UI.printOutputUI();
            TaskList.deleteTask(taskIndex);
            break;

        default:
            CasioException.invalidCommand(taskType);
        }
        return false;
    }
}
