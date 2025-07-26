import java.util.Scanner;

/**
 * Runner class untuk Case 1: Konversi dan Evaluasi Postfix/Prefix
 * Menggunakan konsep Stack untuk konversi dan evaluasi ekspresi matematika
 */
public class Case1Runner {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== SOAL CASE 1: KONVERSI DAN EVALUASI POSTFIX/PREFIX ===");
        String infixExpression;
        boolean isValidInput = false;

        // 1. Inputan berupa notasi infix dengan validasi
        do {
            System.out.print("Masukkan notasi infix (contoh: 5 + 4 / 5): ");
            infixExpression = scanner.nextLine();
            isValidInput = ExpressionProcessor.isValidInfix(infixExpression); // Memvalidasi notasi infix

            if (!isValidInput) {
                System.out.println("======================================");
                System.out.println("Input tidak valid. Silakan coba lagi.");
                System.out.println("======================================");
            }
        } while (!isValidInput);

        // 2. Notasi yang sudah di input oleh user tadi di ubah ke notasi postfix dan
        // prefix dengan menggunakan konsep stack.
        String postfixExpression = ExpressionProcessor.infixToPostfix(infixExpression);
        String prefixExpression = ExpressionProcessor.infixToPrefix(infixExpression);

        System.out.println("\nNotasi Infix: " + infixExpression);
        System.out.println("Notasi Postfix: " + postfixExpression);
        System.out.println("Notasi Prefix: " + prefixExpression);

        // 3. Setelah diubah menjadi notasi postfix dan prefix, notasi tersebut dihitung
        // untuk mendapatkan hasil operasinya, menggunakan konsep stack.
        try {
            double postfixResult = ExpressionProcessor.evaluatePostfix(postfixExpression);
            double prefixResult = ExpressionProcessor.evaluatePrefix(prefixExpression);

            // 4. Tampilkan hasil operasi ke layer dengan Bahasa pemrograman java.
            System.out.println("Hasil operasi Postfix: " + postfixResult);
            System.out.println("Hasil operasi Prefix: " + prefixResult);
        } catch (IllegalArgumentException e) {
            System.err.println("Error saat mengevaluasi ekspresi: " + e.getMessage());
        }

        scanner.close();
    }
}