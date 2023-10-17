package cs3500.pa04.JSON;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa03.Model.ShipType;
import java.util.Map;

/**
 * @param width     -> serving asking for the width of the board
 * @param height    -> server asking for the height of the board
 * @param fleetSpec -> server asking for the amount of each ship
 */
public record SetupJson(@JsonProperty("width") int width,
                        @JsonProperty("height") int height,
                        @JsonProperty("fleet-spec") Map<ShipType, Integer> fleetSpec) {
}
