package aca98b.web4l.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;

@Entity
@Table(name="aca98b_users", schema="s367845")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="users_id_seq")
    @SequenceGenerator(name = "users_id_seq", sequenceName = "users_id_seq", allocationSize = 1)
    Long id;
    @Column
    String username;
    @Column
    String password;
    @Column("session_id")
    String sessionId;
    @Column("session_non_expired")
    boolean sessionNonExpired;
}
