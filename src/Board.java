package src;

public class Board {
    private String[][] board;
    private int rows, cols;

    public Board(int rows,int cols) {
        this.rows = rows;
        this.cols = cols;
        this.board = new String[rows][cols] ;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = ".";
            }
        }
    }
   
    public boolean canPlace(Piece piece, int row, int col) {
        for (int i = 0; i < piece.getHeight(); i++) {
            for (int j = 0; j < piece.getWidth(i); j++) {
                if (piece.getSpotAt(i, j) != ' ') {
                    int newRow = row + i;
                    int newCol = col + j;
                    if (newRow >= rows || newCol >= cols || !board[newRow][newCol].equals(".")) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void placePiece(Piece piece, int row, int col, char ch) {
        for (int i = 0; i < piece.getHeight(); i++) {
            for (int j = 0; j < piece.getWidth(i); j++) {
                if (piece.getSpotAt(i, j) != ' ') {
                    board[row + i][col + j] = String.valueOf(ch);
                }
            }
        }
    }

    public void removePiece(Piece piece, int row, int col) {
        for (int i = 0; i < piece.getHeight(); i++) {
            for (int j = 0; j < piece.getWidth(i); j++) {
                if (piece.getSpotAt(i, j) != ' ') {
                    board[row + i][col + j] = ".";
                }
            }
        }
    }

    public void printBoard() {
        final String[] colors ={
            "\u001B[31m",
            "\u001B[33m",
            "\u001B[32m",
            "\u001B[35m",
            "\u001B[34m",
            "\u001B[36m",
            "\u001B[37m",
            "\u001B[90m",
            "\u001B[91m",
            "\u001B[92m",
            "\u001B[93m",
            "\u001B[94m",
            "\u001B[95m",
            "\u001B[96m",
            "\u001B[90m"
        };
        
        System.out.println("Solusi ditemukan:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                String ch = board[i][j];
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    public int getCols(){
        return cols;
    }

    public int getRows(){
        return rows;
    }

    public String getBoard() {  
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sb.append(grid[i][j]);
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
