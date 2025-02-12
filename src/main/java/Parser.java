public class Parser {
    public static int parseInput(String input, int taskNumber, Task[] taskArray) {

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
            Casio.markTask(taskIndex-1);
            break;

        case "unmark":
            taskIndex = Integer.parseInt(taskName);
            Casio.unmarkTask(taskIndex-1);
            break;

        case "todo":
            Casio.addTodo(taskName);
            taskNumber++;
            System.out.println("Now you have " + taskNumber + " tasks in the list.");
            break;

        case "deadline":
            String[] deadlineParts = taskName.split("/by");
            String deadlineName = deadlineParts[0].trim();
            String deadlineDetails = "";
            if (deadlineParts.length > 1) {
                deadlineDetails = deadlineParts[1].trim();
            }
            Casio.addDeadline(deadlineName, deadlineDetails);
            taskNumber++;
            System.out.println("Now you have " + taskNumber + " tasks in the list.");
            break;

        case "event":
            String[] eventParts = taskName.split("/from");
            String eventName = eventParts[0].trim();
            String eventDetails = "";

            if (eventParts.length > 1) {
                eventDetails = eventParts[1].trim();
            }

            if (eventDetails.contains("/to")) {
                String[] timeParts = eventDetails.split("/to");
                Casio.addEvent(eventName, timeParts[0], timeParts[1]);
                taskNumber++;
                System.out.println("Now you have " + taskNumber + " tasks in the list.");
            } else {
                System.out.println("Please insert a valid end name.");
            }
            break;

        }
        return taskNumber;
    }


}
