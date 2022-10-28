import java.time.LocalDateTime;

public class MonthlyTask extends Task implements Repeatable{
    public MonthlyTask(String header, String description, LocalDateTime deadline, Boolean isPersonalTask) {
        super(header, description, deadline, isPersonalTask);
    }

    @Override
    public LocalDateTime getNextDeadline() {
        LocalDateTime  deadline = getDeadline();
        while (LocalDateTime.now().isAfter(deadline)) {
            deadline = deadline.plusMonths(1);
        }
        return deadline;
    }
    @Override
    public String toString() {
        return super.toString() + "\nБлижайшее актуальное время выполнения: " + getNextDeadline() + '\n';
    }
}
