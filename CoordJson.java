package cs3500.pa04.JSON;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @param xpos -> server passing x position
 * @param ypos -> server passing y position
 */
public record CoordJson(@JsonProperty("x") int xpos,
                        @JsonProperty("y") int ypos) {
}
