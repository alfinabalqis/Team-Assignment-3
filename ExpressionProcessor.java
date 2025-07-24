import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Kelas ini menyediakan fungsionalitas untuk mengonversi ekspresi infix ke postfix dan prefix,
 * serta mengevaluasi ekspresi postfix dan prefix. Ini menggunakan implementasi Stack
 * (secara internal akan menggunakan LinkedListStack untuk fleksibilitas).
 */
public class ExpressionProcessor {

    // Peta untuk menyimpan prioritas operator
    // Prioritas lebih tinggi berarti dieksekusi lebih dulu.
    private static final Map<Character, Integer> PRECEDENCE = new HashMap<>();
    static {
        PRECEDENCE.put('+', 1);
        PRECEDENCE.put('-', 1);
        PRECEDENCE.put('*', 2);
        PRECEDENCE.put('/', 2);
        PRECEDENCE.put('^', 3); // Operator pangkat (jika ada)
    }

    /**
     * Mengembalikan prioritas operator.
     *
     * @param op Operator karakter.
     * @return Prioritas operator (integer), 0 jika bukan operator yang dikenal.
     */
    private static int getPrecedence(char op) {
        return PRECEDENCE.getOrDefault(op, 0);
    }

    /**
     * Memeriksa apakah karakter adalah operator aritmatika yang dikenal.
     *
     * @param c Karakter yang akan diperiksa.
     * @return true jika karakter adalah operator, false jika tidak.
     */
    private static boolean isOperator(char c) {
        return PRECEDENCE.containsKey(c);
    }

    /**
     * Melakukan validasi notasi infix.
     * Ini adalah validasi dasar untuk memenuhi persyaratan soal.
     *
     * @param infix Notasi infix yang akan divalidasi.
     * @return true jika notasi valid, false jika tidak.
     */
    public static boolean isValidInfix(String infix) {
        String cleanInfix = infix.replaceAll("\\s+", ""); // Hapus spasi untuk mempermudah validasi

        if (cleanInfix.isEmpty()) {
            return false;
        }

        // 1. Cek keseimbangan kurung menggunakan Stack
        IStack<Character> parenStack = new LinkedListStack<>(); // Menggunakan LinkedListStack
        for (char c : cleanInfix.toCharArray()) {
            if (c == '(') {
                parenStack.push(c);
            } else if (c == ')') {
                if (parenStack.isEmpty()) {
                    return false; // Kurung tutup tanpa kurung buka yang sesuai
                }
                parenStack.pop();
            }
        }
        if (!parenStack.isEmpty()) {
            return false; // Kurung buka yang tidak ditutup
        }

        // 2. Cek urutan operator dan operand
        // Tidak boleh ada dua operator berurutan (misal: "5 * + 4")
        // Tidak boleh ada dua operand berurutan (misal: "5 4 + 3")
        // Ekspresi tidak boleh dimulai/diakhiri dengan operator (kecuali kurung)
        for (int i = 0; i < cleanInfix.length(); i++) {
            char current = cleanInfix.charAt(i);

            // Cek karakter tidak dikenal
            if (!Character.isDigit(current) && !isOperator(current) && current != '(' && current != ')') {
                return false; // Karakter selain digit, operator, atau kurung
            }

            if (isOperator(current)) {
                // Operator tidak boleh di awal atau akhir ekspresi (kecuali kurung)
                if (i == 0 || i == cleanInfix.length() - 1) {
                    if (current != '(' && current != ')') {
                        return false;
                    }
                }
                // Cek dua operator berurutan (contoh: 5 * / 4 atau 5 + - 4)
                if (i + 1 < cleanInfix.length()) {
                    char next = cleanInfix.charAt(i + 1);
                    if (isOperator(next) && next != '(' && next != ')') { // Menghindari 5*(3) yang valid
                        return false;
                    }
                }
            } else if (Character.isDigit(current)) {
                // Cek dua operand berurutan (contoh: 5 4 +)
                if (i + 1 < cleanInfix.length()) {
                    char next = cleanInfix.charAt(i + 1);
                    if (Character.isDigit(next)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Mengonversi notasi infix ke postfix menggunakan algoritma Shunting-Yard.
     * Memisahkan angka multi-digit dengan spasi di hasil postfix.
     *
     * @param infix Notasi infix yang akan dikonversi.
     * @return Notasi postfix sebagai String.
     */
    public static String infixToPostfix(String infix) {
        IStack<Character> stack = new LinkedListStack<>(); // Menggunakan LinkedListStack
        StringBuilder postfix = new StringBuilder();

        // Regex untuk mengenali angka (satu atau multi-digit) atau operator/kurung.
        Pattern p = Pattern.compile("(\\d+)|([+\\-*/^()])");
        Matcher m = p.matcher(infix);

        while (m.find()) {
            String token = m.group();
            if (token.matches("\\d+")) { // Jika token adalah operand (angka)
                postfix.append(token).append(" "); // Tambahkan spasi untuk memisahkan angka
            } else { // Jika token adalah operator atau kurung
                char c = token.charAt(0); // Ambil karakter operator/kurung
                if (c == '(') {
                    stack.push(c);
                } else if (c == ')') {
                    while (!stack.isEmpty() && stack.peek() != '(') {
                        postfix.append(stack.pop()).append(" ");
                    }
                    if (!stack.isEmpty() && stack.peek() == '(') {
                        stack.pop(); // Pop '(' dari stack
                    }
                } else if (isOperator(c)) {
                    // Pop operator dari stack jika prioritasnya lebih tinggi atau sama
                    while (!stack.isEmpty() && stack.peek() != '(' && getPrecedence(stack.peek()) >= getPrecedence(c)) {
                        postfix.append(stack.pop()).append(" ");
                    }
                    stack.push(c); // Push operator saat ini
                }
            }
        }

        // Pop sisa operator dari stack ke postfix
        while (!stack.isEmpty()) {
            postfix.append(stack.pop()).append(" ");
        }

        return postfix.toString().trim(); // Hapus spasi di akhir jika ada
    }

    /**
     * Mengonversi notasi infix ke prefix.
     * Algoritma:
     * 1. Balik string infix.
     * 2. Ubah '(' menjadi ')' dan sebaliknya.
     * 3. Konversi string yang dibalik dan dimodifikasi ke postfix (gunakan infixToPostfix).
     * 4. Balik hasil postfix untuk mendapatkan prefix.
     *
     * @param infix Notasi infix yang akan dikonversi.
     * @return Notasi prefix sebagai String.
     */
    public static String infixToPrefix(String infix) {
        StringBuilder reversedInfix = new StringBuilder(infix).reverse(); // Balik string infix
        // Ubah kurung
        for (int i = 0; i < reversedInfix.length(); i++) {
            char c = reversedInfix.charAt(i);
            if (c == '(') {
                reversedInfix.setCharAt(i, ')');
            } else if (c == ')') {
                reversedInfix.setCharAt(i, '(');
            }
        }

        // Konversi string yang dibalik dan dimodifikasi ke postfix
        String postfixOfReversed = infixToPostfix(reversedInfix.toString());

        // Balik hasil postfixnya untuk mendapatkan prefix
        // Perlu memisahkan token terlebih dahulu sebelum dibalik
        String[] tokens = postfixOfReversed.split("\\s+"); // Pisahkan token berdasarkan spasi
        StringBuilder prefix = new StringBuilder();
        for (int i = tokens.length - 1; i >= 0; i--) { // Iterasi dari belakang ke depan
            prefix.append(tokens[i]).append(" ");
        }
        return prefix.toString().trim();
    }

    /**
     * Mengevaluasi ekspresi postfix.
     *
     * @param postfix Notasi postfix yang akan dievaluasi.
     * @return Hasil evaluasi sebagai double.
     * @throws IllegalArgumentException jika ekspresi tidak valid atau terjadi pembagian dengan nol.
     */
    public static double evaluatePostfix(String postfix) {
        IStack<Double> stack = new LinkedListStack<>(); // Menggunakan LinkedListStack

        // Tokenisasi string postfix berdasarkan spasi
        String[] tokens = postfix.split("\\s+");

        for (String token : tokens) {
            if (token.matches("-?\\d+(\\.\\d+)?")) { // Jika operand (angka, termasuk negatif dan desimal)
                stack.push(Double.parseDouble(token));
            } else if (token.length() == 1 && isOperator(token.charAt(0))) { // Jika operator
                if (stack.size() < 2) {
                    throw new IllegalArgumentException("Ekspresi postfix tidak valid: kurang operand untuk operator " + token);
                }
                double operand2 = stack.pop(); // Pop operand kedua dulu (karena LIFO)
                double operand1 = stack.pop(); // Pop operand pertama

                double result = applyOperator(operand1, operand2, token.charAt(0));
                stack.push(result);
            } else {
                throw new IllegalArgumentException("Karakter/token tidak dikenal dalam postfix: " + token);
            }
        }

        if (stack.size() != 1) {
            throw new IllegalArgumentException("Ekspresi postfix tidak valid: terlalu banyak atau terlalu sedikit operand.");
        }
        return stack.pop();
    }

    /**
     * Mengevaluasi ekspresi prefix.
     *
     * @param prefix Notasi prefix yang akan dievaluasi.
     * @return Hasil evaluasi sebagai double.
     * @throws IllegalArgumentException jika ekspresi tidak valid atau terjadi pembagian dengan nol.
     */
    public static double evaluatePrefix(String prefix) {
        IStack<Double> stack = new LinkedListStack<>(); // Menggunakan LinkedListStack

        // Tokenisasi string prefix dan balik urutan token agar bisa diproses seperti postfix
        String[] tokens = prefix.split("\\s+");
        // Loop dari belakang ke depan untuk memproses token
        for (int i = tokens.length - 1; i >= 0; i--) {
            String token = tokens[i];
            if (token.matches("-?\\d+(\\.\\d+)?")) { // Jika operand (angka)
                stack.push(Double.parseDouble(token));
            } else if (token.length() == 1 && isOperator(token.charAt(0))) { // Jika operator
                if (stack.size() < 2) {
                    throw new IllegalArgumentException("Ekspresi prefix tidak valid: kurang operand untuk operator " + token);
                }
                // Urutan operand terbalik untuk prefix saat iterasi dari kanan ke kiri
                double operand1 = stack.pop(); // Ini akan menjadi operand kedua dalam operasi
                double operand2 = stack.pop(); // Ini akan menjadi operand pertama dalam operasi

                // Operator diterapkan pada operand2 (pertama) dan operand1 (kedua)
                double result = applyOperator(operand1, operand2, token.charAt(0)); // Urutan penting!
                stack.push(result);
            } else {
                throw new IllegalArgumentException("Karakter/token tidak dikenal dalam prefix: " + token);
            }
        }

        if (stack.size() != 1) {
            throw new IllegalArgumentException("Ekspresi prefix tidak valid: terlalu banyak atau terlalu sedikit operand.");
        }
        return stack.pop();
    }

    /**
     * Melakukan operasi aritmatika berdasarkan operator.
     * @param a Operand pertama.
     * @param b Operand kedua.
     * @param operator Operator (+, -, *, /).
     * @return Hasil operasi.
     * @throws IllegalArgumentException jika operator tidak valid atau terjadi pembagian dengan nol.
     */
    private static double applyOperator(double a, double b, char operator) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) {
                    throw new IllegalArgumentException("Pembagian dengan nol tidak diizinkan.");
                }
                return a / b;
            case '^':
                return Math.pow(a, b);
            default:
                throw new IllegalArgumentException("Operator tidak valid: " + operator);
        }
    }
}