import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var notebook = new TaskBook<>();
        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            inputTask(scanner, notebook);
                            break;
                        case 2:
                            deleteTask(scanner, notebook);
                            break;
                        case 3:
                            getTasksForDay(scanner, notebook);
                            break;
                        case 4:
                            notebook.printRemovedTasks();
                            break;
                        case 5:
                            changeName(scanner, notebook);
                            break;
                        case 6:
                            changeDescription(scanner, notebook);
                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void changeName(Scanner scanner, TaskBook notebook) throws Exception {
        notebook.changeTaskHeader(requestId(scanner), createName(scanner));
    }

    public static void changeDescription(Scanner scanner, TaskBook notebook) throws Exception {
        notebook.changeTaskDescription(requestId(scanner), createDescription(scanner));
    }

    public static int requestId(Scanner scanner) throws Exception {
        System.out.println("Введите ID задачи, которую хотите удалить");
        int id;
        if (scanner.hasNextInt()) {
            id = scanner.nextInt();
        } else {
            throw new Exception("Некорректный ID");
        }
        return id;
    }

    public static void getTasksForDay(Scanner scanner, TaskBook notebook) throws Exception {
        System.out.println("Введите дату:");
        notebook.printTodoListForDay(createDate(scanner));
    }

    public static void deleteTask(Scanner scanner, TaskBook notebook) throws Exception {
        notebook.deleteTask(requestId(scanner));
    }

    private static void inputTask(Scanner scanner, TaskBook notebook) throws Exception {
        System.out.print("Укажите повторяемость:\n1 - однократная\n2 - ежедневная\n3 - еженедельная\n" +
                "4 - ежемесячная\n5 - ежегодная\n");
        int repeatability;
        if (scanner.hasNextInt()) {
            repeatability = scanner.nextInt();
        } else {
            throw new Exception("Неверная повторяемость");
        }
        switch (repeatability) {
            case 1:
                var task = new SingleTask(createName(scanner), createDescription(scanner),
                        createDateTime(scanner), createType(scanner));
                notebook.addTask(task);
                break;
            case 2:
                var task1 = new DailyTask(createName(scanner), createDescription(scanner),
                        createDateTime(scanner), createType(scanner));
                notebook.addTask(task1);
                break;
            case 3:
                var task2 = new WeeklyTask(createName(scanner), createDescription(scanner),
                        createDateTime(scanner), createType(scanner));
                notebook.addTask(task2);
                break;
            case 4:
                var task3 = new MonthlyTask(createName(scanner), createDescription(scanner),
                        createDateTime(scanner), createType(scanner));
                notebook.addTask(task3);
                break;
            case 5:
                var task4 = new AnnualTask(createName(scanner), createDescription(scanner),
                        createDateTime(scanner), createType(scanner));
                notebook.addTask(task4);
                break;
            default:
                throw new Exception("Неверная повторяемость");
        }
    }

    public static String createName(Scanner scanner) throws Exception {
        scanner.nextLine();
        System.out.print("Введите название задачи: ");
        String taskName = scanner.nextLine();
        if (taskName == null || taskName.isBlank()) {
            throw new Exception("Пустое название задачи");
        }
        return taskName;
    }

    public static boolean createType(Scanner scanner) throws Exception {
        System.out.print("Выберите тип задачи:\n1 - личная\n2 - рабочая\n");
        int type;
        if (scanner.hasNextInt()) {
            type = scanner.nextInt();
        } else {
            throw new Exception("Неверно введен тип задачи");
        }
        if (type == 1) {
            return true;
        } else if (type == 2) {
            return false;
        } else throw new Exception("Неверно введен тип задачи");
    }

    public static String createDescription(Scanner scanner) throws Exception {
        scanner.nextLine();
        System.out.print("Введите описание задачи: ");
        String taskDescription = scanner.nextLine();
        if (taskDescription == null || taskDescription.isBlank()) {
            throw new Exception("Пустое описание задачи");
        }
        return taskDescription;
    }

    public static LocalDate createDate(Scanner scanner) throws Exception {
        System.out.println("год:");
        int year;
        if (scanner.hasNextInt()) {
            year = scanner.nextInt();
        } else {
            throw new Exception("Некорректные данные");
        }
        System.out.println("месяц:");
        int month;
        if (scanner.hasNextInt()) {
            month = scanner.nextInt();
        } else {
            throw new Exception("Некорректные данные");
        }
        System.out.println("день:");
        int day;
        if (scanner.hasNextInt()) {
            day = scanner.nextInt();
        } else {
            throw new Exception("Некорректные данные");
        }
        return LocalDate.of(year, month, day);
    }

    public static LocalDateTime createDateTime(Scanner scanner) throws Exception {
        System.out.print("Введите время и дату выполнения:\nчасы:");
        int hours;
        if (scanner.hasNextInt()) {
            hours = scanner.nextInt();
        } else {
            throw new Exception("Некорректные данные");
        }
        System.out.println("минуты:");
        int minutes;
        if (scanner.hasNextInt()) {
            minutes = scanner.nextInt();
        } else {
            throw new Exception("Некорректные данные");
        }
        LocalDateTime deadLine = null;
        try {
            deadLine = LocalDateTime.of(createDate(scanner), LocalTime.of(hours, minutes));
        } catch (DateTimeException e) {
            throw new Exception("Неправильно введены время/дата");
        }
        return deadLine;
    }

    private static void printMenu() {
        System.out.println(
                "1. Добавить задачу\n2. Удалить задачу\n3. Получить задачу на указанный день\n" +
                        "4. Получить список удаленных задач\n5. Редактировать заголовок\n" +
                        "6. Редактировать описание\n0. Выход ");
    }
}