package validators;

import lombok.val;
import models.ValidationProps;

public class HorizontalSingleStepMove implements IMoveValidator{
    @Override
    public Boolean validate(ValidationProps props) {
        // check diff of cell1 to cell2 is only 1 in positive direction
        val from = props.getFrom();
        val to = props.getTo();
        return from.getX().equals(to.getX()) && Math.abs(to.getY()-from.getY())==1;
    }
}
