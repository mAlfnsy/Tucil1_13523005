package src;

import java.util.List;

public class inputFile {
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
