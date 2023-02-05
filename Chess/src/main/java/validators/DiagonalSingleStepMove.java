package validators;


import lombok.val;
import models.ValidationProps;

public class DiagonalSingleStepMove implements IMoveValidator {
    @Override
    public Boolean validate(ValidationProps props) {
        val piece = props.getPiece();
        val from = props.getFrom();
        val to = props.getTo();
        val xDiff = Math.abs(from.getX() - to.getX());
        val yDiff = Math.abs(from.getY() - to.getY());
        return xDiff + yDiff == 2;


    }
}
