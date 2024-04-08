package nl.sogeti.java.joke.rest.controller;

import nl.sogeti.java.joke.exception.NoJokeException;
import nl.sogeti.java.joke.model.joke.Joke;
import nl.sogeti.java.joke.model.joke.JokeFactory;
import nl.sogeti.java.joke.rest.controller.JokeController;
import nl.sogeti.java.joke.service.JokeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(JokeController.class)
class JokeControllerTest {

    MockMvc mockMvc;
    @MockBean
    JokeService jokeService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    void givenAValidRequest_whenGetAJoke_thenAJokeShouldBeReturned() throws Exception {
        final Joke expectedJoke = JokeFactory.anJoke().build();

        when(jokeService.getAJoke()).thenReturn(expectedJoke);

        mockMvc.perform(get("/api/joke")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(expectedJoke.getId()))
                .andExpect(jsonPath("$.joke").value(expectedJoke.getJoke()));
    }

    @Test
    void givenAValidRequestAndExceptionFromService_whenGetAJoke_thenExceptionShouldBeHandled() throws Exception {
        final String errorMessage = "No jokes found!";

        when(jokeService.getAJoke()).thenThrow(new NoJokeException(errorMessage));

        mockMvc.perform(get("/api/joke")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$").value(errorMessage));
    }

}