package cs3500.pa04.JSON;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * @param fleet -> server passing a fleet
 */
public record FleetJson(@JsonProperty("fleet") List<ShipJson> fleet) {
}
