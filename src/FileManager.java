package src;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManager {
    

    public static inputFile FileManager(String filename) throws IOException{
    
        File file = new File("../test/input/" + filename);
        Scanner scanner = new Scanner(file);

        String firstLine = scanner.nextLine();

        if (firstLine == null || firstLine.trim().isEmpty()) {
            throw new IOException("Pastikan inputan N M P benar");
        }
        
        String[] first = firstLine.trim().split("\\s+");
        if (first.length != 3) {
            throw new IOException("Pastikan inputan N M P benar");   
        }
        try {
            int N = Integer.parseInt(first[0]);
            int M = Integer.parseInt(first[1]);
            int P = Integer.parseInt(first[2]);
            
            if (N <= 0 || M <= 0 || P <= 0) {
                throw new IOException("Invalid input: N, M, P must be positive");
            }
            
            String mode = scanner.nextLine().trim();
            if (!mode.equals("DEFAULT")) {
                System.out.println("Tidak Membuat Bonus Custom.\n");
                System.out.println("Solusi DEFAULT ");
            }

            List<Piece> pieces = new ArrayList<>();
            List<String> currentPiece = new ArrayList<>();
            char currentChar = '\0'; 

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine(); 

                if (line.trim().isEmpty()) {
                    continue;
                }

                char firstChar = line.trim().charAt(0);

                if (currentChar != '\0' && firstChar != currentChar) {
                    // Simpan potongan saat ini ke pieces
                    pieces.add(new Piece(currentPiece.toArray(new String[0])));
                    // Mulai potongan baru
                    currentPiece = new ArrayList<>();
                }

                currentPiece.add(line);
                currentChar = firstChar;
            }

            if (!currentPiece.isEmpty()) {
                pieces.add(new Piece(currentPiece.toArray(new String[0])));
            }
            scanner.close();
            return new inputFile(N, M, P, mode, pieces);
        }
        catch  (NumberFormatException e) {
            throw new IOException("Invalid number format in N M P line");
        }
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
