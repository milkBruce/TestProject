import org.example.service.Stage2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestCase2 {
    @Test
    void testEmptyString() {
        assertEquals("d", Stage2.process("abcccbad"));
    }
}
