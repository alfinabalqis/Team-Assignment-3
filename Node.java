/**
 * Kelas pembantu untuk merepresentasikan sebuah node dalam linked list.
 * Digunakan sebagai building block untuk implementasi stack berbasis linked list.
 */
class Node<T> {
    T data;         // Data yang disimpan dalam node
    Node<T> next;   // Pointer ke node berikutnya dalam linked list

    /**
     * Konstruktor untuk membuat node baru.
     *
     * @param data Data yang akan disimpan dalam node.
     */
    public Node(T data) {
        this.data = data;
        this.next = null; // Awalnya, node tidak menunjuk ke mana-mana
    }
}