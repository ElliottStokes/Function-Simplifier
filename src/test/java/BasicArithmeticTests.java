import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BasicArithmeticTests {
    @Test
    void additionToString() {
        Addition add = new Addition(2, 3);
        assertEquals("2 + 3", add.toString());
    }

    @Test
    void additionConfluenceRule() {
        Addition add = new Addition(2, 3);
        if (add.isConfluent()) {
            add.swapValues();
        }
        assertEquals("3 + 2", add.toString());
    }
}
