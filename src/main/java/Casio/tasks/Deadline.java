package Casio.tasks;

import Casio.exceptions.CasioException;
import Casio.parser.DateTimeParser;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private final LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }
    public LocalDateTime getBy(){
        return this.by;
    }
    public String getBySaveFormat() throws CasioException {
        return DateTimeParser.saveDateTimeFormat(this.by);
    }

    @Override
    public String getTypeIcon() {
         return "[D]";
    }

    @Override
    public String getType() {
        return "deadline";
    }

    @Override
    public String toString(){

        try {
            return (" " + description + " (by: " + DateTimeParser.dateTimeToString(by) +")");
        } catch (CasioException e) {
            throw new RuntimeException(e);
        }
    }
}
