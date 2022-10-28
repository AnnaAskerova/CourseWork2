import java.time.LocalDateTime;

public class AnnualTask extends Task implements Repeatable{
    public AnnualTask(String header, String description, LocalDateTime deadline, Boolean isPersonalTask) {
        super(header, description, deadline, isPersonalTask);
    }

    @Override
    public LocalDateTime getNextDeadline() {
        LocalDateTime  deadline = getDeadline();
        while (LocalDateTime.now().isAfter(deadline)) {
            deadline = deadline.plusYears(1);
        }
        return deadline;
    }

    @Override
    public String toString() {
        return super.toString() + "\nБлижайшее актуальное время выполнения: " + getNextDeadline() + '\n';
    }
}
