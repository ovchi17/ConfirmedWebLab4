package aca98b.web4l.model.response;

public class AuthSessionResponse {
    private String sessionID;

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    @Override
    public String toString() {
        return "AuthSessionResponse{" +
                "sessionID='" + sessionID + '\'' +
                '}';
    }
}
