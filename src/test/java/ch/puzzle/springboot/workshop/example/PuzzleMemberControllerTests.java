package ch.puzzle.springboot.workshop.example;

import ch.puzzle.springboot.workshop.example.controller.PuzzleMemberController;
import ch.puzzle.springboot.workshop.example.model.PuzzleMember;
import ch.puzzle.springboot.workshop.example.repository.PuzzleMemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(PuzzleMemberController.class)
public class PuzzleMemberControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PuzzleMemberRepository repository;

    private PuzzleMember mockPuzzleMember = new PuzzleMember("Hans", "Muster", 3);

    @Test
    public void incrementCoffeeConsumption() throws Exception {
        given(repository.save(mockPuzzleMember)).willReturn(mockPuzzleMember);
        given(repository.findById(42L)).willReturn(Optional.of(mockPuzzleMember));

        mvc.perform(post("/api/puzzle-members/drink-coffee")
                .contentType(MediaType.TEXT_PLAIN)
                .content("42"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.coffeeConsumption", is(4)))
                .andExpect(jsonPath("$.firstName", is("Hans")))
                .andExpect(jsonPath("$.lastName", is("Muster")));
    }

    @Test
    public void noUserFound() throws Exception {
        mvc.perform(post("/api/puzzle-members/drink-coffee")
                .contentType(MediaType.TEXT_PLAIN)
                .content("43"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}
