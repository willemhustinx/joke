package nl.sogeti.java.joke.rest.controller;

import lombok.AllArgsConstructor;
import nl.sogeti.java.joke.model.joke.Joke;
import nl.sogeti.java.joke.rest.mapper.JokeMapper;
import nl.sogeti.java.joke.rest.response.JokeResponse;
import nl.sogeti.java.joke.service.JokeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/joke")
@AllArgsConstructor
public class JokeController {

    private final JokeService jokeService;

    @GetMapping
    public ResponseEntity<JokeResponse> getAJoke() {
        final Joke joke = jokeService.getAJoke();
        return ResponseEntity.ok().body(JokeMapper.jokeToJokeResponse(joke));
    }
}
