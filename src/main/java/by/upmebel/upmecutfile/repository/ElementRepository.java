package by.upmebel.upmecutfile.repository;

import by.upmebel.upmecutfile.domain.Element;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ElementRepository extends JpaRepository<Element, Long> {

    @Query("SELECT DISTINCT e FROM Element e " +
            "LEFT JOIN FETCH e.sides " +
            "WHERE (e.id = :id)")
    Optional<Element> findByIdWithSides(Long id);

    @Query("SELECT DISTINCT e FROM Element e " +
            "LEFT JOIN FETCH e.sides")
    Page<Element> findAllWithSides(Pageable pageable);

}