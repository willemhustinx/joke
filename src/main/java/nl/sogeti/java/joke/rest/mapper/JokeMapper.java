package nl.sogeti.java.joke.rest.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import nl.sogeti.java.joke.model.joke.Joke;
import nl.sogeti.java.joke.rest.response.JokeResponse;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JokeMapper {
    public static JokeResponse jokeToJokeResponse(Joke joke) {
        return JokeResponse.builder()
                .id(joke.getId())
                .joke(joke.getJoke())
                .build();
    }
}
