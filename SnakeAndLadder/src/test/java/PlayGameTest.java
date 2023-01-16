import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.code.model.Board;
import org.code.model.Counter;
import org.code.model.Dice;
import org.code.model.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class PlayGameTest {

    private final List<Player> players = new ArrayList<>();
    private final List<Counter> counters = new ArrayList<>();
    private final List<Player> winners = new ArrayList<>();
    private Dice dice;
    private Board board;

    @Test
    public void setup() {
        // Create 2 players
        players.add(new Player("Player1"));
        players.add(new Player("Player2"));
        players.add(new Player("Player3"));
        players.add(new Player("Player4"));
        players.add(new Player("Player5"));
        players.add(new Player("Player6"));
        players.add(new Player("Player7"));
        players.add(new Player("Player8"));
        players.add(new Player("Player9"));
        players.add(new Player("Player10"));


        // Create 2 counters
        counters.add(Counter.builder().color("Color1").player(players.get(0)).build());
        counters.add(Counter.builder().color("Color2").player(players.get(1)).build());
        counters.add(Counter.builder().color("Color3").player(players.get(2)).build());
        counters.add(Counter.builder().color("Color4").player(players.get(3)).build());
        counters.add(Counter.builder().color("Color5").player(players.get(4)).build());
        counters.add(Counter.builder().color("Color6").player(players.get(5)).build());
        counters.add(Counter.builder().color("Color7").player(players.get(6)).build());
        counters.add(Counter.builder().color("Color8").player(players.get(7)).build());
        counters.add(Counter.builder().color("Color9").player(players.get(8)).build());
        counters.add(Counter.builder().color("Color10").player(players.get(9)).build());

        // Create dice
        dice = new Dice();

        // Create a board of fixed size
        board = new Board(100);

        // Add ladders to board
        addLadders(board.getBoard());

        // Add snakes to board
        addSnakes(board.getBoard());
        // play game
        val winner = playGame();
        log.info("Winners of the game is : {}", winner);
    }

    public void moveCounter(@NonNull final Counter counter, final Integer diceValue) {
        val currentPosition = counter.getPosition();
        var targetPosition = currentPosition + diceValue;
        while (targetPosition <= board.getSize() && board.getBoard().get(targetPosition) > 0) {
            targetPosition = board.getBoard().get(targetPosition);
            log.info("Counter : {} jump to position : {}", counter.getColor(), targetPosition);
            counter.setPosition(targetPosition);
        }
        if (targetPosition <= board.getSize()) {
            counter.setPosition(targetPosition);
        }
        log.info("Counter : {} is at position: {} ", counter.getColor(), counter.getPosition());
    }

    private Boolean checkGameStatus(@NonNull final Counter counter) {
        return counter.getPosition().equals(board.getSize());
    }

    private List<Player> playGame() {
        var chance = 0;
        var totalCounter = counters.size();
        while (totalCounter > 1) {
            log.info("Current Turn is of player : {}", players.get(chance));
            val diceValue = dice.roll();
            moveCounter(counters.get(chance), diceValue);
            if (checkGameStatus(counters.get(chance))) {
                log.info("Player : {} won", players.get(chance));
                winners.add(players.get(chance));
                players.remove(chance);
                counters.remove(chance);

            }
            totalCounter = counters.size();
            chance = (++chance) % totalCounter;
        }
        return winners;

    }

    private void addLadders(final List<Integer> board) {
        try {
            board.set(2, 38);
            board.set(8, 31);
            board.set(21, 42);
            board.set(51, 67);
            board.set(46, 84);
            board.set(71, 91);
            board.set(80, 99);
        } catch (Exception e) {
            log.error("Ladder added outside game bounds");
        }

    }

    private void addSnakes(final List<Integer> board) {
        try {
            board.set(33, 5);
            board.set(63, 16);
            board.set(54, 34);
            board.set(93, 74);
            board.set(97, 61);
        } catch (Exception e) {
            log.error("Snakes added outside game bounds");
        }
    }
}
