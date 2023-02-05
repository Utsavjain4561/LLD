package models;

import enums.Color;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.val;

@RequiredArgsConstructor
@Getter
public abstract class Player {
    private String id;
    private Boolean isChecked = false;
    private Color color;

    public void makeMove(Cell from, Cell to) {
        val piece = from.getPiece();
        // check if the move is valid or not
        if (!piece.canMove(from, to)) throw new UnsupportedOperationException("Not a valid move");
        from.setPiece(null);
        piece.setMoves(piece.getMoves() + 1);
        // Kill the opponents piece if we can
        if (!to.isFree()) {
            val opponentPiece = to.getPiece();
            opponentPiece.setIsKilled(true);
        }
        // move the piece
        to.setPiece(piece);
    }
}
