import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Seminar4_Lesson {
    public static void main(String[] args) {
        task0();
        task1();
        task2();

        Integer[] array = {69,1,2,3,5,7,2,4,8,5,3};
        task3(array);
        
        task4();
        
        String infixExpression = "a+b*c-(d/e+f*g*h)";
        String postfixExpression = task5(infixExpression);
        System.out.println("Infix Expression: " + infixExpression);
        System.out.println("Postfix Expression: " + postfixExpression);
    }

    static void task0() {
        // 1) Замерьте время, за которое в ArrayList добавятся 10000 элементов.
        // 2) Замерьте время, за которое в LinkedList добавятся 10000 элементов.
        // Сравните с предыдущим.
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new LinkedList<>();

        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            list1.add(0, i);
        }

        long middle = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            list2.add(0, i);
        }
        long finish = System.currentTimeMillis();

        System.out.println(middle - start);
        System.out.println(finish - middle);
    }

    static void task1() {
        // Реализовать консольное приложение, которое:
        // 1. Принимает от пользователя строку вида
        // text~num
        // 2. Нужно рассплитить строку по ~, сохранить text в связный список на
        // позицию num.
        // 3. Если введено print~num, выводит строку из позиции num в связном
        // списке и удаляет её из списка.

        LinkedList<String> list = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);
        boolean work = true;
        while (work) {
            System.out.println("Input command: ");
            String line = scanner.nextLine();
            String[] array = line.split("~");
            String text = array[0];
            int num = Integer.parseInt(array[1]);
            switch (text) {
                case "print":
                    System.out.println(list.remove(num));
                    break;
                case "exit":
                    System.out.println("goodby");
                    work = false;
                    break;
                default:
                    list.add(num, text);
            }
        }
        scanner.close();
    }

    static void task2() {
        // Реализовать консольное приложение, которое:
        // 1. Принимает от пользователя и “запоминает” строки.
        // 2. Если введено print, выводит строки так, чтобы последняя введенная
        // была первой в списке, а первая - последней.
        // 3. Если введено revert, удаляет предыдущую введенную строку из памяти.

        LinkedList<String> list = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);
        boolean work = true;
        while (work) {
            System.out.println("Input text: ");
            String line = scanner.nextLine();
            switch (line) {
                case "print":
                    Collections.reverse(list);
                    System.out.println(list);
                    Collections.reverse(list);
                    break;
                case "revert":
                    if (!list.isEmpty()) {
                        list.removeLast();
                        System.out.println(list);
                    } else {
                        System.out.println("list is empty");
                    }
                    break;
                case "exit":
                    System.out.println("goodby");
                    work = false;
                    break;
                default:
                    list.add(line);
            }
        }
        scanner.close();
    }

    static void task3(Integer[] array) {
        // 1) Написать метод, который принимает массив элементов, помещает их в стэк
        // и выводит на консоль содержимое стэка.
        // 2) Написать метод, который принимает массив элементов, помещает их в
        // очередь и выводит на консоль содержимое очереди.
        Queue<Integer> queue = new LinkedList<>(Arrays.asList(array));
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < array.length; i++) {
            stack.add(array[i]);
        }
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
        System.out.println();
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }

    static void task4() {
        // Реализовать стэк с помощью массива.
        // Нужно реализовать методы:
        // size(), empty(), push(), peek(), pop().
        MyStack myStack = new MyStack();
        myStack.push(12);
        myStack.push(11);
        myStack.push(122);
        myStack.push(18);
        myStack.push(15);
        System.out.println(myStack.size());
        System.out.println(myStack.pop());
        System.out.println(myStack.peek());
        myStack.pop();
        myStack.pop();
        myStack.pop();
        myStack.pop();
        System.out.println(myStack.empty());
    }

    static String task5(String expression) {
        // Реализовать алгоритм перевода из инфиксной записи в постфиксную для
        // арифметического выражения.
        // http://primat.org/news/obratnaja_polskaja_zapis/2016-04-09-1181
        // Вычислить запись если это возможно
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (char c : expression.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                result.append(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop());
                }
                stack.pop();
            } else {
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    result.append(stack.pop());
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        return result.toString();
    }

    static int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return -1;
        }
    }
}
