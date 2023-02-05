package models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import models.pieces.Piece;

import java.util.Objects;

@RequiredArgsConstructor
@Getter
public class Cell {
    // y is col in matrix
    private final Integer y;
    // x is row in matrix
    private final Integer x;
    @Setter
    private Piece piece;

    public boolean isFree(){
        return Objects.isNull(this.piece);
    }

}
