public class CasioException extends Exception {
    public CasioException(String message) {
        super(message);
    }

    public static void invalidIndex(int index) throws CasioException {
        index++;
        throw new CasioException("OOPS! There are no tasks with index: " + index);
    }

    public static void missingDetails(String detail, String name) throws CasioException {
        throw new CasioException("OOPS! Missing details ("+detail+") for: " + name);
    }

    public static void missingTaskName(String type) throws CasioException {
        throw new CasioException("OOPS! You must specify a name for " + type + " tasks! (eg. '"+type+" InsertNameHere)'");
    }
}
