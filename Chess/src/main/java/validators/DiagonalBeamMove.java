package validators;

import lombok.val;
import models.Board;
import models.Cell;
import models.ValidationProps;
import org.apache.commons.lang3.tuple.Pair;

public class DiagonalBeamMove implements IMoveValidator {
    @Override
    public Boolean validate(ValidationProps props) {
        val from = props.getFrom();
        val to = props.getTo();
        val board = Board.getBoard();
        val xDiff = Math.abs(from.getX() - to.getX());
        val yDiff = Math.abs(from.getY() - to.getY());
        val signs = compare(from, to);
        if (xDiff != yDiff) return false;
        for (int d = 1; d < xDiff; d++) {
            val x = from.getX() + signs.getLeft() * d;
            val y = from.getY() + signs.getRight() * d;
            val cell = board.getCell(y, x);
            if (!cell.isFree()) return false;
        }
        return true;
    }

    private Pair<Integer, Integer> compare(Cell from, Cell to) {
        val x1 = from.getX();
        val y1 = from.getY();
        val x2 = to.getX();
        val y2 = to.getY();
        // scan on which side is dest cell
        if (x1 > x2 && y1 < y2) return Pair.of(-1, 1);
        else if (x1 > x2 && y1 > y2) return Pair.of(-1, -1);
        else if (x1 < x2 && y1 < y2) return Pair.of(1, 1);
        return Pair.of(1, -1);
    }
}
