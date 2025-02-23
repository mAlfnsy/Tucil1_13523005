package src;

import java.util.List;


public class Solver {
    private Board board;
    private List<Piece> pieces;
    private boolean solved;
    private long attempts;
    private long startTime;

    public Solver(Board board, List<Piece> pieces) {
        this.board = board;
        this.pieces = pieces;
        this.solved = false;
        this.attempts = 0;
    }

    public void solve() {
        solve(0);
    }

    private void solve(int pieceIndex) { 
        if (pieceIndex == pieces.size()) {
            solved = true;
            board.printBoard();
            
            return;
        }

        Piece piece = pieces.get(pieceIndex);
        for (int rotation = 0; rotation < 4; rotation++) {
            for (int mirror = 0; mirror < 2; mirror++) {
                for (int i = 0; i < board.getRows(); i++) {
                    for (int j = 0; j < board.getCols(); j++) {
                        attempts++;
                        if (board.canPlace(piece, i, j)) {
                            board.placePiece(piece, i, j, piece.getShape()[0].trim().charAt(0));
                            solve(pieceIndex + 1);
                            if (solved) return;
                            board.removePiece(piece, i, j);
                        }
                    }
                }
                piece = piece.mirror();
            }
            piece = piece.rotate();
        }
    }

    public long getStartime(){
        return startTime;
    }

    public long getAttempts() {
        return attempts;
    }

    public boolean puzzleSolved(){
        return solved;
    }
}
