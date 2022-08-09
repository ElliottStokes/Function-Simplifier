package BasicArithmeticTests;

import org.FunctionSimplifier.BasicArithmetic.Addition;
import org.FunctionSimplifier.BasicArithmetic.Multiplication;
import org.FunctionSimplifier.Function;
import org.FunctionSimplifier.Variable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AdditionTests {
    @Test
    void additionToString() {
        Addition add = new Addition(2, 3);
        Assertions.assertEquals("2 + 3", add.toString());

        Addition addWithDecimals = new Addition(5.3, 7.9);
        Assertions.assertEquals("5.3 + 7.9", addWithDecimals.toString());

        Variable varX = new Variable("x");
        Variable varY = new Variable("y");
        Addition add_XY = new Addition(varX, varY);
        Assertions.assertEquals("x + y", add_XY.toString());
    }

    @Test
    void additionCommutativeRule() {
        Addition add = new Addition(2, 3);
        Assertions.assertEquals("2 + 3", add.toString());
        add.applyCommutation();
        Assertions.assertEquals("3 + 2", add.toString());

        Variable X = new Variable("x");
        Variable Y = new Variable("y");
        Addition add_XY = new Addition(X, Y);
        Assertions.assertEquals("x + y", add_XY.toString());
        add_XY.applyCommutation();
        Assertions.assertEquals("y + x", add_XY.toString());
    }

    @Test
    void evaluateAddition() {
        Addition add = new Addition(4, 7); // 4 + 7
        Function addResult = add.evaluate();
        Assertions.assertEquals("11", addResult.toString());

        Variable X = new Variable("x", 2); // 2x
        Variable Y = new Variable("y", 1, 2); //y^2
        Addition add_XY = new Addition(X, Y); // 2x + y^2
        Function add_XYResult = add_XY.evaluate();
        Assertions.assertEquals("2x + y^2", add_XYResult.toString());

        Variable X2 =  new Variable("x", 3); // 3x
        Addition add_2x3x = new Addition(X, X2); // 2x + 3x
        Function add_2x3xResult = add_2x3x.evaluate();
        Assertions.assertEquals("5x", add_2x3xResult.toString());

        Addition addThreeNumbers = new Addition(new Addition(new Variable(5), new Variable(7)), new Function(new Variable(10))); // 5 + 7 + 10
        Function addThreeNumbersResult = addThreeNumbers.evaluate(); // 22
        Assertions.assertEquals("22", addThreeNumbersResult.toString());

        Addition addTwoFunctions = new Addition(new Addition(7, 4), new Addition(new Variable("x", 2), new Variable("y", 1, 2))); // 7 + 4 + 2x + y^2
        Function addTwoFunctionsResult = addTwoFunctions.evaluate(); // 11 + 2x + y^2
        Assertions.assertEquals("11 + 2x + y^2", addTwoFunctionsResult.toString());

        Addition addTwoFunctions2 = new Addition(new Addition(new Variable("x", 5), new Variable("y", 7)), new Addition(new Variable("x", 9), new Variable("y", 3))); // 5x + 7x + 9y + 3y
        Function addTwoFunctions2Result = addTwoFunctions2.evaluate(); // 21x + 3y
        Assertions.assertEquals("21x + 3y", addTwoFunctions2Result.toString());
    }

    @Test
    void variablesToString() {
        Variable variable1 = new Variable(5);
        Variable variable2 = new Variable("x");
        Variable variable3 = new Variable("x", 3);
        Variable variable4 = new Variable("x", 5, 2);
        Assertions.assertEquals("5", variable1.toString());
        Assertions.assertEquals("x", variable2.toString());
        Assertions.assertEquals("3x", variable3.toString());
        Assertions.assertEquals("5x^2", variable4.toString());
    }

/*    @Test
    void evaluateVariable() {
        Variable variable1 = new Variable("x");
        Variable variable2 = new Variable("x", 3);
        Variable variable3 = new Variable("x", 5, 2);
        Assertions.assertEquals(5, variable1.evaluate(5));
        Assertions.assertEquals(15, variable2.evaluate(5));
        Assertions.assertEquals(125, variable3.evaluate(5));
    }*/
}
