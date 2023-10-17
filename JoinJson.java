package cs3500.pa04.JSON;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @param githubUser -> server asking for github username
 * @param gameType   -> server asking for which gametype: Single or Multi
 */
public record JoinJson(@JsonProperty("name") String githubUser,
                       @JsonProperty("game-type") String gameType) {
}
