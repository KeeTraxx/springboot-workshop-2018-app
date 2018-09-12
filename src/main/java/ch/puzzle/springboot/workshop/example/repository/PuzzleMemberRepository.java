package ch.puzzle.springboot.workshop.example.repository;

import ch.puzzle.springboot.workshop.example.model.PuzzleMember;
import org.springframework.data.repository.CrudRepository;

public interface PuzzleMemberRepository extends CrudRepository<PuzzleMember, Long> {
}
