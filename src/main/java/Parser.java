public class Parser {

    public static int parseInput(String input, int taskNumber, Task[] taskArray) throws CasioException{

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
            UI.printTasks(taskNumber, taskArray);
            break;

        case "mark":
            taskIndex = Integer.parseInt(taskName);
            taskIndex--;
            if (taskIndex <0 || taskIndex >= taskNumber){
                CasioException.invalidIndex(taskIndex);
            }
            if (taskArray[taskIndex].isDone == true){
                throw new CasioException("OOPS! Tried to mark a marked task!");
            }
            Casio.markTask(taskIndex);
            break;

        case "unmark":
            taskIndex = Integer.parseInt(taskName);
            taskIndex--;
            if (taskIndex <0 || taskIndex >= taskNumber){
                CasioException.invalidIndex(taskIndex);
            }
            if (taskArray[taskIndex].isDone == false){
                throw new CasioException("OOPS! Tried to unmark an unmarked task!");
            }
            Casio.unmarkTask(taskIndex);
            break;

        case "todo":
            if (taskName.equals("")){
                CasioException.missingTaskName("todo");
            }
            Casio.addTodo(taskName);
            taskNumber++;
            System.out.println("Now you have " + taskNumber + " tasks in the list.");
            break;

        case "deadline":
            if (taskName.equals("")){
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
            if (taskName.equals("")){
                CasioException.missingTaskName("event");
            }
            String[] eventParts = taskName.split("/from");
            String eventName = eventParts[0].trim();
            String eventDetails = "";

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

        default:
            throw new CasioException("Invalid task type: " + taskType);
        }
        return taskNumber;
    }


}
