public class MyStack {
    
    private int[] array = new int[10];
    private int size = 0;

    int size() {
        return size;
    }

    boolean empty() {
        return size == 0;
    }

    void push(int item) {
        if (size == array.length) {
            int[] tempArray = new int[array.length * 2];
            System.arraycopy(array, 0, tempArray, 0, array.length);
            array = tempArray;
        }
        array[size++] = item;
    }

    int peek() {
        return array[size - 1];
    }

    int pop() {
        return array[--size];
    }
}
