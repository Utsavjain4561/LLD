package validators;

import lombok.val;
import models.ValidationProps;


public class VerticalSingleStepMove implements IMoveValidator{
    @Override
    public Boolean validate(ValidationProps props) {
        // check diff of cell1 to cell2 is only 1 in positive direction
        val from = props.getFrom();
        val to = props.getTo();
        return from.getY().equals(to.getY()) && Math.abs(to.getX()-from.getX())==1;

    }

}
