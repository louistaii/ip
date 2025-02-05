public class Task
{
    protected String description;
    protected boolean isDone;
    protected String type;
    protected String date;

    public Task(String description, String type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
        this.date = "";
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public String getTypeIcon() {
        if (type == "todo"){
            return "T";
        }
        if (type == "event"){
            return "E";
        }
        if (type == "deadline"){
            return "D";
        }
        return " ";
    }
}
