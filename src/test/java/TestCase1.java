import org.example.service.Stage1;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestCase1 {

    @Test
    void testComplexDeletionChain() {
        assertEquals("d", Stage1.removeConsecutiveChars("aabcccbbad"));
    }
}
