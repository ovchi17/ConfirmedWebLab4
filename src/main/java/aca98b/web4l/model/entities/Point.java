package aca98b.web4l.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="aca98b_points", schema="s367845")
public class Point {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="points_id_seq")
    @SequenceGenerator(name = "points_id_seq", sequenceName = "points_id_seq", allocationSize = 1)
    private Long id;

    private float x;

    private float y;

    private float r;

    private boolean result;

    private String time;

    @Column("execution_time")
    private String executionTime;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User ownerId;
}
