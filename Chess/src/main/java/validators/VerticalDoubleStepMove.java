package validators;

import lombok.val;
import models.ValidationProps;

public class VerticalDoubleStepMove implements IMoveValidator {
    @Override
    public Boolean validate(ValidationProps props) {
        val piece = props.getPiece();
        val from = props.getFrom();
        val to = props.getTo();
        if (piece.getMoves() > 0) return false;

        return from.getY().equals(to.getY()) && Math.abs(to.getX() - from.getX()) == 2;
    }
}
