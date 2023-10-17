package cs3500.pa04.JSON;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa03.Model.Coord;
import java.util.List;

/**
 * @param damage -> server asking for the damage
 */
public record ReportDamageJson(@JsonProperty("coordinates") List<Coord> damage) {
}
