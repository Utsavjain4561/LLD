package models.pieces;

import conditions.Conditions;
import helpers.Checker;
import lombok.val;
import models.Cell;
import models.ValidationProps;

public class King extends Piece {

    @Override
    public Boolean canMove(Cell from, Cell to) {
        val props = new ValidationProps(this, from, to);
        val checker = new Checker(props);
        return checker.or(Conditions.KING_MOVES_VALIDATIONS) && checker.or(Conditions.CELL_SAFE_VALIDATIONS);
    }
}
