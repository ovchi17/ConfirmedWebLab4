package aca98b.web4l.model.response;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

@Data
@SuperBuilder
public class Response {
    LocalDateTime timeStamp;
    int statusCode;
    HttpStatus status;
    String message;
    String devMessage;
}
