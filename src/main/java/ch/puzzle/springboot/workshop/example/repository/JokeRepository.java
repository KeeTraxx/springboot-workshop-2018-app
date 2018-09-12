package ch.puzzle.springboot.workshop.example.repository;

import ch.puzzle.springboot.workshop.example.model.Joke;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface JokeRepository extends CrudRepository<Joke, Long> {
    Iterable<Joke> findTop3ByOrderByWordsDesc();
    Iterable<Joke> findAllByWords(int words);

    @Query("SELECT j FROM Joke j WHERE j.joke LIKE %?1%")
    Iterable<Joke> containsWord(String search);
}
