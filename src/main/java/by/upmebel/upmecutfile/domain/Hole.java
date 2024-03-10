package by.upmebel.upmecutfile.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "holes")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hole {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private double diameter;

    private double depth;

    private int drillingEntrySpeed;

    private int drillingExitSpeed;

    private double coordinateByLength;

    private double coordinateByBreadth;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "element_side_id", referencedColumnName = "id")
    private ElementSide elementSide;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hole hole = (Hole) o;
        return Double.compare(diameter, hole.diameter) == 0 && Double.compare(depth, hole.depth) == 0 && drillingEntrySpeed == hole.drillingEntrySpeed && drillingExitSpeed == hole.drillingExitSpeed && Double.compare(coordinateByLength, hole.coordinateByLength) == 0 && Double.compare(coordinateByBreadth, hole.coordinateByBreadth) == 0 && Objects.equals(id, hole.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, diameter, depth, drillingEntrySpeed, drillingExitSpeed, coordinateByLength, coordinateByBreadth);
    }
}
