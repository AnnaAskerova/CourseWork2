
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class TaskBook<T extends Task & Repeatable> {
    private final Map<Integer, T> book = new HashMap<>();
    private final Map<Integer, T> removedTasks = new HashMap<>();


    public void addTask(T task) {
        book.put(task.getId(), task);
    }

    public void deleteTask(int id) {
        removedTasks.put(id, book.get(id));
        book.remove(id);
    }

    public void changeTaskHeader(int id, String s) {
        book.get(id).setHeader(s);
    }

    public void changeTaskDescription(int id, String s) {
        book.get(id).setDescription(s);
    }

    public void printTodoListForDay(LocalDate date) {
        for (T value : book.values()) {
            if (value.getNextDeadline() != null && value.getNextDeadline().toLocalDate().equals(date)) {
                System.out.println(value);
            }
        }
    }

    public void printRemovedTasks() {
        for (T value : removedTasks.values()) {
            System.out.println(value);
        }
    }
}
