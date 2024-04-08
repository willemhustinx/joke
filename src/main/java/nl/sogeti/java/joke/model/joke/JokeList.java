package nl.sogeti.java.joke.model.joke;

import lombok.Data;

import java.util.List;

@Data
public class JokeList {
    private List<Joke> jokes;
}
