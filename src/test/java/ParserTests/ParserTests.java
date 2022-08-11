package ParserTests;

import org.FunctionSimplifier.ReversePolishNotation.RPNParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParserTests {
    RPNParser rpnParser = new RPNParser();
    @Test
    public void operatorTests() {
        Assertions.assertEquals("xy^", rpnParser.parse("x^y"));
        Assertions.assertEquals("xy/", rpnParser.parse("x/y"));
        Assertions.assertEquals("xy*", rpnParser.parse("x*y"));
        Assertions.assertEquals("xy+", rpnParser.parse("x+y"));
        Assertions.assertEquals("xy-", rpnParser.parse("x-y"));
    }

    @Test
    public void basicFunctionTests() {
        Assertions.assertEquals("xy+zp*-", rpnParser.parse("x + y - z * p"));
        Assertions.assertEquals("xy*zx/+", rpnParser.parse("x * y + z / x"));
        Assertions.assertEquals("xy^", rpnParser.parse("x ^ y"));
    }

    @Test
    public void bracketTests() {
        Assertions.assertEquals("xyzm-/+", rpnParser.parse("x + y / (z - m)"));
        Assertions.assertEquals("xy-zm-/", rpnParser.parse("(x - y) / (z - m)"));
        Assertions.assertEquals("xy+vx/y*-z-", rpnParser.parse("x + y - v / x * y - z"));
        Assertions.assertEquals("xy+v-x/y*z-", rpnParser.parse("(((((x + y) - v) / x) * y) - z)"));
    }
}
