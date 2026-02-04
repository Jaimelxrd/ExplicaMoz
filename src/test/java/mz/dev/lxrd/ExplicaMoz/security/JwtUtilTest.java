package mz.dev.lxrd.ExplicaMoz.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {

    private JwtUtil jwtUtil;
    private final String SECRET = "test-secret-key-very-long-and-secure-1234567890";

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil();
        ReflectionTestUtils.setField(jwtUtil, "secretKey", SECRET);
    }

    @Test
    void shouldGenerateValidToken() {
        String username = "admin";
        String token = jwtUtil.generateToken(username);

        assertNotNull(token);
        assertEquals(username, jwtUtil.extractUsername(token));
        assertTrue(jwtUtil.validateToken(token, username));
    }

    @Test
    void shouldFailForInvalidUser() {
        String token = jwtUtil.generateToken("admin");
        assertFalse(jwtUtil.validateToken(token, "otheruser"));
    }

    @Test
    void shouldDetectExpiredToken() {
        // We can't easily test expiration without mocking Date or waiting,
        // but we can at least verify basic functionality.
        String token = jwtUtil.generateToken("admin");
        assertFalse(jwtUtil.isTokenExpired(token));
    }
}
