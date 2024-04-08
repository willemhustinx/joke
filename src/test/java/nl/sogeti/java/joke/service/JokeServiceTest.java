package nl.sogeti.java.joke.service;

import nl.sogeti.java.joke.exception.NoJokeException;
import nl.sogeti.java.joke.model.joke.Joke;
import nl.sogeti.java.joke.model.joke.JokeFactory;
import nl.sogeti.java.joke.model.joke.JokeList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JokeServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    JokeService jokeService;

    @Test
    void givenAValidResponse_whenGetAJoke_ThenAValidJokeIsReturned() {

        final Joke expectedJoke = JokeFactory.anJoke()
                .joke("1")
                .safe(true)
                .build();
        List<Joke> jokes = new ArrayList<>();

        jokes.add(JokeFactory.anJoke()
                .joke("12")
                .safe(true)
                .build());
        jokes.add(JokeFactory.anJoke()
                .joke("1")
                .safe(false)
                .build());
        jokes.add(expectedJoke);

        JokeList jokeList = new JokeList();
        jokeList.setJokes(jokes);

        when(restTemplate.getForEntity(JokeService.URL, JokeList.class)).thenReturn(ResponseEntity.ok(jokeList));

        final Joke result = jokeService.getAJoke();

        assertEquals(expectedJoke, result);
    }

    @Test
    void givenAValidResponseWithNoSafeJoke_whenGetAJoke_ThenAExceptionIsThrows() {
        List<Joke> jokes = new ArrayList<>();

        jokes.add(JokeFactory.anJoke()
                .safe(false)
                .build());

        JokeList jokeList = new JokeList();
        jokeList.setJokes(jokes);

        when(restTemplate.getForEntity(JokeService.URL, JokeList.class)).thenReturn(ResponseEntity.ok(jokeList));

        assertThrows(NoJokeException.class, () -> jokeService.getAJoke());
    }
}