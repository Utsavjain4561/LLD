package models;


import java.util.Objects;

public class Board {

    private final Integer size;
    private Cell[][] cells;
    private static Board board;

    private Board(Integer size){
        this.size = size;
        this.cells = new Cell[size][size];
        setBoard();
    }
    public static Board createBoard(Integer size){
        if(Objects.isNull(board)){
            board = new Board(size);
        }
        return board;
    }
    public static Board getBoard() {
        return board;
    }

    private void setBoard(){
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                cells[i][j] = new Cell(i, j);
            }
        }
        // TODO: initialize the pieces on the chess board
    }
    public Cell getCell(Integer y, Integer x){
        return x>=0 && x<size && y>=0 && y<size ? this.cells[x][y]: null;
    }

}
