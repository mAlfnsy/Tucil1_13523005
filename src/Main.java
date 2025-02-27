package src;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.FileHandler;

import src.*;



public class Main {
    public static void main(String[] args) {
        Scanner inputFileName = new Scanner(System.in);
        System.out.print("Masukkan path file input: ");
        String fileName = inputFileName.nextLine();
        while (!fileName.endsWith(".txt")) {
            System.out.println("Format file harus (.txt).");
            System.out.print("Masukkan path file input: ");
            fileName = inputFileName.nextLine();
        } 

        try {
            inputFile pieces = FileManager.FileManager(fileName);
                      
            Board board = new Board(pieces.N, pieces.M);
            Solver solver = new Solver(board, pieces.pieces);
            
            long startTime = System.currentTimeMillis();
            solver.solve();
            long executionTime = System.currentTimeMillis() - startTime;
            long attempts = solver.getAttempts();
            System.out.println("Execution time: " + executionTime + " ms");
            System.err.println("Attempts : " + attempts);
            
            Scanner userInput = new Scanner(System.in);
            System.out.print("\nSave solution to txt file? (y/n): ");
            String save = userInput.nextLine().trim().toLowerCase();
            userInput.close();

            if (save.equals("y")){
                String outputFilename = "solution_" + fileName;
                       FileManager.saveToFile(outputFilename, board.getBoard(), executionTime, attempts);
                       System.out.println("Solution saved to: test/output/" + outputFilename);
            }
            else{
                System.out.println("Solution unsaved.");
            }
            
        } catch (IOException e) {
           System.out.println("Error: " + e.getMessage());
       } finally {
            inputFileName.close();
        }
    }
}