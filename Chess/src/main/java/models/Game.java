package models;



import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.val;

import java.util.List;

@RequiredArgsConstructor
public class Game {

    private Board board;
    private List<Player> players;
    @Setter
    private GameStatus status;
    private Integer turn = 0;

    public GameStatus playTurn(Cell from, Cell to){
        val player = players.get(turn);
        turn = ++turn % players.size();
        // make move of player
        player.makeMove(from, to);
        // scan board for game status --> (check, checkMate or draw)

        return status;
    }
}
