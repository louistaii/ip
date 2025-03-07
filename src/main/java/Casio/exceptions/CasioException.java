package Casio.exceptions;

public class CasioException extends Exception {
    public CasioException(String message) {
        super(message);
    }

    public static void invalidCommand(String command) throws CasioException {
        throw new CasioException("OOPS! " + command + " is not a valid command.\n"
                + "List of valid commands: 'todo', 'deadline', 'event', 'list', 'bye'");
    }

    public static void invalidIndex(int index, int taskNumber) throws CasioException {
        index++;
        throw new CasioException("OOPS! There are no tasks with index: " + index
                + " (valid index: 1 to " + taskNumber + ")");
    }

    public static void emptyList() throws CasioException {
        throw new CasioException("OOPS! Unable to fulfill command because your list is empty!");
    }

    public static void missingSortBy() throws CasioException {
        throw new CasioException("OOPS! Missing details (/by) for sort command: "
                + "\n(eg. 'sort /by time)'");
    }

    public static void missingDeadlineBy(String name) throws CasioException {
        throw new CasioException("OOPS! Missing details (/by) for: " + name
                + "\n(eg. '" + name + " /by InsertDeadlineHere)'");
    }

    public static void missingEventFrom(String name) throws CasioException {
        throw new CasioException("OOPS! Missing details (/from) for: " + name
                + "\n(eg. '" + name + " /from InsertEvenStartTimeHere /to InsertEvenEndTimeHere')");
    }

    public static void missingEventTo(String name) throws CasioException {
        throw new CasioException("OOPS! Missing details (/to) for: " + name
                + "\n(eg. '" + name + " /from InsertEvenStartTimeHere /to InsertEvenEndTimeHere')");
    }

    public static void missingTaskName(String type) throws CasioException {
        throw new CasioException("OOPS! You must specify a name for " + type + " tasks!"
                + "\n(eg. '" + type + " InsertNameHere')");
    }

    public static void unrecognizedDateTime() throws CasioException {
        throw new CasioException("OOPS! Invalid Date Time format. Try: yyyy-mm-dd hh:mm");
    }

}
