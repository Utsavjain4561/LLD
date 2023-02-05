package factory;

import enums.Color;
import enums.Direction;
import enums.PieceType;
import lombok.val;
import models.pieces.Piece;

public class PieceFactory {

    public static Piece getPiece(PieceType type, Color color){
        val piece = type.getConstructor().get();
        piece.setColor(color);
        if(PieceType.PAWN.equals(type))
            piece.setDirection(color.equals(Color.WHITE) ? Direction.UP: Direction.DOWN);
        return piece;
    }
}
