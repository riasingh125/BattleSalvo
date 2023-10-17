package cs3500.pa04.JSON;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa03.Model.GameResult;

/**
 * @param result -> server passing the result of the game
 * @param reason -> server passing the reason why the game ended
 */
public record EndgameJson(@JsonProperty("result") GameResult result,
                          @JsonProperty("reason") String reason) {
}
