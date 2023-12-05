package aca98b.web4l.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PointResponse extends Response{
    Map<String, ?> pointsData;
}