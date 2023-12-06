package aca98b.web4l.model.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    private LocalDateTime timeStamp;
    private int statusCode;
    private HttpStatus status;
    private String message;
    private String devMessage;
}
