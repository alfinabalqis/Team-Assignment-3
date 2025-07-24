/**
 * Implementasi Stack menggunakan Array.
 * Mengimplementasikan interface IStack.
 * Ukuran array bersifat tetap (fixed size).
 */
public class ArrayStack<T> implements IStack<T> {
    private T[] stackArray;  // Array untuk menyimpan elemen stack
    private int top;         // Indeks elemen teratas stack
    private int capacity;    // Kapasitas maksimum stack

    /**
     * Konstruktor untuk ArrayStack.
     *
     * @param capacity Kapasitas maksimum stack yang akan dibuat.
     */
    @SuppressWarnings("unchecked")
    public ArrayStack(int capacity) {
        this.capacity = capacity;
        // Membuat array generik (perlu suppress warnings karena Java generik)
        this.stackArray = (T[]) new Object[capacity];
        this.top = -1; // -1 menunjukkan stack kosong
    }

    /**
     * Menambahkan elemen ke bagian atas stack.
     * Kompleksitas waktu: O(1)
     *
     * @param item Elemen yang akan ditambahkan.
     * @throws IllegalStateException jika stack penuh.
     */
    @Override
    public void push(T item) {
        if (size() == capacity) {
            throw new IllegalStateException("Stack penuh, tidak bisa push elemen.");
        }
        top++; // Pindahkan indeks top ke atas
        stackArray[top] = item; // Masukkan elemen
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
        T data = stackArray[top]; // Ambil data dari top
        stackArray[top] = null; // Bantu garbage collection (opsional)
        top--; // Pindahkan indeks top ke bawah
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
        return stackArray[top];
    }

    /**
     * Memeriksa apakah stack kosong.
     * Kompleksitas waktu: O(1)
     *
     * @return true jika stack kosong, false jika tidak.
     */
    @Override
    public boolean isEmpty() {
        return top == -1; // Jika top adalah -1, stack kosong
    }

    /**
     * Mengembalikan jumlah elemen dalam stack.
     * Kompleksitas waktu: O(1)
     *
     * @return Jumlah elemen dalam stack.
     */
    @Override
    public int size() {
        return top + 1; // Jumlah elemen adalah indeks top + 1
    }
}