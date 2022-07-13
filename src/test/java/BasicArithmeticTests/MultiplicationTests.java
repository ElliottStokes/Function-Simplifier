package BasicArithmeticTests;

import org.FunctionSimplifier.BasicArithmetic.Multiplication;
import org.FunctionSimplifier.Function;
import org.FunctionSimplifier.Variable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MultiplicationTests {
    @Test
    void multiplicationToString() {
        Multiplication multi = new Multiplication(5, 3);
        Assertions.assertEquals("5 * 3", multi.toString());

        Multiplication multiWithDecimals = new Multiplication(7.3, 9.7);
        Assertions.assertEquals("7.3 * 9.7", multiWithDecimals.toString());

        Variable varX = new Variable("x");
        Variable varY = new Variable("y");
        Multiplication multi_XY = new Multiplication(varX, varY);
        Assertions.assertEquals("x * y", multi_XY.toString());

        Multiplication multiTwoFunctions = new Multiplication(multi, multiWithDecimals);
        Assertions.assertEquals("5 * 3 * 7.3 * 9.7", multiTwoFunctions.toString());

        Multiplication multiThreeFunctions = new Multiplication(multiTwoFunctions, multi_XY);
        Assertions.assertEquals("5 * 3 * 7.3 * 9.7 * x * y", multiThreeFunctions.toString());
    }

    @Test
    void multiplicationCommutativeRule() {
        Multiplication multi = new Multiplication(5, 3);
        Assertions.assertEquals("5 * 3", multi.toString());
        multi.applyCommutation();
        Assertions.assertEquals("3 * 5", multi.toString());

        Variable varX = new Variable("x");
        Variable varY = new Variable("y");
        Multiplication multi_XY = new Multiplication(varX, varY);
        Assertions.assertEquals("x * y", multi_XY.toString());
        multi_XY.applyCommutation();
        Assertions.assertEquals("y * x", multi_XY.toString());

        Multiplication multiTwoFunctions = new Multiplication(new Multiplication(5.5, 2), new Multiplication(3.14, 1.23));
        Assertions.assertEquals("5.5 * 2 * 3.14 * 1.23", multiTwoFunctions.toString());
        multiTwoFunctions.applyCommutation();
        Assertions.assertEquals("3.14 * 1.23 * 5.5 * 2", multiTwoFunctions.toString());
    }

    @Test
    void evaluateMultiplication() {
        Multiplication multi = new Multiplication(7, 4);
        Function multiResult = multi.evaluate();
        Assertions.assertEquals("28", multiResult.toString());

        Variable X = new Variable("x", 2); // 2x
        Variable Y = new Variable("y", 1, 2); //y^2
        Multiplication multi_XY = new Multiplication(X, Y); // 2x * y^2
        Variable multi_XYResult = multi_XY.evaluate(10, 3);
        Assertions.assertEquals("180", multi_XYResult.toString());

        Variable X2 =  new Variable("x", 3); // 3x
        Multiplication add_2x3x = new Multiplication(X, X2); // 2x * 3x
        Function add_2x3xResult = add_2x3x.evaluate();
        Assertions.assertEquals("6x^2", add_2x3xResult.toString());

        Multiplication multi_constantAndX = new Multiplication(6, X);
        Function multi_constantAndXResult = multi_constantAndX.evaluate();
        Assertions.assertEquals("12x", multi_constantAndXResult.toString());

        Multiplication multi_xAndConstant = new Multiplication(X2, 5.5);
        Function multi_xAndConstantResult = multi_xAndConstant.evaluate();
        Assertions.assertEquals("16.5x", multi_xAndConstantResult.toString());

        Multiplication multiTwoFunctions = new Multiplication(multi, new Multiplication(new Variable("x", 2), new Variable("y", 1, 2))); // 7 * 4 * 2x * y^2
        Function multiTwoFunctionsResult = multiTwoFunctions.evaluate(); // 56x * y^2
        Assertions.assertEquals("56x * y^2", multiTwoFunctionsResult.toString());
    }
}
