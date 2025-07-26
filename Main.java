import java.util.Scanner;

/**
 * Main class untuk menjalankan program Team Assignment 3
 * Menyediakan menu untuk memilih antara Case 1 dan Case 2
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("==========================================");
        System.out.println("    TEAM ASSIGNMENT 3 - DATA STRUCTURES");
        System.out.println("==========================================");
        System.out.println();

        while (true) {
            System.out.println("Pilih case yang ingin dijalankan:");
            System.out.println("1. Case 1 - Konversi dan Evaluasi Postfix/Prefix");
            System.out.println("2. Case 2 - Menghitung Jumlah Item dalam Antrian");
            System.out.println("3. Keluar");
            System.out.print("Pilihan Anda (1-3): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.println("\n" + "=".repeat(50));
                    System.out.println("Menjalankan Case 1...");
                    System.out.println("=".repeat(50));
                    runCase1();
                    break;
                case 2:
                    System.out.println("\n" + "=".repeat(50));
                    System.out.println("Menjalankan Case 2...");
                    System.out.println("=".repeat(50));
                    runCase2();
                    break;
                case 3:
                    System.out.println("Terima kasih telah menggunakan program ini!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Pilihan tidak valid! Silakan pilih 1, 2, atau 3.");
            }

            System.out.println("\n" + "=".repeat(50));
            System.out.println("Kembali ke menu utama...");
            System.out.println("=".repeat(50) + "\n");
        }
    }

    /**
     * Method untuk menjalankan Case 1
     */
    private static void runCase1() {
        try {
            // Menggunakan reflection untuk menjalankan Case1Runner
            Class<?> case1Class = Class.forName("Case1Runner");
            case1Class.getMethod("main", String[].class).invoke(null, (Object) new String[0]);
        } catch (Exception e) {
            System.err.println("Error menjalankan Case 1: " + e.getMessage());
            System.out.println("Pastikan file Case1Runner.java sudah di-compile.");
        }
    }

    /**
     * Method untuk menjalankan Case 2
     */
    private static void runCase2() {
        try {
            // Menggunakan reflection untuk menjalankan Case2Runner
            Class<?> case2Class = Class.forName("Case2Runner");
            case2Class.getMethod("main", String[].class).invoke(null, (Object) new String[0]);
        } catch (Exception e) {
            System.err.println("Error menjalankan Case 2: " + e.getMessage());
            System.out.println("Pastikan file Case2Runner.java sudah di-compile.");
        }
    }
}