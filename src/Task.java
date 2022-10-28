import java.time.LocalDateTime;
import java.util.Objects;

public class Task {
    private static int idGenerator = 0;
    private final Integer id = idGenerator++;
    private String header;
    private String description;
    private LocalDateTime deadline;
    private final Boolean isPersonalTask;

    public Task(String header, String description, LocalDateTime deadline,
                Boolean isPersonalTask) {
        setHeader(header);
        setDescription(description);
        this.deadline = deadline;
        this.isPersonalTask = isPersonalTask;
    }

    public Integer getId() {
        return id;
    }

    protected LocalDateTime getDeadline() {
        return deadline;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        String s = "личная";
        if (!isPersonalTask) s = "рабочая";
        return id + "   " + header + ": " + description + "\nТип задачи: " + s;

    }
}
