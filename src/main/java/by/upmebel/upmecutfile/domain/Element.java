package by.upmebel.upmecutfile.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "elements")
@Getter
@Setter
public class Element {

    @Id
    private Long id;

    private String name;

    @OneToMany(
            mappedBy = "element",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.REMOVE, CascadeType.PERSIST}
    )
    private List<ElementSide> sides;

}
