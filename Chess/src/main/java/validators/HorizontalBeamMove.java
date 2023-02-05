package validators;

import lombok.val;
import models.Board;
import models.ValidationProps;

public class HorizontalBeamMove implements IMoveValidator {

    @Override
    public Boolean validate(ValidationProps props) {
        val piece = props.getPiece();
        val from = props.getFrom();
        val to = props.getTo();
        val board = Board.getBoard();
        if (!from.getX().equals(to.getX())) return false;
        for(int y = Math.min(from.getY(), to.getY())+1; y<Math.max(from.getY(), to.getY()); y++){
            val cell = board.getCell(y, from.getX());
            if(!cell.isFree()) return false;
        }
        return true;
    }
}
