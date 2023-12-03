package aca98b.web4l.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class AuthResponse extends Response {
    String sessionId;
    boolean sessionIdNonExpired;
}
