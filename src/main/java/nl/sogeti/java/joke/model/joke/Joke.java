package nl.sogeti.java.joke.model.joke;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Joke {
    private long id;
    private String joke;
    private boolean safe;

    public int getJokeLength() {
        return joke.length();
    }
}
