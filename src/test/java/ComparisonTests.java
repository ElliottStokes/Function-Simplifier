import org.FunctionSimplifier.BasicArithmetic.Addition;
import org.FunctionSimplifier.BasicArithmetic.Division;
import org.FunctionSimplifier.BasicArithmetic.Multiplication;
import org.FunctionSimplifier.BasicArithmetic.Subtraction;
import org.FunctionSimplifier.Variable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ComparisonTests {
    @Test
    public void test1() {
        Assertions.assertEquals("2", new Addition(1, 1).evaluate().toString());
    }
    @Test
    public void test2() {
        Assertions.assertEquals("444", new Addition(123, 321).evaluate().toString());
    }
    @Test
    public void test3() {
        //Assertions.assertEquals("15", new Addition(new Addition(1, 2), new Addition(3, new Addition(4, 5)))).evaluate().toString());
        Assertions.fail();
    }
    @Test
    public void test4() {
        Assertions.assertEquals("2x", new Addition(new Variable("x"), new Variable("x")).evaluate().toString());
    }
    @Test
    public void test5() {
        Assertions.assertEquals("x+y",  new Addition(new Variable("x"), new Variable("y")).evaluate().toString());
    }
    @Test
    public void test6() {
        Assertions.assertEquals("2x+2y", new Addition(new Addition(new Variable("x"), new Variable("x")), new Addition(new Variable("y"), new Variable("y"))).evaluate().toString());
    }
    @Test
    public void test7() {
        Assertions.assertEquals("2x+2y", new Addition(new Addition(new Variable("x"), new Variable("y")), new Addition(new Variable("x"), new Variable("y"))).evaluate().toString());
    }
    @Test
    public void test8() {
        //Assertions.assertEquals("17x", new Addition(new Variable("3x"), new Addition(new Variable("5x"), new Variable("9x"))).evaluate().toString());
        Assertions.fail();
    }
    @Test
    public void test9() {
        Assertions.assertEquals("2x+7", new Addition(new Addition(new Variable("x"), new Variable("x")), new Addition(5,2)).evaluate().toString());
    }
    @Test
    public void test10() {
        //Assertions.assertEquals("6+3x", new Addition(new Addition(1, new Addition(new Variable("x"), 2), new Addition(new Variable("x"), new Addition(3, new Variable("x"))).evaluate().toString());
        Assertions.fail();
    }
    @Test
    public void test11() {
        Assertions.assertEquals("8", new Subtraction(10, 2).evaluate().toString());
    }
    @Test
    public void test12() {
        //Assertions.assertEquals("-10", new Subtraction(new Subtraction(10, new Subtraction(8, 6)), new Subtraction(4, - 2)).evaluate().toString());
        Assertions.fail();
    }
    @Test
    public void test13() {
        Assertions.assertEquals("4x", new Subtraction(new Variable("x", 5), new Variable("x")).evaluate().toString());
    }
    @Test
    public void test14() {
        Assertions.assertEquals("-4x", new Subtraction(new Variable("x"), new Variable("x", 5)).evaluate().toString());
    }
    @Test
    public void test15() {
        Assertions.assertEquals("x-y", new Subtraction(new Variable("x"),new Variable("y")).evaluate().toString());
    }
    @Test
    public void test16() {
        //Assertions.assertEquals("-3x-3y", new Subtraction(new Subtraction(new Variable("x", 3), new Variable("x", 6)), new Subtraction(new Variable("y"), new Variable("y", 2))).evaluate().toString());
        Assertions.fail();
    }
    @Test
    public void test17() {
        //Assertions.assertEquals("7x-6y", new Subtraction(new Subtraction(new Variable("x", 10), new Variable("y", 5)), new Subtraction(new Variable("y", 3), new Variable("y"))).evaluate().toString());
        Assertions.fail();
    }
    @Test
    public void test18() {
        //Assertions.assertEquals("x", new Subtraction(new Variable("x", 10), new Subtraction(new Variable("x", 8), new Variable("x"))).evaluate().toString());
        Assertions.fail();
    }
    @Test
    public void test19() {
        //Assertions.assertEquals("5x15", new Subtraction(new Variable("x", 5), new Subtraction(10, 5)).evaluate().toString());
        Assertions.fail();
    }
    @Test
    public void test20() {
        //Assertions.assertEquals("5-2x", new Subtraction(new Subtraction(new Variable(5), new Variable("x")), new Subtraction(new Variable(10), new Variable("x", -3))).evaluate().toString());
        Assertions.fail();
    }
    @Test
    public void test21() {
        Assertions.assertEquals("15", new Multiplication(5,3).evaluate().toString());
    }
    @Test
    public void test22() {
        //Assertions.assertEquals("40", new Multiplication(2, new Multiplication(4, 5)).evaluate().toString());
        Assertions.fail();
    }
    @Test
    public void test23() {
        Assertions.assertEquals("x^2", new Multiplication(new Variable("x"), new Variable("x")).evaluate().toString());
    }
    @Test
    public void test24() {
        Assertions.assertEquals("18x^2", new Multiplication(new Variable("x", 3), new Variable("x", 6)).evaluate().toString());
    }
    @Test
    public void test25() {
        Assertions.assertEquals("x*y", new Multiplication(new Variable("x"), new Variable("y")).evaluate().toString());
    }
    @Test
    public void test26() {
        Assertions.assertEquals("x^2*y^2", new Multiplication(new Multiplication(new Variable("x"), new Variable("x")), new Multiplication(new Variable("y"), new Variable("y"))).evaluate().toString());
    }
    @Test
    public void test27() {
        Assertions.assertEquals("x^2*y^2", new Multiplication(new Multiplication(new Variable("x"), new Variable("y")), new Multiplication(new Variable("x"), new Variable("y"))).evaluate().toString());
    }
    @Test
    public void test28() {
        // No representation of brackets
        Assertions.fail();
    }
    @Test
    public void test29() {
        Assertions.assertEquals("45x^2", new Multiplication(new Multiplication(new Variable("x"), new Variable(3)), new Multiplication(new Variable("x", 3), new Variable(5))).evaluate().toString());
    }
    @Test
    public void test30() {
        Assertions.assertEquals("-150x^2", new Multiplication(new Multiplication(new Variable(5), new Variable("x")), new Multiplication(new Variable(10), new Variable("x", -3))).evaluate().toString());
    }
    @Test
    public void test31() {
        Assertions.assertEquals("5", new Division(100, 20).evaluate().toString());
    }
    @Test
    public void test32() {
        //Assertions.assertEquals("5", new Division(50, new Division(5, 2)).evaluate().toString());
        Assertions.fail();
    }
    @Test
    public void test33() {
        Assertions.assertEquals("1", new Division(new Variable("x"), new Variable("x")).evaluate().toString());
    }
    @Test
    public void test34() {
        Assertions.assertEquals("3", new Division(new Variable("x", 9), new Variable("x", 3)).evaluate().toString());
    }
    @Test
    public void test35() {
        Assertions.assertEquals("x/y", new Division(new Variable("x"), new Variable("y")).evaluate().toString());
    }
    @Test
    public void test36() {
        //Assertions.assertEquals("1/y^2", new Division(new Division(new Variable("x"), new Variable("x")), new Division(new Variable("y"), new Variable("y"))).evaluate().toString());
        Assertions.fail();
    }
    @Test
    public void test37() {
        //Assertions.assertEquals("1/y^2", new Division(new Division(new Variable("x"), new Variable("y")), new Division(new Variable("x"), new Variable("y"))).evaluate().toString());
        Assertions.fail();
    }
    @Test
    public void test38() {
        // No representation of brackets
        Assertions.fail();
    }
    @Test
    public void test39() {
        //Assertions.assertEquals("1", new Division(new Division(new Variable("x", 10), new Variable(2)), new Division(new Variable("x"), new Variable(5))).evaluate().toString());
        Assertions.fail();
    }
    @Test
    public void test40() {
        //Assertions.assertEquals("0.167", new Division(new Division(new Variable(5), new Variable("x")), new Division(new Variable(10), new Variable("x", -3))).evaluate().toString());
        Assertions.fail();
    }
    @Test
    public void test41() {
        Assertions.assertEquals("2x^2", new Addition(new Variable("x", 1, 2), new Variable("x", 1, 2)).evaluate().toString());
    }
    @Test
    public void test42() {
        Assertions.assertEquals("3x^2", new Subtraction(new Variable("x", 5, 2), new Variable("x", 2, 2)).evaluate().toString());
    }
    @Test
    public void test43() {
        Assertions.assertEquals("x^5", new Multiplication(new Variable("x", 1, 2), new Variable("x", 1, 3)).evaluate().toString());
    }
    @Test
    public void test44() {
        Assertions.assertEquals("15x^7", new Multiplication(new Variable("x", 3, 5), new Variable("x", 5, 2)).evaluate().toString());
    }
    @Test
    public void test45() {
        Assertions.assertEquals("x", new Division(new Variable("x", 1, 3), new Variable("x", 1, 2)).evaluate().toString());
    }
    @Test
    public void test46() {
        // No notation of mixed operator functions
        Assertions.fail();
    }
    @Test
    public void test47() {
        // No notation of mixed operator functions
        Assertions.fail();
    }
    @Test
    public void test48() {
        //Assertions.assertEquals("2x+2x^2+2x^3", new Addition(new Addition(new Variable("x"), new Addition(new Variable("x"), new Variable("x", 1, 2))), new Addition(new Variable("x", 1, 2), new Addition(new Variable("x", 1, 3), new Variable("x", 1, 3)))).evaluate().toString());
        Assertions.fail();
    }
    @Test
    public void test49() {
        //Assertions.assertEquals("2x+2x^2+2x^3", new Addition(new Addition(new Variable("x"), new Addition(new Variable("x", 1, 2), new Variable("x", 1, 3))), new Addition(new Variable("x"), new Addition(new Variable("x", 1, 2), new Variable("x", 1, 3)))).evaluate().toString());
        Assertions.fail();
    }
    @Test
    public void test50() {
        // No notation of mixed operator functions
        Assertions.fail();
    }
}