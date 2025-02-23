package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PuzzleSolver {
    private int N, M, P;
    private String[][] board;
    private List<String[]> pieces;
    private boolean solved;

    public PuzzleSolver(int N, int M, int P, List<String[]> pieces) {
        this.N = N;
        this.M = M;
        this.P = P;
        this.board = new String[N][M];
        this.pieces = pieces;
        this.solved = false;
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                board[i][j] = ".";
            }
        }
    }

    public void solve() {
        solve(0);
    }

    private void solve(int pieceIndex) {
        if (pieceIndex == P) {
            solved = true;
            printSolution();
            if (!solved){
                System.out.println("Ga selesai");
            }
            return;
        }

        String[] piece = pieces.get(pieceIndex);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (canPlace(piece, i, j)) {
                    placePiece(piece, i, j, piece[0].trim().charAt(0));
                    solve(pieceIndex + 1);
                    if (solved) return;
                    removePiece(piece, i, j);
                }
            }
        }
    }

    private boolean canPlace(String[] piece, int row, int col) {
        for (int i = 0; i < piece.length; i++) {
            for (int j = 0; j < piece[i].length(); j++) {
                if (piece[i].charAt(j) != ' ') {
                    int newRow = row + i;
                    int newCol = col + j;
                    if (newRow >= N || newCol >= M || !board[newRow][newCol].equals(".")) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private void placePiece(String[] piece, int row, int col, char ch) {
        for (int i = 0; i < piece.length; i++) {
            for (int j = 0; j < piece[i].length(); j++) {
                if (piece[i].charAt(j) != ' ') {
                    board[row + i][col + j] = String.valueOf(ch);
                }
            }
        }
    }

    private void removePiece(String[] piece, int row, int col) {
        for (int i = 0; i < piece.length; i++) {
            for (int j = 0; j < piece[i].length(); j++) {
                if (piece[i].charAt(j) != ' ') {
                    board[row + i][col + j] = ".";
                }
            }
        }
    }

    private void printSolution() {
        System.out.println("Solusi ditemukan:");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner filescanner = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter filename : ");

        String fileInput = filescanner.nextLine();

        try {
            File file = new File("test/input/"+ fileInput);
            Scanner scanner = new Scanner(file);

            int N = scanner.nextInt();
            int M = scanner.nextInt();
            int P = scanner.nextInt();
            scanner.nextLine(); // Consume newline setelah N, M, P

            List<String[]> pieces = new ArrayList<>();
            List<String> currentPiece = new ArrayList<>();
            char currentChar = '\0'; // Karakter awal potongan saat ini

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                // Jika baris kosong, abaikan
                if (line.trim().isEmpty()) {
                    continue;
                }

                // Ambil karakter pertama dari baris
                char firstChar = line.trim().charAt(0);

                // Jika karakter pertama berbeda dengan currentChar, potongan saat ini selesai
                if (currentChar != '\0' && firstChar != currentChar) {
                    // Simpan potongan saat ini ke pieces
                    pieces.add(currentPiece.toArray(new String[0]));
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
                pieces.add(currentPiece.toArray(new String[0]));
            }

            PuzzleSolver solver = new PuzzleSolver(N, M, P, pieces);
            long startTime = System.currentTimeMillis();
            solver.solve();
            long executionTime = System.currentTimeMillis() - startTime;

            System.out.println("Execution time: " + executionTime + " ms");
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File tidak ditemukan.");
        }
    }
}