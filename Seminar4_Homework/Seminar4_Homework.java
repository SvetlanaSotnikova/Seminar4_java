import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class Seminar4_Homework {
    private static Deque<Integer> undoStack = new ArrayDeque<>();
    private static Deque<Integer> stack = new ArrayDeque<>();

    public static void main(String[] args) {
        System.out.println("result reverse: \n" + task0());
        
        task1();
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("input number 1: ");
        int number1 = scanner.nextInt();

        System.out.print("input operator: ");
        char operator = scanner.next().charAt(0);

        System.out.print("input number 2: ");
        int number2 = scanner.nextInt();

        System.out.println(task2(operator, number1, number2));

        scanner.close();
    }

    static LinkedList<Integer> task0() {
        // 1. Пусть дан LinkedList с несколькими элементами. Реализуйте метод, который
        // вернет “перевернутый” список.
        Scanner scanner = new Scanner(System.in);
        LinkedList<Integer> list = new LinkedList<>();
        System.out.print("Input size of your list: ");
        int size = scanner.nextInt();

        for (int i = 0; i < size; i++) {
            System.out.print("input element " + (i + 1) + ": ");
            list.add(scanner.nextInt());
        }
        System.out.println(list);

        Collections.reverse(list);
        scanner.close();

        return list;
    }

    static void task1() {
        // 2. Реализуйте очередь с помощью LinkedList со следующими методами:
        // enqueue() - помещает элемент в конец очереди, dequeue() - возвращает первый
        // элемент из очереди и удаляет его, first() - возвращает первый элемент из
        // очереди, не удаляя.
        LinkedList<Integer> queue = new LinkedList<>();

        // Помещаем элементы в конец очереди
        enqueue(queue, 1);
        enqueue(queue, 2);
        enqueue(queue, 3);

        // Выводим первый элемент
        System.out.println("First element: " + first(queue));

        // Извлекаем и выводим элементы из очереди
        while (!queue.isEmpty()) {
            System.out.println("Dequeued: " + dequeue(queue));
        }

    }

    static void enqueue(LinkedList<Integer> queue, int item) {
        queue.addLast(item);
    }

    static int dequeue(LinkedList<Integer> queue) {
        return queue.removeFirst();
    }

    static int first(LinkedList<Integer> queue) {
        return queue.getFirst();
    }

    static int task2(char op, int a, int b) {

        // 3. * В калькулятор добавьте возможность отменить последнюю операцию.

        int result;
        switch (op) {
            case '+':
                result = a + b;
                stack.push(result);
                break;
            case '-':
                result = a - b;
                stack.push(result);
                break;
            case '*':
                result = a * b;
                stack.push(result);
                break;
            case '/':
                result = a / b;
                stack.push(result);
                break;
            case '<':
                if (!stack.isEmpty()) {
                    undoStack.push(stack.pop());
                }
                return stack.isEmpty() ? 0 : stack.peek();
            default:
                throw new IllegalArgumentException("Неподдерживаемая операция: " + op);
        }
        undoStack.clear(); // Очищаем undoStack при выполнении новой операции

        return result;

    }
}
