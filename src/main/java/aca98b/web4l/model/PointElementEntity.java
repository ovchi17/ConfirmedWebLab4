package aca98b.web4l.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;

@Entity
@Table(name="aca98b_points", schema="s367845")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PointElementEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="points_id_seq")
    @SequenceGenerator(name = "points_id_seq", sequenceName = "points_id_seq", allocationSize = 1)
    private Long id;

    private float x;

    private float y;

    private float r;

    private boolean result;

    private String time;

    @Column("exec_time")
    private String executionTime;

    @ManyToOne
    @JoinColumn(name = "owner")
    private UserEntity owner;
}
