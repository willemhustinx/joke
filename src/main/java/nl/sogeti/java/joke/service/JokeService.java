package nl.sogeti.java.joke.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.sogeti.java.joke.exception.NoJokeException;
import nl.sogeti.java.joke.exception.RestTemplateResponseErrorHandler;
import nl.sogeti.java.joke.model.joke.Joke;
import nl.sogeti.java.joke.model.joke.JokeList;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Comparator;

@Service
@Slf4j
@AllArgsConstructor
public class JokeService {

    public static final String URL = "https://v2.jokeapi.dev/joke/Any?type=single&amount=10";

    private final RestTemplate restTemplate;

    public JokeService(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder
                .errorHandler(new RestTemplateResponseErrorHandler())
                .build();
    }

    public Joke getAJoke() {
        final ResponseEntity<JokeList> response = restTemplate.getForEntity(URL, JokeList.class);

        if (response.getBody() == null  || response.getBody().getJokes() == null || response.getBody().getJokes().isEmpty()) {
            throw new NoJokeException("No jokes found!");
        }

        return response.getBody().getJokes()
                .stream()
                .filter(Joke::isSafe)
                .min(Comparator.comparingInt(Joke::getJokeLength))
                .orElseThrow(() -> new NoJokeException("No jokes found!"));
    }
}
