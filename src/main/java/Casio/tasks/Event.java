package Casio.tasks;

import Casio.exceptions.CasioException;
import Casio.parser.DateTimeParser;

import java.time.LocalDateTime;

public class Event extends Task {

    private final LocalDateTime from;
    private final LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }


    public LocalDateTime getFrom() {
        return this.from;
    }

    public LocalDateTime getTo()  {
        return this.from;
    }

    public String getFromSaveFormat() throws CasioException {
        return DateTimeParser.saveDateTimeFormat(this.from);
    }

    public String getToSaveFormat() throws CasioException {
        return DateTimeParser.saveDateTimeFormat(this.to);
    }

    @Override
    public String getTypeIcon() {
        return "[E]";
    }

    @Override
    public String getType() {
        return "event";
    }

    @Override
    public String toString(){
        try {
            return (" " + description + " (from: " + DateTimeParser.dateTimeToString(from) + " to: " + DateTimeParser.dateTimeToString(to) + ")");
        } catch (CasioException e) {
            throw new RuntimeException(e);
        }
    }


}