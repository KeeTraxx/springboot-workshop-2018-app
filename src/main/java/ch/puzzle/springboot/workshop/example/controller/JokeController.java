package ch.puzzle.springboot.workshop.example.controller;

import ch.puzzle.springboot.workshop.example.model.Joke;
import ch.puzzle.springboot.workshop.example.repository.JokeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/joke")
public class JokeController {

    @Autowired
    private JokeRepository jokeRepository;

    @PostConstruct
    void loadJokesFromExternalSource() {
        RestTemplate template = new RestTemplate();
        ResponseEntity<JokeResponse> responseEntity = template.getForEntity("http://api.icndb.com/jokes/random/5", JokeResponse.class);

        JokeResponse jokeResponse = responseEntity.getBody();
        List<Joke> jokes = Arrays.asList(jokeResponse.getValue());

        jokes.forEach( j -> j.countWords());

        jokeRepository.saveAll(jokes);
    }

    @GetMapping
    public Iterable<Joke> getJokes() {
        return jokeRepository.findAll();
    }

    @GetMapping("top3")
    public Iterable<Joke> top3ByWords() {
        return jokeRepository.findTop3ByOrderByWordsDesc();
    }

    @GetMapping("bywords/{words}")
    public Iterable<Joke> byWords(@RequestParam int words) {
        return jokeRepository.findAllByWords(words);
    }

    @GetMapping("contains/{word}")
    public Iterable<Joke> byWords(@RequestParam String word) {
        return jokeRepository.containsWord(word);
    }

    @GetMapping("{id}")
    public Optional<Joke> getJoke(@PathVariable long id) {
        return jokeRepository.findById(id);
    }

    @PostMapping
    public Joke addJoke(Joke joke) {
        return jokeRepository.save(joke);
    }


}

class JokeResponse {
    Joke[] value;

    public Joke[] getValue() {
        return value;
    }
}
