package models.pieces;

import enums.Color;
import enums.Direction;
import lombok.Getter;
import lombok.Setter;
import models.Cell;

@Getter
public abstract class Piece {
    @Setter
    private Color color;
    @Setter
    private Boolean isKilled = false;
    @Setter
    private Direction direction = Direction.BOTH;
    @Setter
    private int moves = 0;
    public Boolean canMove(Cell from, Cell to){return false;}
}
