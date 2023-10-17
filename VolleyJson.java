package cs3500.pa04.JSON;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa03.Model.Coord;
import java.util.List;

/**
 * @param coords -> server asking for the back and forth shooting of shots
 */
public record VolleyJson(@JsonProperty("coordinates") List<Coord> coords) {
}
