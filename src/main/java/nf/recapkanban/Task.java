package nf.recapkanban;

public record Task(
        String id,
        String name,
        Status status
) {
}
