package Casio.parser;

import Casio.Casio;
import Casio.tasks.TaskList;
import Casio.ui.UI;
import Casio.exceptions.CasioException;
import Casio.tasks.Task;

import java.util.ArrayList;

public class Parser {


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

            UI.printOutputUI();
            TaskList.addDeadline(deadlineName, deadlineDetails);
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
                TaskList.addEvent(eventName, timeParts[0], timeParts[1]);
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
