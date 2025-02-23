package src;

public class Piece {
    private String[] shape;

    public Piece(String[] shape) {
        this.shape = shape;
    }

    public int getHeight() {
        return shape.length;
    }

    public int getWidth(int row) {
        return shape[row].length();
    }

    public char getSpotAt(int row, int col) {
        return shape[row].charAt(col);
    }

    public String[] getShape() {
        return shape;
    }

    public char[][] toMatrix() {
        // Hitung jumlah baris dan kolom maksimum
        int rows = shape.length;
        int cols = 0;
        for (String row : shape) {
            if (row.length() > cols) {
                cols = row.length();
            }
        }
    
        // Buat matriks dengan ukuran yang sesuai
        char[][] matrix = new char[rows][cols];
    
        // Isi matriks dengan karakter dari shape
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < shape[i].length(); j++) {
                matrix[i][j] = shape[i].charAt(j);
            }
            // Isi sisa kolom dengan spasi jika panjang baris tidak seragam
            for (int j = shape[i].length(); j < cols; j++) {
                matrix[i][j] = ' ';
            }
        }
    
        return matrix;
    }

    private static char[][] rotateMatrix(char[][] block){
        int rows = block.length;
        int cols = block[0].length;
        char[][] rotated = new char[cols][rows];

        for (int i = 0; i < rows; ++i){
            for (int j = 0; j < cols; ++j){
                rotated[j][rows - 1 - i] = block[i][j];
            }
        }

        return rotated;
    }

    public Piece rotate() {
        char[][] matrix = toMatrix();
        char[][] rotatedMatrix = rotateMatrix(matrix);

        String[] newShape = new String[rotatedMatrix.length]; 

        for (int i = 0; i < rotatedMatrix.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < rotatedMatrix[i].length; j++) {
                sb.append(rotatedMatrix[i][j]);
            }

            newShape[i] = sb.toString();
        }

        return new Piece(newShape);
    }


    public Piece mirror() {
        String[] newShape = new String[shape.length];
        for (int i = 0; i < shape.length; i++) {
            newShape[i] = new StringBuilder(shape[i]).reverse().toString();
        }
        return new Piece(newShape);
    }

    // Cetak bentuk potongan (untuk debugging)
    public void printPiece() {
        for (String row : shape) {
            System.out.println(row);
        }
    }

    public static void printMatrix(char[][] matrix) {
        for (char[] row : matrix) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
                }
    }
}
