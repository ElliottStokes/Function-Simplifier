package ParserTests;

import org.FunctionSimplifier.ReversePolishNotation.RPNParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParserTests {
    RPNParser rpnParser = new RPNParser();
    @Test
    public void operatorTests() {
        Assertions.assertEquals("x y ^", rpnParser.parse("x ^ y"));
        Assertions.assertEquals("x y /", rpnParser.parse("x / y"));
        Assertions.assertEquals("x y *", rpnParser.parse("x * y"));
        Assertions.assertEquals("x y +", rpnParser.parse("x + y"));
        Assertions.assertEquals("x y -", rpnParser.parse("x - y"));
    }

    @Test
    public void basicFunctionTests() {
        Assertions.assertEquals("x y + z p * -", rpnParser.parse("x + y - z * p"));
        Assertions.assertEquals("x y * z x / +", rpnParser.parse("x * y + z / x"));
        Assertions.assertEquals("x y ^", rpnParser.parse("x ^ y"));
    }

    @Test
    public void bracketTests() {
        Assertions.assertEquals("x y ( z m - ) / +", rpnParser.parse("x + y / ( z - m )"));
        Assertions.assertEquals("( x y - ) ( z m - ) /", rpnParser.parse("( x - y ) / ( z - m )"));
        Assertions.assertEquals("x y + v x / y * - z -", rpnParser.parse("x + y - v / x * y - z"));
        Assertions.assertEquals("( ( ( ( ( x y + ) v - ) x / ) y * ) z - )", rpnParser.parse("( ( ( ( ( x + y ) - v ) / x ) * y ) - z )"));
    }


    @Test
    public void numbersTest() {
        Assertions.assertEquals("5 10 +", rpnParser.parse("5 + 10"));
        Assertions.assertEquals("x 5 ^", rpnParser.parse("x ^ 5"));
        Assertions.assertEquals("5x 7x +", rpnParser.parse("5x + 7x"));
    }
}
