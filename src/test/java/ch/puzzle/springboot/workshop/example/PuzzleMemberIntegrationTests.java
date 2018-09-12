package ch.puzzle.springboot.workshop.example;

import ch.puzzle.springboot.workshop.example.model.PuzzleMember;
import ch.puzzle.springboot.workshop.example.repository.PuzzleMemberRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PuzzleMemberIntegrationTests {

    private static final PuzzleMember PUZZLE_MEMBER = new PuzzleMember() {{
        this.setFirstName("Khôi");
        this.setFirstName("Tran");
    }};

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mvc;

    @Autowired
    private PuzzleMemberRepository repository;

    static String toJson(Object o) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(o);
    }

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void incrementCoffeeConsumption() throws Exception {
        MvcResult result = mvc.perform(put("/api/puzzle-members")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(toJson(PUZZLE_MEMBER)))
                .andDo(print()).andReturn();

        Integer id = JsonPath.read(result.getResponse().getContentAsString(), "$.id");

        mvc.perform(post("/api/puzzle-members/drink-coffee")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(id.toString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.coffeeConsumption", is(1)));
    }
}
