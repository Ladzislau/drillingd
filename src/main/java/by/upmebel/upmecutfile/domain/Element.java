package by.upmebel.upmecutfile.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "elements")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Element {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Date createdAt;

    @OneToMany(
            mappedBy = "element",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<ElementSide> sides;

    public Element(Long id, String name, List<ElementSide> sides) {
        this.id = id;
        this.name = name;
        this.sides = sides;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Element element = (Element) o;
        return Objects.equals(id, element.id) && Objects.equals(name, element.name) && Objects.equals(createdAt, element.createdAt) && Objects.equals(sides, element.sides);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, createdAt);
    }
}
