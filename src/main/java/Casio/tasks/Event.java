package Casio.tasks;

public class Event extends Task {
    protected String from ="";
    protected String to = "";

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return this.from;
    }

    public String getTo() {
        return this.to;
    }

    @Override
    public String getTypeIcon() {
        return "[E]";
    }

    @Override
    public String toString(){
        return (" " + description + " (from: " + from + "to:" + to + ")");
    }
}