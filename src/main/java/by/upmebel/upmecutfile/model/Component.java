package by.upmebel.upmecutfile.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "components")
public class Component {

    @Id
    private Long id;

    private Long height;

    private Long length;

    private Long width;

    @OneToMany
    private List<Hole> holes;

}
