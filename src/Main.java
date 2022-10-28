import java.time.DateTimeException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var notebook = new Dispatcher();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                label:
                while (true) {
                    printMenu();
                    System.out.print("Выберите пункт меню: ");
                    int menu = Integer.parseInt(scanner.nextLine());
                    switch (menu) {
                        case 1:
                            notebook.inputTask(scanner);
                            break;
                        case 2:
                            notebook.deleteTask(scanner);
                            break;
                        case 3:
                            notebook.getTasksForDay(scanner);
                            break;
                        case 4:
                            notebook.printRemovedTasks();
                            break;
                        case 5:
                            notebook.changeName(scanner);
                            break;
                        case 6:
                            notebook.changeDescription(scanner);
                            break;
                        case 0:
                            break label;
                    }
                }
            } catch (DateTimeException dte) {
                System.out.println("Введите дату и время в правильном формате чч:мм гггг-мм-дд");
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println(e.getMessage());
            }
        }
    }

    private static void printMenu() {
        System.out.println(
                "1. Добавить задачу\n2. Удалить задачу\n3. Получить задачу на указанный день\n" +
                        "4. Получить список удаленных задач\n5. Редактировать заголовок\n" +
                        "6. Редактировать описание\n0. Выход ");
    }
}