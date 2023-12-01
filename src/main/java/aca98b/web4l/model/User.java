package aca98b.web4l.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="users", schema="s367845")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    String login;
    String password;
}
