import enums.Color;
import enums.PieceType;
import factory.PieceFactory;
import lombok.val;
import models.Board;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ChessTest {

    @Test
    public void setup(){
        // initialize a board
        val board = Board.createBoard(8);

        val whiteP1 = PieceFactory.getPiece(PieceType.PAWN, Color.BLACK);
        val cell_1_0 = board.getCell(1, 0);
        cell_1_0.setPiece(whiteP1);
        val cell_2_0 = board.getCell(2, 0);

        val result = whiteP1.canMove(cell_1_0,cell_2_0);
        Assertions.assertTrue(result);


    }
}
