
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class TaskBook<T extends Task & Repeatable> {
    private final Map<Integer, T> book = new HashMap<>();


    public void addTask(T task) {
        book.put(task.getId(), task);
    }

    public void deleteTask(int id) {
        book.get(id).setRemote(true);
    }

    public void changeTaskHeader(int id, String s) {
        book.get(id).setHeader(s);
    }
    public void changeTaskDescription(int id, String s) {
        book.get(id).setDescription(s);
    }
    public void printTodoListForDay(LocalDate date) {
        for (T value : book.values()) {
            if (value.getNextDeadline() != null && value.getNextDeadline().toLocalDate().equals(date) && !value.getRemote()) {
                System.out.println(value);
            }
        }
    }

    public void printRemovedTasks() {
        for (T value : book.values()) {
            if (value.getRemote()) {
                System.out.println(value);
            }
        }
    }
}
