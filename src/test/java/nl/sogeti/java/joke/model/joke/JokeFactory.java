package nl.sogeti.java.joke.model.joke;

import nl.sogeti.java.joke.util.TestUtils;


public class JokeFactory {

    public static Joke.JokeBuilder anJoke() {
        return new Joke.JokeBuilder()
                .id(TestUtils.faker.number().positive())
                .joke(TestUtils.faker.lorem().characters())
                .safe(TestUtils.faker.bool().bool());
    }
}