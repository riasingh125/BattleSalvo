package cs3500.pa04.JSON;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa03.Model.Coord;
import java.util.List;

/**
 * @param coordinates -> server asking for the shots the client fires
 */
public record TakeShotsJson(@JsonProperty("coordinates") List<Coord> coordinates) {
}
