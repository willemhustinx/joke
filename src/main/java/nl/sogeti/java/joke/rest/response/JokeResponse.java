package nl.sogeti.java.joke.rest.response;

import lombok.Builder;

@Builder
public record JokeResponse(
        long id,
        String joke) {
}
