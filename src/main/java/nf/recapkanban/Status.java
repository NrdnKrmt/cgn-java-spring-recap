package nf.recapkanban;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {
    TODO("ToDo"),
    DOING("Doing"),
    DONE("Done");

    private final String statusString;

    Status(String statusString) {
        this.statusString = statusString;
    }

    @JsonValue
    public String getStatusString() {
        return statusString;
    }
}
