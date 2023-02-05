package validators;

import lombok.val;
import models.ValidationProps;

public class OpponentOccupiedCell implements IMoveValidator{
    @Override
    public Boolean validate(ValidationProps props) {
        val piece = props.getPiece();
        val to = props.getTo();
        return !piece.getColor().equals(to.getPiece().getColor());
    }
}
