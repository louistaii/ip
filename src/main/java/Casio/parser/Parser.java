package Casio.parser;

import Casio.Casio;
import Casio.ui.UI;
import Casio.exceptions.CasioException;
import Casio.tasks.Task;

import java.util.ArrayList;

public class Parser {


    public static int parseInput(String input, int taskNumber, ArrayList<Task> taskArray) throws CasioException {


        String[] splitInput = input.split(" ", 2);
        String taskType = splitInput[0];
        String taskName = "";

        if (splitInput.length > 1) {
            taskName = splitInput[1];
        }

        int taskIndex;

        switch (taskType) {
        case "bye":
            UI.exit();
            break;
        case "list":
            UI.printTasks(taskArray);
            break;

        case "mark":
            taskIndex = Integer.parseInt(taskName);

            taskIndex--;
            if (taskIndex <0 || taskIndex >= taskNumber){
                CasioException.invalidIndex(taskIndex, taskNumber);
            }
            if (taskArray.get(taskIndex).getDone()){
                throw new CasioException("OOPS! Tried to mark a marked task!");
            }
            Casio.markTask(taskIndex);
            break;

        case "unmark":
            taskIndex = Integer.parseInt(taskName);
            taskIndex--;
            if (taskIndex <0 || taskIndex >= taskNumber){
                CasioException.invalidIndex(taskIndex, taskNumber);
            }
            if (!taskArray.get(taskIndex).getDone()){
                throw new CasioException("OOPS! Tried to unmark an unmarked task!");
            }
            Casio.unmarkTask(taskIndex);
            break;

        case "todo":
            if (taskName.isEmpty()){
                CasioException.missingTaskName("todo");
            }
            Casio.addTodo(taskName);
            taskNumber++;
            System.out.println("Now you have " + taskNumber + " tasks in the list.");
            break;

        case "deadline":
            if (taskName.isEmpty()){
                CasioException.missingTaskName("deadline");
            }
            String[] deadlineParts = taskName.split("/by");
            String deadlineName = deadlineParts[0].trim();

            if (deadlineParts.length <= 1) {
                CasioException.missingDetails("by", deadlineName);
            }

            String deadlineDetails = deadlineParts[1].trim();

            Casio.addDeadline(deadlineName, deadlineDetails);
            taskNumber++;
            System.out.println("Now you have " + taskNumber + " tasks in the list.");
            break;

        case "event":
            if (taskName.isEmpty()){
                CasioException.missingTaskName("event");
            }
            String[] eventParts = taskName.split("/from");
            String eventName = eventParts[0].trim();
            String eventDetails;

            if (eventParts.length <= 1) {
                CasioException.missingDetails("from", eventName);
            }
            eventDetails = eventParts[1].trim();

            if (eventDetails.contains("/to")) {
                String[] timeParts = eventDetails.split("/to");
                Casio.addEvent(eventName, timeParts[0], timeParts[1]);
                taskNumber++;
                System.out.println("Now you have " + taskNumber + " tasks in the list.");
            } else {
                CasioException.missingDetails("to", eventName);
            }
            break;

        case "delete":
            taskIndex = Integer.parseInt(taskName);
            taskIndex--;
            if (taskIndex <0 || taskIndex >= taskNumber){
                CasioException.invalidIndex(taskIndex, taskNumber);
            }
            Casio.deleteTask(taskIndex);
            break;


        default:
            throw new CasioException("Invalid command: " + taskType);
        }
        return taskNumber;
    }

}
