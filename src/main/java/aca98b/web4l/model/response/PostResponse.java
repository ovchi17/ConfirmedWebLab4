package aca98b.web4l.model.response;

public class PostResponse {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "PostResponse{" +
                "message='" + message + '\'' +
                '}';
    }
}
