/**
 * Implementasi Queue menggunakan Array.
 * Menggunakan konsep circular array untuk efisiensi memori.
 */
public class ArrayQueue<T> implements IQueue<T> {
    private T[] array;
    private int front;
    private int rear;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    @SuppressWarnings("unchecked")
    public ArrayQueue() {
        this.array = (T[]) new Object[DEFAULT_CAPACITY];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    @SuppressWarnings("unchecked")
    public ArrayQueue(int capacity) {
        this.array = (T[]) new Object[capacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    @Override
    public void enqueue(T item) {
        if (size == array.length) {
            resize();
        }
        rear = (rear + 1) % array.length;
        array[rear] = item;
        size++;
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue kosong");
        }
        T item = array[front];
        array[front] = null;
        front = (front + 1) % array.length;
        size--;
        return item;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue kosong");
        }
        return array[front];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        T[] newArray = (T[]) new Object[array.length * 2];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[(front + i) % array.length];
        }
        array = newArray;
        front = 0;
        rear = size - 1;
    }

    /**
     * Menampilkan semua elemen dalam queue.
     */
    public void display() {
        if (isEmpty()) {
            System.out.println("Queue kosong");
            return;
        }
        System.out.print("Queue: ");
        for (int i = 0; i < size; i++) {
            System.out.print(array[(front + i) % array.length] + " ");
        }
        System.out.println();
    }
}