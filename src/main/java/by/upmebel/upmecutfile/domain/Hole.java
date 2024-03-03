package by.upmebel.upmecutfile.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "holes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hole {

    @Id
    private Long id;

    private double diameter;

    private double depth;

    private int drillingEntrySpeed;

    private int drillingExitSpeed;

    private String coordinateByLength;

    private String coordinateByBreadth;

    @ManyToOne(fetch = FetchType.LAZY)
    private ElementSide elementSide;
}
