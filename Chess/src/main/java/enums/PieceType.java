package enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import models.pieces.*;

import java.util.function.Supplier;
@RequiredArgsConstructor
@Getter
public enum PieceType {
    PAWN(Pawn::new),
    KING(King::new),
    QUEEN(Queen::new),
    KNIGHT(Knight::new),
    ROOK(Rook::new),
    BISHOP(Bishop::new);

    private final Supplier<Piece> constructor;
}
