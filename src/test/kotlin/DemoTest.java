import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DemoTest {

    @Test
    void shouldAddNumbers() {
        assertEquals(4, NumbersAdder.add(2, 2));
    }
}
