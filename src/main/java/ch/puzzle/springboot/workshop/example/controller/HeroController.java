package ch.puzzle.springboot.workshop.example.controller;

import com.google.common.collect.Lists;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/heroes")
public class HeroController {

    private final List<String> heroes = Lists.newArrayList("Batman", "Catwoman");

    @GetMapping
    public List<String> getHeroes() {
        return heroes;
    }

    @PutMapping(consumes = MediaType.TEXT_PLAIN_VALUE)
    public void putHero(@RequestBody String hero) {
        heroes.add(hero);
    }

    @GetMapping("letter/{letter}")
    public Stream<String> getHeroesStartingWithLetter(@PathVariable String letter) {
        return heroes.stream().filter( name -> name.startsWith(letter));
    }

}
