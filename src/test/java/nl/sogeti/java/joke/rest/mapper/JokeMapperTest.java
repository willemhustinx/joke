package nl.sogeti.java.joke.rest.mapper;

import nl.sogeti.java.joke.model.joke.Joke;
import nl.sogeti.java.joke.model.joke.JokeFactory;
import nl.sogeti.java.joke.rest.response.JokeResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JokeMapperTest {

    @Test
    void givenAValidJoke_whenIsMapped_AValidJokeResponseIsReturned() {
        final Joke joke = JokeFactory.anJoke().build();

        final JokeResponse jokeResponse = JokeMapper.jokeToJokeResponse(joke);

        assertEquals(joke.getId(), jokeResponse.id());
        assertEquals(joke.getJoke(), jokeResponse.joke());
    }
}