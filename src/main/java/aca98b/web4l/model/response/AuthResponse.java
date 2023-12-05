package aca98b.web4l.model.response;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AuthResponse extends Response {
    String jwt;
}
