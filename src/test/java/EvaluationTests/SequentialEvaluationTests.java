package EvaluationTests;

import org.FunctionSimplifier.SequentialEvaluation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SequentialEvaluationTests {
    SequentialEvaluation sequentialEvaluation = new SequentialEvaluation();

    @Test
    public void testOperators() {
        Assertions.assertEquals("6", sequentialEvaluation.evaluate("( 1 + 2 ) + 3"));
        Assertions.assertEquals("10", sequentialEvaluation.evaluate("( ( 1 + 2 ) + 3 ) + 4"));
        Assertions.assertEquals("4", sequentialEvaluation.evaluate("2 ^ 2"));
        Assertions.assertEquals("16", sequentialEvaluation.evaluate("( 2 ^ 2 ) ^ 2"));
        Assertions.assertEquals("1", sequentialEvaluation.evaluate("10 ^ 0"));
        Assertions.assertEquals("25", sequentialEvaluation.evaluate("5 * 5"));
        Assertions.assertEquals("50", sequentialEvaluation.evaluate("5 * 5 * 2"));
        Assertions.assertEquals("50", sequentialEvaluation.evaluate("( 5 * 5 ) * 2"));
        Assertions.assertEquals("10", sequentialEvaluation.evaluate("100 / 10"));
        Assertions.assertEquals("1", sequentialEvaluation.evaluate("100 / 100"));
        Assertions.assertEquals("10", sequentialEvaluation.evaluate("1 + 2 + 3 + 4"));
        Assertions.assertEquals("3", sequentialEvaluation.evaluate("1 + 2"));
    }

    @Test
    public void testCombinationOfOperators() {
        Assertions.assertEquals("10", sequentialEvaluation.evaluate("5 + 10 - 5"));
        Assertions.assertEquals("45", sequentialEvaluation.evaluate("5 * 10 - 5"));
        Assertions.assertEquals("25", sequentialEvaluation.evaluate("5 * ( 10 - 5 )"));
        Assertions.assertEquals("-7", sequentialEvaluation.evaluate("5 - 10 / 1 - 2"));
        Assertions.assertEquals("5", sequentialEvaluation.evaluate("( 5 - 10 ) / ( 1 - 2 )"));
    }


    @Test
    public void testAlgebraValues() {
        Assertions.assertEquals("x + 10", sequentialEvaluation.evaluate("x + 5 + 5"));
        Assertions.assertEquals("2x", sequentialEvaluation.evaluate("x + x"));
        Assertions.assertEquals("2x + y^2", sequentialEvaluation.evaluate("x + x + y * y"));
        Assertions.assertEquals("0", sequentialEvaluation.evaluate("5x + 2x - 7x"));
        Assertions.assertEquals("2x^2 + 5x - 7", sequentialEvaluation.evaluate("x^2 + x^2 + 3x + 2x - 7"));
        Assertions.assertEquals("x^4", sequentialEvaluation.evaluate("x ^ 2 * x ^ 2"));
        Assertions.assertEquals("xy", sequentialEvaluation.evaluate("x * y"));
    }
}
