package by.upmebel.upmecutfile.domain;

import jakarta.persistence.*;
import jakarta.validation.Constraint;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "element_sides")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ElementSide implements Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private double length;

    private double breadth;

    @ManyToOne
    @JoinColumn(name = "element_id", referencedColumnName = "id")
    private Element element;

    @OneToMany(
            mappedBy = "elementSide",
            cascade = CascadeType.ALL
    )
    private List<Hole> holes;

    public ElementSide(double length, double breadth) {
        this.length = length;
        this.breadth = breadth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ElementSide that = (ElementSide) o;
        return Double.compare(length, that.length) == 0 && Double.compare(breadth, that.breadth) == 0 && Objects.equals(id, that.id) && Objects.equals(element, that.element) && Objects.equals(holes, that.holes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, length, breadth);
    }

    @Override
    public ElementSide clone() {
        try {
            ElementSide clone = (ElementSide) super.clone();
            clone.setId(null);
            clone.setHoles(null);
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
