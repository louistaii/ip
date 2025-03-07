package Casio.tasks;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[✔]" : "[ ]"); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public boolean getDone() {
        return isDone;
    }

    public String getType() {
        return " ";
    }

    public String getTypeIcon() {
        return "[ ]";
    }

    @Override
    public String toString(){
        return " " + description;
    }
}
