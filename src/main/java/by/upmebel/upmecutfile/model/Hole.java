package by.upmebel.upmecutfile.model;

import jakarta.persistence.*;

@Entity
@Table(name = "holes")
public class Hole {

    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Component component;
}
