package ch.puzzle.springboot.workshop.example.controller;

import ch.puzzle.springboot.workshop.example.model.PuzzleMember;
import ch.puzzle.springboot.workshop.example.repository.PuzzleMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/puzzle-members")
public class PuzzleMemberController {
    @Autowired
    PuzzleMemberRepository repo;

    @GetMapping
    Iterable<PuzzleMember> findAll() {
        return repo.findAll();
    }

    @PutMapping
    PuzzleMember newPuzzleMember(@RequestBody PuzzleMember puzzleMember) {
        return repo.save(puzzleMember);
    }

    @PostMapping(path = "/drink-coffee", consumes = MediaType.TEXT_PLAIN_VALUE)
    PuzzleMember incrementCoffeeCountById(@RequestBody String id) throws Exception {
        Optional<PuzzleMember> member = repo.findById(Long.parseLong(id));
        if (member.isPresent()) {
            member.get().setCoffeeConsumption(member.get().getCoffeeConsumption() + 1);
            return repo.save(member.get());
        } else {
            throw new Http404Exception("Something not found");
        }
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    private class Http404Exception extends Exception {
        public Http404Exception(String s) {
            super(s);
        }
    }
}
