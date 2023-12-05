package aca98b.web4l.model.response;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AuthResponse extends Response {
    String token;
//    boolean sessionIdNonExpired;
}
