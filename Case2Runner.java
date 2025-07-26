import java.util.Scanner;

/**
 * Runner class untuk Case 2: Menghitung Jumlah Item dalam Antrian
 * Menggunakan konsep Queue dengan implementasi Array dan Linked List
 */
public class Case2Runner {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== SOAL CASE 2: MENGHITUNG JUMLAH ITEM DALAM ANTRIAN ===");

        // Demo dengan Array Queue
        System.out.println("\n--- Demo Array Queue ---");
        ArrayQueue<String> arrayQueue = new ArrayQueue<>();

        // Menambahkan beberapa item ke queue
        String[] items = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
        for (String item : items) {
            arrayQueue.enqueue(item);
            System.out.println("Menambahkan: " + item);
        }

        arrayQueue.display();
        System.out.println("Jumlah item dalam Array Queue: " + arrayQueue.size());

        // Menghapus beberapa item
        System.out.println("\nMenghapus item dari depan:");
        for (int i = 0; i < 2; i++) {
            String removed = arrayQueue.dequeue();
            System.out.println("Dihapus: " + removed);
        }

        arrayQueue.display();
        System.out.println("Jumlah item dalam Array Queue setelah penghapusan: " + arrayQueue.size());

        // Demo dengan Linked List Queue
        System.out.println("\n--- Demo Linked List Queue ---");
        LinkedListQueue<Integer> linkedListQueue = new LinkedListQueue<>();

        // Menambahkan beberapa angka ke queue
        int[] numbers = { 10, 20, 30, 40, 50, 60 };
        for (int number : numbers) {
            linkedListQueue.enqueue(number);
            System.out.println("Menambahkan: " + number);
        }

        linkedListQueue.display();
        System.out.println("Jumlah item dalam Linked List Queue: " + linkedListQueue.size());

        // Menghapus beberapa item
        System.out.println("\nMenghapus item dari depan:");
        for (int i = 0; i < 3; i++) {
            Integer removed = linkedListQueue.dequeue();
            System.out.println("Dihapus: " + removed);
        }

        linkedListQueue.display();
        System.out.println("Jumlah item dalam Linked List Queue setelah penghapusan: " + linkedListQueue.size());

        // Demo interaktif
        System.out.println("\n--- Demo Interaktif Queue ---");
        System.out.println("Pilih implementasi queue:");
        System.out.println("1. Array Queue");
        System.out.println("2. Linked List Queue");
        System.out.print("Pilihan Anda (1/2): ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        IQueue<String> interactiveQueue;
        if (choice == 1) {
            interactiveQueue = new ArrayQueue<>();
            System.out.println("Menggunakan Array Queue");
        } else {
            interactiveQueue = new LinkedListQueue<>();
            System.out.println("Menggunakan Linked List Queue");
        }

        while (true) {
            System.out.println("\nOperasi Queue:");
            System.out.println("1. Tambah item (enqueue)");
            System.out.println("2. Hapus item (dequeue)");
            System.out.println("3. Lihat item depan (peek)");
            System.out.println("4. Tampilkan jumlah item");
            System.out.println("5. Tampilkan semua item");
            System.out.println("6. Keluar");
            System.out.print("Pilihan Anda (1-6): ");

            int operation = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (operation) {
                case 1:
                    System.out.print("Masukkan item: ");
                    String item = scanner.nextLine();
                    interactiveQueue.enqueue(item);
                    System.out.println("Item '" + item + "' ditambahkan");
                    break;
                case 2:
                    try {
                        String removed = interactiveQueue.dequeue();
                        System.out.println("Item '" + removed + "' dihapus");
                    } catch (IllegalStateException e) {
                        System.out.println("Queue kosong, tidak ada item yang bisa dihapus");
                    }
                    break;
                case 3:
                    try {
                        String front = interactiveQueue.peek();
                        System.out.println("Item di depan: " + front);
                    } catch (IllegalStateException e) {
                        System.out.println("Queue kosong");
                    }
                    break;
                case 4:
                    System.out.println("Jumlah item dalam queue: " + interactiveQueue.size());
                    break;
                case 5:
                    if (interactiveQueue instanceof ArrayQueue) {
                        ((ArrayQueue<String>) interactiveQueue).display();
                    } else if (interactiveQueue instanceof LinkedListQueue) {
                        ((LinkedListQueue<String>) interactiveQueue).display();
                    }
                    break;
                case 6:
                    System.out.println("Terima kasih telah menggunakan program ini!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }
}