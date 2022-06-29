package BasicArithmeticTests;

import org.FunctionSimplifier.BasicArithmetic.Division;
import org.FunctionSimplifier.Variable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DivisionTests {
    @Test
    void divisionToString() {
        Division div = new Division(125, 5);
        Assertions.assertEquals("125 / 5", div.toString());

        Division divDecimals = new Division(17.5, 1.25);
        Assertions.assertEquals("17.5 / 1.25", divDecimals.toString());

        Division div_XY = new Division(new Variable("x"), new Variable("y"));
        Assertions.assertEquals("x / y", div_XY.toString());
    }

    @Test
    void divisionEvaluate() {
        Division div = new Division(125, 5);
        Variable divResult = div.evaluate();
        Assertions.assertEquals("25", divResult.toString());

        Division divDecimals = new Division(17.5, 1.25);
        Variable divDecimalsResult = divDecimals.evaluate();
        Assertions.assertEquals("14", divDecimalsResult.toString());

        Division div_XY = new Division(new Variable("x"), new Variable("y"));
        Variable div_XYResult = div_XY.evaluate(30, 3);
        Assertions.assertEquals("10", div_XYResult.toString());
        div_XYResult = div_XY.evaluate(12, 0.75);
        Assertions.assertEquals("16", div_XYResult.toString());

        Division divExponential = new Division(new Variable("n", 1, 2), new Variable("m", 1, 3));
        Variable divExponentialResult = divExponential.evaluate(6, 2);
        Assertions.assertEquals("4.5", divExponentialResult.toString());

        Division divSameLabel = new Division(new Variable("x", 10, 4), new Variable("x", 5, 2));
        Variable divSameLabelResult = divSameLabel.evaluate();
        Assertions.assertEquals("2x^2", divSameLabelResult.toString());

        Division divSameLabelSameExp = new Division(new Variable("x", 9, 1), new Variable("x", 3, 1));
        Variable divSameLabelSameExpResult = divSameLabelSameExp.evaluate();
        Assertions.assertEquals("3", divSameLabelSameExpResult.toString());
    }
}
