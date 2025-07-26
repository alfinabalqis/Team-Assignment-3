# Team Assignment 3 - Data Structures Implementation

## 👥 Team:
| Nama                            | NIM        |
|---------------------------------|------------|
| Horas Marolop Amsal Siregar     | 2802608864 |
| Alfina Balqis Nurzaharani       | 2802582152 |
| Egi Nugraha                     | 2802597854 |

Program Java yang mengimplementasikan konsep Stack dan Queue dengan berbagai struktur data.

## Deskripsi Program

Program ini terdiri dari dua case utama:
1. **Case 1**: Konversi dan evaluasi notasi infix ke postfix dan prefix menggunakan Stack
2. **Case 2**: Implementasi Queue untuk menghitung jumlah item dalam antrian

## Struktur File

### Case 1: Stack Implementation
- `IStack.java` - Interface untuk Abstract Data Type Stack
- `ArrayStack.java` - Implementasi Stack menggunakan Array
- `LinkedListStack.java` - Implementasi Stack menggunakan Linked List
- `Node.java` - Class Node untuk Linked List
- `ExpressionProcessor.java` - Processor untuk konversi dan evaluasi ekspresi matematika

### Case 2: Queue Implementation
- `IQueue.java` - Interface untuk Abstract Data Type Queue
- `ArrayQueue.java` - Implementasi Queue menggunakan Array (Circular Array)
- `LinkedListQueue.java` - Implementasi Queue menggunakan Linked List

### Main Program
- `Main.java` - Program utama dengan menu untuk memilih case
- `Case1Runner.java` - Runner khusus untuk Case 1 (Postfix/Prefix)
- `Case2Runner.java` - Runner khusus untuk Case 2 (Queue Implementation)

## Cara Menjalankan Program

### Menjalankan Program Utama (Menu)
1. Compile semua file Java:
   ```bash
   javac *.java
   ```

2. Jalankan program utama:
   ```bash
   java Main
   ```
   Program akan menampilkan menu untuk memilih antara Case 1 dan Case 2.

### Menjalankan Case Secara Terpisah
1. **Case 1 - Postfix/Prefix:**
   ```bash
   java Case1Runner
   ```

2. **Case 2 - Queue Implementation:**
   ```bash
   java Case2Runner
   ```

## Detail Implementasi

### Case 1: Konversi dan Evaluasi Postfix/Prefix (Bobot 65%)

#### 1. Validasi Input Infix (Bobot 20%)
- User diminta memasukkan notasi infix
- Program memvalidasi bahwa input merupakan notasi infix yang valid
- Contoh valid: `5 + 4 / 5`
- Contoh tidak valid: `5 * 5 +`

#### 2. Konversi ke Postfix dan Prefix (Bobot 10%)
- Menggunakan konsep Stack untuk konversi
- Infix: `5 + 4 / 5` → Postfix: `545/+`
- Infix: `5 + 4 / 5` → Prefix: `+/455`

#### 3. Evaluasi Ekspresi (Bobot 10%)
- Menghitung hasil operasi dari notasi postfix dan prefix
- Menggunakan konsep Stack untuk evaluasi

#### 4. Tampilan Hasil (Bobot 25%)
- Menampilkan hasil operasi ke console
- Implementasi dalam Bahasa Java

### Case 2: Queue Implementation (Bobot 35%)

#### Fitur Utama:
- **Array Queue**: Implementasi queue menggunakan array dengan konsep circular array
- **Linked List Queue**: Implementasi queue menggunakan linked list
- **Operasi Dasar**:
  - `enqueue()`: Menambah item ke belakang queue
  - `dequeue()`: Menghapus dan mengembalikan item dari depan queue
  - `peek()`: Melihat item di depan queue tanpa menghapus
  - `size()`: Menghitung jumlah item dalam queue
  - `isEmpty()`: Memeriksa apakah queue kosong

#### Demo Program:
1. **Demo Array Queue**: Menunjukkan operasi queue dengan array
2. **Demo Linked List Queue**: Menunjukkan operasi queue dengan linked list
3. **Demo Interaktif**: User dapat memilih implementasi dan melakukan operasi queue secara interaktif

## Contoh Output

### Case 1:
```
=== SOAL CASE 1: KONVERSI DAN EVALUASI POSTFIX/PREFIX ===
Masukkan notasi infix (contoh: 5 + 4 / 5): 5 + 4 / 5

Notasi Infix: 5 + 4 / 5
Notasi Postfix: 545/+
Notasi Prefix: +/455
Hasil operasi Postfix: 5.8
Hasil operasi Prefix: 5.8
```

### Case 2:
```
=== SOAL CASE 2: MENGHITUNG JUMLAH ITEM DALAM ANTRIAN ===

--- Demo Array Queue ---
Menambahkan: Item 1
Menambahkan: Item 2
Menambahkan: Item 3
Menambahkan: Item 4
Menambahkan: Item 5
Queue: Item 1 Item 2 Item 3 Item 4 Item 5
Jumlah item dalam Array Queue: 5

Menghapus item dari depan:
Dihapus: Item 1
Dihapus: Item 2
Queue: Item 3 Item 4 Item 5
Jumlah item dalam Array Queue setelah penghapusan: 3
```

## Teknologi yang Digunakan
- Java 8+
- Konsep Object-Oriented Programming
- Abstract Data Types (ADT)
- Data Structures: Stack dan Queue
- Array dan Linked List implementations

