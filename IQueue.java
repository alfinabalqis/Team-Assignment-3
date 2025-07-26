/**
 * Interface untuk ADT (Abstract Data Type) Queue.
 * Mendefinisikan operasi-operasi dasar yang harus dimiliki sebuah queue.
 */
public interface IQueue<T> {
    /**
     * Menambahkan elemen ke bagian belakang queue.
     *
     * @param item Elemen yang akan ditambahkan.
     */
    void enqueue(T item);

    /**
     * Menghapus dan mengembalikan elemen dari bagian depan queue.
     *
     * @return Elemen di bagian depan queue.
     * @throws IllegalStateException jika queue kosong.
     */
    T dequeue();

    /**
     * Mengembalikan elemen di bagian depan queue tanpa menghapusnya.
     *
     * @return Elemen di bagian depan queue.
     * @throws IllegalStateException jika queue kosong.
     */
    T peek();

    /**
     * Memeriksa apakah queue kosong.
     *
     * @return true jika queue kosong, false jika tidak.
     */
    boolean isEmpty();

    /**
     * Mengembalikan jumlah elemen dalam queue.
     *
     * @return Jumlah elemen dalam queue.
     */
    int size();
}