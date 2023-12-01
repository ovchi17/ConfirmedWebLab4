package aca98b.web4l.session;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.UUID;

@Component
public class SessionHandler {
    private static final HashMap<String, String> SESSIONS = new HashMap<>();
    private static final int TOKEN_LENGTH = 32;

    private String generateSessionID() {
        return new String(
                Base64.getEncoder().encode(
                        UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8)
                )
        );
    }

    public String register(final String username) {
        if (username == null) return null;

        final String sessionID = generateSessionID();
        SESSIONS.put(sessionID, username);
        System.out.println("CURRENT SESSION_ID IS " + sessionID);
        return sessionID;
    }

    public String getUsernameForSession(final String sessionID) {
        return SESSIONS.get(sessionID);
    }

    public void invalidate(final String token) {
        SESSIONS.remove(token);
    }
}
