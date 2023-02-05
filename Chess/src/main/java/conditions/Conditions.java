package conditions;

import validators.*;

import java.util.List;
import java.util.function.Supplier;

public class Conditions {

    public static final List<Supplier<IMoveValidator>> BASE_MOVE_VALIDATIONS = List.of(DirectionValidator::new);
    public static final List<Supplier<IMoveValidator>> PAWN_VERTICAL_MOVE_VALIDATIONS = List.of(
            VerticalDoubleStepMove::new, VerticalSingleStepMove::new
    );
    public static final Supplier<IMoveValidator> PAWN_DIAGONAL_MOVE_VALIDATION = DiagonalSingleStepMove::new;
    public static final List<Supplier<IMoveValidator>> KING_MOVES_VALIDATIONS = List.of(
            VerticalSingleStepMove::new, DiagonalSingleStepMove::new, HorizontalSingleStepMove::new
    );
    public static final List<Supplier<IMoveValidator>> ROOK_MOVE_VALIDATIONS = List.of(
            HorizontalBeamMove::new, VerticalBeamMove::new
    );
    public static final List<Supplier<IMoveValidator>> BISHOP_MOVE_VALIDATIONS = List.of(DiagonalBeamMove::new);
    public static final List<Supplier<IMoveValidator>> QUEEN_MOVE_VALIDATIONS = List.of(
            HorizontalBeamMove::new, VerticalBeamMove::new, DiagonalBeamMove::new
    );
    public static final List<Supplier<IMoveValidator>> KNIGHT_MOVE_VALIDATIONS = List.of(LStepMove::new);

    public static final Supplier<IMoveValidator> CELL_FREE_VALIDATION = CellUnoccupied::new;
    public static final Supplier<IMoveValidator> OPPONENT_CELL_VALIDATION = OpponentOccupiedCell::new;
    public static final List<Supplier<IMoveValidator>> CELL_SAFE_VALIDATIONS = List.of(
            CellUnoccupied::new, OpponentOccupiedCell::new
    );

}
