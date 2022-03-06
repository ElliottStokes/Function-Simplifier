import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BasicArithmeticTests {
    @Test
    void additionToString() {
        Addition add = new Addition(2, 3);
        assertEquals("2.0 + 3.0", add.toString());
    }

    @Test
    void additionConfluenceRule() {
        Addition add = new Addition(2, 3);
        if (add.isConfluent()) {
            add.swapValues();
        }
        assertEquals("3.0 + 2.0", add.toString());
    }

    @Test
    void variablesToString() {
        Variable variable1 = new Variable(5);
        Variable variable2 = new Variable("x");
        Variable variable3 = new Variable("x", 3);
        Variable variable4 = new Variable("x", 5, 2);
        assertEquals("5.0", variable1.toString());
        assertEquals("x", variable2.toString());
        assertEquals("3.0x", variable3.toString());
        assertEquals("5.0x^2.0", variable4.toString());
    }

    @Test
    void evaluateVariable() {
        Variable variable1 = new Variable("x");
        Variable variable2 = new Variable("x", 3);
        Variable variable3 = new Variable("x", 5, 2);
        assertEquals(5, variable1.evaluate(5));
        assertEquals(15, variable2.evaluate(5));
        assertEquals(125, variable3.evaluate(5));
    }
}
