package by.upmebel.upmecutfile.repository;

import by.upmebel.upmecutfile.domain.ElementSide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ElementSideRepository extends JpaRepository<ElementSide, Long> {

    @Query("SELECT s from ElementSide s " +
            "LEFT JOIN FETCH s.holes h " +
            "WHERE s.element.id = :elementId")
    List<ElementSide> findAllByElementId(Long elementId);

    @Query("SELECT s from ElementSide s " +
            "JOIN s.element e " +
            "LEFT JOIN FETCH s.holes h " +
            "WHERE e.id = :elementId")
    List<ElementSide> findAllByElementIdWithHoles(Long elementId);

    @Query("SELECT s from ElementSide s " +
            "JOIN s.element e " +
            "LEFT JOIN FETCH s.holes h " +
            "WHERE e.id = :elementId " +
            "AND s.id = :sideId")
    Optional<ElementSide> findBySideIdAndElementIdWithHoles(Long sideId, Long elementId);

    @Query("SELECT s from ElementSide s " +
            "LEFT JOIN FETCH s.element e " +
            "WHERE s.id = :sideId " +
            "AND e.id = :elementId")
    Optional<ElementSide> findBySideIdAndElementIdWithElement(Long sideId, Long elementId);

    @Query("SELECT s from ElementSide s " +
            "LEFT JOIN FETCH s.holes h " +
            "WHERE s.element.id IN :elementIds")
    List<ElementSide> findAllByIdsWithHoles(List<Long> elementIds);

    @Query("SELECT s from ElementSide s " +
            "LEFT JOIN FETCH s.holes h " +
            "WHERE s.id = :sideId")
    Optional<ElementSide> findByIdWithHoles(Long sideId);


}
