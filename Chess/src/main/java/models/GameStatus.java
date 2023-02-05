package models;

import enums.GameResult;
import lombok.Builder;

@Builder(toBuilder = true)
public class GameStatus {
    private final Player winner;
    private final GameResult result;

}
