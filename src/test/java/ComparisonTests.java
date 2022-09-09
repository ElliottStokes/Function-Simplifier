import org.FunctionSimplifier.SequentialEvaluation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ComparisonTests {
    SequentialEvaluation se = new SequentialEvaluation();
    @Test
    public void test1() {
        Assertions.assertEquals("2", se.evaluate("1 + 1"));
    }
    @Test
    public void test2() {
        Assertions.assertEquals("444", se.evaluate("123 + 321"));
    }
    @Test
    public void test3() {
        Assertions.assertEquals("15", se.evaluate("1 + 2 + 3 + 4 + 5"));
    }
    @Test
    public void test4() {
        Assertions.assertEquals("2x", se.evaluate("x + x"));
    }
    @Test
    public void test5() {
        Assertions.assertEquals("x + y", se.evaluate("x + y"));
    }
    @Test
    public void test6() {
        Assertions.assertEquals("2x + 2y", se.evaluate("x + x + y + y"));
    }
    @Test
    public void test7() {
        Assertions.assertEquals("2x + 2y", se.evaluate("x + y + x + y"));
    }
    @Test
    public void test8() {
        Assertions.assertEquals("17x", se.evaluate("3x + 5x + 9x"));
    }
    @Test
    public void test9() {
        Assertions.assertEquals("2x + 7", se.evaluate("x + x + 5 + 2"));
    }
    @Test
    public void test10() {
        Assertions.assertEquals("6 + 3x", se.evaluate("1 + x + 2 + x + 3 + x"));
    }
    @Test
    public void test11() {
        Assertions.assertEquals("8", se.evaluate("10 - 2"));
    }
    @Test
    public void test12() {
        Assertions.assertEquals("-10", se.evaluate("10 - 8 - 6 - 4 - 2"));
    }
    @Test
    public void test13() {
        Assertions.assertEquals("4x", se.evaluate("5x - x"));
    }
    @Test
    public void test14() {
        Assertions.assertEquals("-4x", se.evaluate("x - 5x"));
    }
    @Test
    public void test15() {
        Assertions.assertEquals("x - y", se.evaluate("x - y"));
    }
    @Test
    public void test16() {
        Assertions.assertEquals("-3x - 3y", se.evaluate("3x - 6x - y - 2y"));
    }
    @Test
    public void test17() {
        Assertions.assertEquals("7x - 6y", se.evaluate("10x - 5y - 3x - y"));
    }
    @Test
    public void test18() {
        Assertions.assertEquals("x", se.evaluate("10x - 8x - x"));
    }
    @Test
    public void test19() {
        Assertions.assertEquals("5x - 15", se.evaluate("5x - 10 - 5"));
    }
    @Test
    public void test20() {
        Assertions.assertEquals("5 - 2x", se.evaluate("5 - x - 10 - -3x"));
    }
    @Test
    public void test21() {
        Assertions.assertEquals("15", se.evaluate("5 * 3"));
    }
    @Test
    public void test22() {
        Assertions.assertEquals("40", se.evaluate("2 * 4 * 5"));
    }
    @Test
    public void test23() {
        Assertions.assertEquals("x^2", se.evaluate("x * x"));
    }
    @Test
    public void test24() {
        Assertions.assertEquals("18x^2", se.evaluate("3x * 6x"));
    }
    @Test
    public void test25() {
        Assertions.assertEquals("xy", se.evaluate("x * y"));
    }
    @Test
    public void test26() {
        Assertions.assertEquals("x^2 * y^2", se.evaluate("x * x * y * y"));
    }
    @Test
    public void test27() {
        Assertions.assertEquals("x^2 * y^2", se.evaluate("x * y * x * y"));
    }
    @Test
    public void test28() {
        Assertions.assertEquals("x^7", se.evaluate("x * ( x * x^2 * x^3 )"));
    }
    @Test
    public void test29() {
        Assertions.assertEquals("45x^2", se.evaluate("x * 3 * 3x * 5"));
    }
    @Test
    public void test30() {
        Assertions.assertEquals("-150x^2", se.evaluate("5 * x * 10 * -3x"));
    }
    @Test
    public void test31() {
        Assertions.assertEquals("5", se.evaluate("100 / 20"));
    }
    @Test
    public void test32() {
        Assertions.assertEquals("5", se.evaluate("50 / 5 / 2"));
    }
    @Test
    public void test33() {
        Assertions.assertEquals("1", se.evaluate("x / x"));
    }
    @Test
    public void test34() {
        Assertions.assertEquals("3", se.evaluate("9x / 3x"));
    }
    @Test
    public void test35() {
        Assertions.assertEquals("x / y", se.evaluate("x / y"));
    }
    @Test
    public void test36() {
        Assertions.assertEquals("1 / y^2", se.evaluate("x / x / y / y"));
    }
    @Test
    public void test37() {
        Assertions.assertEquals("1 / y^2", se.evaluate("x / y / x / y"));
    }
    @Test
    public void test38() {
        Assertions.assertEquals("1 + x + x^2", se.evaluate("( x + x^2 + x^3 ) / x"));
    }
    @Test
    public void test39() {
        Assertions.assertEquals("1", se.evaluate("10x / 2 / x / 5"));
    }
    @Test
    public void test40() {
        Assertions.assertEquals("0.167", se.evaluate("5 / x / 10 / -3x"));
    }
    @Test
    public void test41() {
        Assertions.assertEquals("2x^2", se.evaluate("x ^ 2 + x ^ 2"));
    }
    @Test
    public void test42() {
        Assertions.assertEquals("3x^2", se.evaluate("5x ^ 2 - 2x ^ 2"));
    }
    @Test
    public void test43() {
        Assertions.assertEquals("x^5", se.evaluate("x ^ 2 * x ^ 3"));
    }
    @Test
    public void test44() {
        Assertions.assertEquals("15x^7", se.evaluate("3x ^ 5 * 5x ^ 2"));
    }
    @Test
    public void test45() {
        Assertions.assertEquals("x", se.evaluate("x ^ 3 / x ^ 2"));
    }
    @Test
    public void test46() {
        Assertions.assertEquals("x^3 + y^-2", se.evaluate("x ^ 5 / x ^ 2 + y / y ^ 3"));
    }
    @Test
    public void test47() {
        Assertions.assertEquals("6x^3", se.evaluate("x ^ 5 / x ^ 2 + 5x ^ 3"));
    }
    @Test
    public void test48() {
        Assertions.assertEquals("2x + 2x^2 + 2x^3", se.evaluate("x + x + x ^ 2 + x ^ 2 + x ^ 3 + x ^ 3"));
    }
    @Test
    public void test49() {
        Assertions.assertEquals("2x + 2x^2 + 2x^3", se.evaluate("x + x ^ 2 + x ^ 3 + x + x ^ 2 + x ^ 3"));
    }
    @Test
    public void test50() {
        Assertions.assertEquals("x^9", se.evaluate("x ^ 2 * x ^ 3 / x * x ^ 5"));
    }
}
