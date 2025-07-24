/**
 * Interface untuk ADT (Abstract Data Type) Stack.
 * Mendefinisikan operasi-operasi dasar yang harus dimiliki sebuah stack.
 */
public interface IStack<T> {
    /**
     * Menambahkan elemen ke bagian atas stack.
     *
     * @param item Elemen yang akan ditambahkan.
     */
    void push(T item);

    /**
     * Menghapus dan mengembalikan elemen dari bagian atas stack.
     *
     * @return Elemen di bagian atas stack.
     * @throws IllegalStateException jika stack kosong.
     */
    T pop();

    /**
     * Mengembalikan elemen di bagian atas stack tanpa menghapusnya.
     *
     * @return Elemen di bagian atas stack.
     * @throws IllegalStateException jika stack kosong.
     */
    T peek();

    /**
     * Memeriksa apakah stack kosong.
     *
     * @return true jika stack kosong, false jika tidak.
     */
    boolean isEmpty();

    /**
     * Mengembalikan jumlah elemen dalam stack.
     *
     * @return Jumlah elemen dalam stack.
     */
    int size();
}