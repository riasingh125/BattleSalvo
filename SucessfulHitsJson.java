package cs3500.pa04.JSON;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @param empty -> empty string representing the empty response from client
 */
public record SucessfulHitsJson(@JsonProperty("arguments: ") String empty) {

}

