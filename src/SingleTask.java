import java.time.LocalDateTime;

public class SingleTask extends Task implements Repeatable{
    public SingleTask(String header, String description, LocalDateTime deadline, Boolean isPersonalTask) {
        super(header, description, deadline, isPersonalTask);
    }

    @Override
    public LocalDateTime getNextDeadline() {
        if (LocalDateTime.now().isBefore(getDeadline())) {
            return getDeadline();
        } else return null;
    }

    @Override
    public String toString() {
        return super.toString() + "\nВремя выполнения: " + getNextDeadline() + '\n';
    }
}
