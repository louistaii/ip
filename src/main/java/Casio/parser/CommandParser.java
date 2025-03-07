package Casio.parser;

import Casio.tasks.TaskList;
import Casio.ui.UI;
import Casio.exceptions.CasioException;

import java.time.LocalDateTime;

public class CommandParser {


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
