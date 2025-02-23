package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManager {
    public static class inputFile {
        public final int N, M, P;
        public final String mode;
        public final List<Piece> pieces;
        
        public inputFile(int N, int M, int P, String mode, List<Piece> pieces) {
            this.N = N;
            this.M = M;
            this.P = P;
            this.mode = mode;
            this.pieces = pieces;
        }
    }

    public static inputFile FileManager(String filename) throws FileNotFoundException{
        File file = new File("Tucil1_13523005/test/input/" + filename);
        Scanner scanner = new Scanner(file);

        int N = scanner.nextInt();
        int M = scanner.nextInt();
        int P = scanner.nextInt();
        scanner.nextLine(); // Consume newline setelah N, M, P

        String mode = scanner.nextLine().trim();
        if (!mode.equals("DEFAULT")) {
            System.out.println("Tidak Membuat Bonus Custom.\n");
            System.out.println("Solusi DEFAULT ");
        }

        List<Piece> pieces = new ArrayList<>();
        List<String> currentPiece = new ArrayList<>();
        char currentChar = '\0'; // Karakter awal potongan saat ini

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine(); // Jangan gunakan trim() di sini

            // Jika baris kosong, abaikan
            if (line.trim().isEmpty()) {
                continue;
            }

            // Ambil karakter pertama yang bukan spasi dari baris
            char firstChar = line.trim().charAt(0);

            // Jika karakter pertama berbeda dengan currentChar, potongan saat ini selesai
            if (currentChar != '\0' && firstChar != currentChar) {
                // Simpan potongan saat ini ke pieces
                pieces.add(new Piece(currentPiece.toArray(new String[0])));
                // Mulai potongan baru
                currentPiece = new ArrayList<>();
            }

            // Tambahkan baris ke potongan saat ini
            currentPiece.add(line);
            // Update currentChar
            currentChar = firstChar;
        }

        // Tambahkan potongan terakhir ke pieces
        if (!currentPiece.isEmpty()) {
            pieces.add(new Piece(currentPiece.toArray(new String[0])));
        }

        scanner.close();
        return new inputFile(N, M, P, mode, pieces);
    }

    public static void saveToFile(String filename, String boardState, long executionTime, long attempts) throws IOException {
        File testFolder = new File("../test/output");
        if (!testFolder.exists()) {
            testFolder.mkdir();
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter("../test/output/" + filename))) {
            writer.println("Solution:");
            writer.println(boardState);
            writer.println("Execution time: " + executionTime + " ms");
            writer.println("Iterations: " + attempts);
        }
    }
}
