package BasicArithmeticTests;

import org.FunctionSimplifier.BasicArithmetic.Addition;
import org.FunctionSimplifier.BasicArithmetic.Subtraction;
import org.FunctionSimplifier.Function;
import org.FunctionSimplifier.Variable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SubtractionTests {
    @Test
    void subtractionToString() {
        Subtraction subtract = new Subtraction(2, 3);
        Assertions.assertEquals("2 - 3", subtract.toString());

        Subtraction subtractWithDecimals = new Subtraction(5.3, 7.9);
        Assertions.assertEquals("5.3 - 7.9", subtractWithDecimals.toString());

        Variable varX = new Variable("x");
        Variable varY = new Variable("y");
        Subtraction subtract_XY = new Subtraction(varX, varY);
        Assertions.assertEquals("x - y", subtract_XY.toString());
    }

    @Test
    void evaluateSubtraction() {
        Subtraction subtract = new Subtraction(4, 7); // 4 + 7
        Function subtractResult = subtract.evaluate();
        Assertions.assertEquals("-3", subtractResult.toString());

        Variable X = new Variable("x", 2); // 2x
        Variable Y = new Variable("y", 1, 2); //y^2
        Subtraction subtract_XY = new Subtraction(X, Y); // 2x + y^2
        Variable subtract_XYResult = subtract_XY.evaluate(5, 2);
        Assertions.assertEquals("6", subtract_XYResult.toString());

        Variable X2 =  new Variable("x", 3); // 3x
        Subtraction subtract_2x3x = new Subtraction(X, X2); // 2x + 3x
        Function subtract_2x3xResult = subtract_2x3x.evaluate();
        Assertions.assertEquals("-x", subtract_2x3xResult.toString());
    }
}
