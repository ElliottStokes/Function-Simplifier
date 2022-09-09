import org.FunctionSimplifier.SyntaxTree.SyntaxTree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ComparisonTests {
    @Test
    public void test1() {
        Assertions.assertEquals("2", new SyntaxTree("1 + 1").simplify());
    }
    @Test
    public void test2() {
        Assertions.assertEquals("444", new SyntaxTree("123 + 321").simplify());
    }
    @Test
    public void test3() {
        Assertions.assertEquals("15", new SyntaxTree("1 + 2 + 3 + 4 + 5").simplify());
    }
    @Test
    public void test4() {
        Assertions.assertEquals("2x", new SyntaxTree("x + x").simplify());
    }
    @Test
    public void test5() {
        Assertions.assertEquals("x+y", new SyntaxTree("x + y").simplify());
    }
    @Test
    public void test6() {
        Assertions.assertEquals("2x+2y", new SyntaxTree("x + x + y + y").simplify());
    }
    @Test
    public void test7() {
        Assertions.assertEquals("2x+2y", new SyntaxTree("x + y + x + y").simplify());
    }
    @Test
    public void test8() {
        Assertions.assertEquals("17x", new SyntaxTree("3x + 5x + 9x").simplify());
    }
    @Test
    public void test9() {
        Assertions.assertEquals("2x+7", new SyntaxTree("x + x + 5 + 2").simplify());
    }
    @Test
    public void test10() {
        Assertions.assertEquals("6+3x", new SyntaxTree("1 + x + 2 + x + 3 + x").simplify());
    }
    @Test
    public void test11() {
        Assertions.assertEquals("8", new SyntaxTree("10 - 2").simplify());
    }
    @Test
    public void test12() {
        Assertions.assertEquals("-10", new SyntaxTree("10 - 8 - 6 - 4 - 2").simplify());
    }
    @Test
    public void test13() {
        Assertions.assertEquals("4x", new SyntaxTree("5x - x").simplify());
    }
    @Test
    public void test14() {
        Assertions.assertEquals("-4x", new SyntaxTree("x - 5x").simplify());
    }
    @Test
    public void test15() {
        Assertions.assertEquals("x-y", new SyntaxTree("x - y").simplify());
    }
    @Test
    public void test16() {
        Assertions.assertEquals("-3x-3y", new SyntaxTree("3x - 6x - y - 2y").simplify());
    }
    @Test
    public void test17() {
        Assertions.assertEquals("7x-6y", new SyntaxTree("10x - 5y - 3x - y").simplify());
    }
    @Test
    public void test18() {
        Assertions.assertEquals("x", new SyntaxTree("10x - 8x - x").simplify());
    }
    @Test
    public void test19() {
        Assertions.assertEquals("5x15", new SyntaxTree("5x - 10 - 5").simplify());
    }
    @Test
    public void test20() {
        Assertions.assertEquals("5-2x", new SyntaxTree("5 - x - 10 - -3x").simplify());
    }
    @Test
    public void test21() {
        Assertions.assertEquals("15", new SyntaxTree("5 * 3").simplify());
    }
    @Test
    public void test22() {
        Assertions.assertEquals("40", new SyntaxTree("2 * 4 * 5").simplify());
    }
    @Test
    public void test23() {
        Assertions.assertEquals("x^2", new SyntaxTree("x * x").simplify());
    }
    @Test
    public void test24() {
        Assertions.assertEquals("18x^2", new SyntaxTree("3x * 6x").simplify());
    }
    @Test
    public void test25() {
        Assertions.assertEquals("x*y", new SyntaxTree("x * y").simplify());
    }
    @Test
    public void test26() {
        Assertions.assertEquals("x^2*y^2", new SyntaxTree("x * x * y * y").simplify());
    }
    @Test
    public void test27() {
        Assertions.assertEquals("x^2*y^2", new SyntaxTree("x * y * x * y").simplify());
    }
    @Test
    public void test28() {
        Assertions.assertEquals("x^7", new SyntaxTree("x * ( x * x^2 * x^3 )").simplify());
    }
    @Test
    public void test29() {
        Assertions.assertEquals("45x^2", new SyntaxTree("x * 3 * 3x * 5").simplify());
    }
    @Test
    public void test30() {
        Assertions.assertEquals("-150x^2", new SyntaxTree("5 * x * 10 * -3x").simplify());
    }
    @Test
    public void test31() {
        Assertions.assertEquals("5", new SyntaxTree("100 / 20").simplify());
    }
    @Test
    public void test32() {
        Assertions.assertEquals("5", new SyntaxTree("50 / 5 / 2").simplify());
    }
    @Test
    public void test33() {
        Assertions.assertEquals("1", new SyntaxTree("x / x").simplify());
    }
    @Test
    public void test34() {
        Assertions.assertEquals("3", new SyntaxTree("9x / 3x").simplify());
    }
    @Test
    public void test35() {
        Assertions.assertEquals("x/y", new SyntaxTree("x / y").simplify());
    }
    @Test
    public void test36() {
        Assertions.assertEquals("1/y^2", new SyntaxTree("x / x / y / y").simplify());
    }
    @Test
    public void test37() {
        Assertions.assertEquals("1/y^2", new SyntaxTree("x / y / x / y").simplify());
    }
    @Test
    public void test38() {
        Assertions.assertEquals("1+x+x^2", new SyntaxTree("( x + x^2 + x^3 ) / x").simplify());
    }
    @Test
    public void test39() {
        Assertions.assertEquals("1", new SyntaxTree("10x / 2 / x / 5").simplify());
    }
    @Test
    public void test40() {
        Assertions.assertEquals("0.167", new SyntaxTree("5 / x / 10 / -3x").simplify());
    }
    @Test
    public void test41() {
        Assertions.assertEquals("2x^2", new SyntaxTree("x ^ 2 + x ^ 2").simplify());
    }
    @Test
    public void test42() {
        Assertions.assertEquals("3x^2", new SyntaxTree("5x ^ 2 - 2x ^ 2").simplify());
    }
    @Test
    public void test43() {
        Assertions.assertEquals("x^5", new SyntaxTree("x ^ 2 * x ^ 3").simplify());
    }
    @Test
    public void test44() {
        Assertions.assertEquals("15x^7", new SyntaxTree("3x ^ 5 * 5x ^ 2").simplify());
    }
    @Test
    public void test45() {
        Assertions.assertEquals("x", new SyntaxTree("x ^ 3 / x ^ 2").simplify());
    }
    @Test
    public void test46() {
        Assertions.assertEquals("x^3+y^-2", new SyntaxTree("x ^ 5 / x ^ 2 + y / y ^ 3").simplify());
    }
    @Test
    public void test47() {
        Assertions.assertEquals("6x^3", new SyntaxTree("x ^ 5 / x ^ 2 + 5x ^ 3").simplify());
    }
    @Test
    public void test48() {
        Assertions.assertEquals("2x+2x^2+2x^3", new SyntaxTree("x + x + x ^ 2 + x ^ 2 + x ^ 3 + x ^ 3").simplify());
    }
    @Test
    public void test49() {
        Assertions.assertEquals("2x+2x^2+2x^3", new SyntaxTree("x + x ^ 2 + x ^ 3 + x + x ^ 2 + x ^ 3").simplify());
    }
    @Test
    public void test50() {
        Assertions.assertEquals("x^9", new SyntaxTree("x ^ 2 * x ^ 3 / x * x ^ 5").simplify());
    }
}
