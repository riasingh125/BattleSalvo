package cs3500.pa04.JSON;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa03.Model.Coord;
import cs3500.pa03.Model.DirectionType;

/**
 * @param coord     ->server asking for the coordinates of the ships
 * @param length    -> server asking for the length of each ship
 * @param direction ->server asking for the direction of each ship
 */
public record ShipJson(@JsonProperty("coord") Coord coord,
                       @JsonProperty("length") int length,
                       @JsonProperty("direction") DirectionType direction) {

}
