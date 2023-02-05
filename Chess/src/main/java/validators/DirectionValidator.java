package validators;

import enums.Direction;
import lombok.val;
import models.ValidationProps;


public class DirectionValidator implements IMoveValidator{

    @Override
    public Boolean validate(ValidationProps props) {
        val piece = props.getPiece();
        val from = props.getFrom();
        val to = props.getTo();
        val direction = piece.getDirection();
        if(direction.equals(Direction.BOTH)) return true;
        if(direction.equals(Direction.UP) && to.getX()<from.getX()) return true;
        else return direction.equals(Direction.DOWN) && to.getX() > from.getX();
    }
}
