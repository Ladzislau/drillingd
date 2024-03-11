package by.upmebel.upmecutfile.repository;

import by.upmebel.upmecutfile.domain.Hole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface HoleRepository extends JpaRepository<Hole, Long> {

    @Query("SELECT h FROM Hole h " +
            "JOIN h.elementSide s " +
            "JOIN s.element e " +
            "WHERE h.id = :holeId " +
            "AND s.id = :sideId " +
            "AND e.id = :elementId")
    Optional<Hole> findByHoleIdAndSideIdAndElementId(Long holeId, Long sideId, Long elementId);

    @Query("SELECT h FROM Hole h " +
            "LEFT JOIN FETCH h.elementSide s " +
            "WHERE s.id = :sideId " +
            "AND h.id = :holeId")
    Optional<Hole> findByHoleIdAndSideIdWithSide(Long holeId, Long sideId);

    @Modifying
    @Query("DELETE FROM Hole h " +
            "WHERE h.elementSide.element.id = :elementId")
    void deleteByElementId(Long elementId);
}
