package ch.puzzle.springboot.workshop.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Joke {

    @Id
    @GeneratedValue
    private long id;

    private String joke;

    private int words;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    public int getWords() {
        return words;
    }

    public void setWords(int words) {
        this.words = words;
    }

    public void countWords() {
        String trimmed = this.joke.trim();
        if (trimmed.isEmpty()) {
            words = 0;
        } else {
            words = trimmed.split("\\s+").length;
        }
    }

}
