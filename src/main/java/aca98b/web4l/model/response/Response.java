package aca98b.web4l.model.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    LocalDateTime timeStamp;
    int statusCode;
    HttpStatus status;
    String message;
    String devMessage;
}
