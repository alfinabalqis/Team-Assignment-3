/**
 * Implementasi Stack menggunakan Linked List.
 * Mengimplementasikan interface IStack.
 * Ukuran bersifat dinamis.
 */
public class LinkedListStack<T> implements IStack<T> {
    private Node<T> top; // Pointer ke elemen paling atas stack
    private int size;    // Ukuran stack

    /**
     * Konstruktor untuk LinkedListStack.
     * Menginisialisasi stack kosong.
     */
    public LinkedListStack() {
        this.top = null;
        this.size = 0;
    }

    /**
     * Menambahkan elemen ke bagian atas stack.
     * Kompleksitas waktu: O(1)
     *
     * @param item Elemen yang akan ditambahkan.
     */
    @Override
    public void push(T item) {
        Node<T> newNode = new Node<>(item); // Buat node baru
        newNode.next = top; // Node baru menunjuk ke node sebelumnya di atas (top lama)
        top = newNode;      // Node baru menjadi top
        size++;
    }

    /**
     * Menghapus dan mengembalikan elemen dari bagian atas stack.
     * Kompleksitas waktu: O(1)
     *
     * @return Elemen di bagian atas stack.
     * @throws IllegalStateException jika stack kosong.
     */
    @Override
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack kosong, tidak bisa pop elemen.");
        }
        T data = top.data; // Ambil data dari top
        top = top.next;    // Pindahkan top ke node berikutnya
        size--;
        return data;
    }

    /**
     * Mengembalikan elemen di bagian atas stack tanpa menghapusnya.
     * Kompleksitas waktu: O(1)
     *
     * @return Elemen di bagian atas stack.
     * @throws IllegalStateException jika stack kosong.
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack kosong, tidak bisa peek elemen.");
        }
        return top.data;
    }

    /**
     * Memeriksa apakah stack kosong.
     * Kompleksitas waktu: O(1)
     *
     * @return true jika stack kosong, false jika tidak.
     */
    @Override
    public boolean isEmpty() {
        return top == null; // Stack kosong jika top adalah null
    }

    /**
     * Mengembalikan jumlah elemen dalam stack.
     * Kompleksitas waktu: O(1)
     *
     * @return Jumlah elemen dalam stack.
     */
    @Override
    public int size() {
        return size;
    }
}