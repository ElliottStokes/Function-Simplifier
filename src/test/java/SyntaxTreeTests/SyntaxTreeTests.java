package SyntaxTreeTests;

import org.FunctionSimplifier.SyntaxTree.SyntaxTree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SyntaxTreeTests {
    @Test
    public void creatingBinaryTreeTest() {
        SyntaxTree tree1 = new SyntaxTree("x + y");
        Assertions.assertEquals("+", tree1.getRootNode().toString());
        Assertions.assertEquals("x", tree1.getRootNode().getLeftNode().toString());
        Assertions.assertEquals("y", tree1.getRootNode().getRightNode().toString());

        SyntaxTree tree2 = new SyntaxTree("x + y + z");
        Assertions.assertEquals("+", tree2.getRootNode().toString());
        Assertions.assertEquals("+", tree2.getRootNode().getLeftNode().toString());
        Assertions.assertEquals("z", tree2.getRootNode().getRightNode().toString());
        Assertions.assertEquals("x", tree2.getRootNode().getLeftNode().getLeftNode().toString());
        Assertions.assertEquals("y", tree2.getRootNode().getLeftNode().getRightNode().toString());
    }

    @Test
    public void depthFirstTraversalTests() {
        SyntaxTree tree1 = new SyntaxTree("x + y");
        Assertions.assertEquals("xy+", tree1.depthFirstTraversal());

        SyntaxTree tree2 = new SyntaxTree("x + y + z");
        Assertions.assertEquals("xy+z+", tree2.depthFirstTraversal());

        SyntaxTree tree3 = new SyntaxTree("( ( ( ( ( x + y ) - v ) / x ) * y ) - z )");
        Assertions.assertEquals("xy+v-x/y*z-", tree3.depthFirstTraversal());

        SyntaxTree tree4 = new SyntaxTree("( x - y ) / ( z - m )");
        Assertions.assertEquals("xy-zm-/", tree4.depthFirstTraversal());

        SyntaxTree tree5 = new SyntaxTree("x + y + z - a * b");
        Assertions.assertEquals("xy+z+ab*-", tree5.depthFirstTraversal());

        SyntaxTree tree6 = new SyntaxTree("x - ( y + z * ( a / b ^ n ) )");
        Assertions.assertEquals("xyzabn^/*+-", tree6.depthFirstTraversal());

        SyntaxTree tree7 = new SyntaxTree("x - ( ( y + z + p ) * ( a / b ^ n ) )");
        Assertions.assertEquals("xyz+p+abn^/*-", tree7.depthFirstTraversal());

        SyntaxTree tree8 = new SyntaxTree("B * C + ( E + F * G )");
        Assertions.assertEquals("BC*EFG*++", tree8.depthFirstTraversal());
    }

    @Test
    public void inOrderTraversalTests() {
        SyntaxTree tree1 = new SyntaxTree("x + y");
        Assertions.assertEquals("x+y", tree1.inOrderTraversal());

        SyntaxTree tree2 = new SyntaxTree("x + y + z");
        Assertions.assertEquals("x+y+z", tree2.inOrderTraversal());

        SyntaxTree tree3 = new SyntaxTree("( ( ( ( ( x + y ) - v ) / x ) * y ) - z )");
        Assertions.assertEquals("(x+y-v)/x*y-z", tree3.inOrderTraversal());

        SyntaxTree tree4 = new SyntaxTree("( x - y ) / ( z - m )");
        Assertions.assertEquals("(x-y)/(z-m)", tree4.inOrderTraversal());

        SyntaxTree tree5 = new SyntaxTree("x + y + z - a * b");
        Assertions.assertEquals("x+y+z-a*b", tree5.inOrderTraversal());

        SyntaxTree tree6 = new SyntaxTree("x - ( y + z * ( a / b ^ n ) )");
        Assertions.assertEquals("x-y+z*a/b^n", tree6.inOrderTraversal());

        SyntaxTree tree7 = new SyntaxTree("x - ( ( y + z + p ) * ( a / b ^ n ) )");
        Assertions.assertEquals("x-(y+z+p)*a/b^n", tree7.inOrderTraversal());

        SyntaxTree tree8 = new SyntaxTree("B * C + ( E + F * G )");
        Assertions.assertEquals("B*C+E+F*G", tree8.inOrderTraversal());
    }

    @Test
    public void codedSyntaxTreeTests() {
        SyntaxTree tree1 = new SyntaxTree("x + y");
        Assertions.assertEquals(1, tree1.getRootNode().getOrder());

        SyntaxTree tree2 = new SyntaxTree("x + y + z");
        Assertions.assertEquals(2, tree2.getRootNode().getOrder());

        SyntaxTree tree3 = new SyntaxTree("( ( ( ( ( x + y ) - v ) / x ) * y ) - z )");
        Assertions.assertEquals(5, tree3.getRootNode().getOrder());

        SyntaxTree tree4 = new SyntaxTree("( x - y ) / ( z - m )");
        Assertions.assertEquals(3, tree4.getRootNode().getOrder());

        SyntaxTree tree5 = new SyntaxTree("x + y + z - a * b");
        Assertions.assertEquals(4, tree5.getRootNode().getOrder());

        SyntaxTree tree6 = new SyntaxTree("x - ( y + z * ( a / b ^ n ) )");
        Assertions.assertEquals(5, tree6.getRootNode().getOrder());

        SyntaxTree tree7 = new SyntaxTree("x - ( ( y + z + p ) * ( a / b ^ n ) )");
        Assertions.assertEquals(6, tree7.getRootNode().getOrder());

        SyntaxTree tree8 = new SyntaxTree("B * C + ( E + F * G )");
        Assertions.assertEquals(4, tree8.getRootNode().getOrder());
    }

    @Test
    public void simplificationAdditionTests() {
        SyntaxTree tree1 = new SyntaxTree("x + x");
        Assertions.assertEquals("2x", tree1.simplify());

        SyntaxTree tree2 = new SyntaxTree("x + x + x");
        Assertions.assertEquals("3x", tree2.simplify());

        SyntaxTree tree3 = new SyntaxTree("5 + 10");
        Assertions.assertEquals("15", tree3.simplify());

        SyntaxTree tree4 = new SyntaxTree("5x + 10");
        Assertions.assertEquals("5x+10", tree4.simplify());
    }

    @Test
    public void simplificationSubtractionTests() {
        SyntaxTree tree1 = new SyntaxTree("2x - x");
        Assertions.assertEquals("x", tree1.simplify());

        SyntaxTree tree2 = new SyntaxTree("5x - 2x - x");
        Assertions.assertEquals("2x", tree2.simplify());

        SyntaxTree tree3 = new SyntaxTree("5 - 10");
        Assertions.assertEquals("-5", tree3.simplify());

        SyntaxTree tree4 = new SyntaxTree("5 - 10 + 20");
        Assertions.assertEquals("15", tree4.simplify());
    }

    @Test
    public void simplificationMultiplyTests() {
        SyntaxTree tree1 = new SyntaxTree("x * x");
        Assertions.assertEquals("x^2", tree1.simplify());

        SyntaxTree tree2 = new SyntaxTree("x * y");
        Assertions.assertEquals("x*y", tree2.simplify());
    }

    @Test
    public void simplificationDivideTests() {
        SyntaxTree tree1 = new SyntaxTree("10 / 2");
        Assertions.assertEquals("5", tree1.simplify());

        SyntaxTree tree2 = new SyntaxTree("5x / 2");
        Assertions.assertEquals("2.5x", tree2.simplify());
    }

    @Test
    public void simplificationCombiningLikeTerms() {
        SyntaxTree treeAddition = new SyntaxTree("x + y + x + y");
        Assertions.assertEquals("2x2y+", treeAddition.simplify());

        SyntaxTree treeSubtraction = new SyntaxTree("x - y - x - y");
        Assertions.assertEquals("-2y", treeSubtraction.simplify());

        SyntaxTree treeMultiply = new SyntaxTree("x * x * y * y");
        Assertions.assertEquals("x^2y^2*", treeMultiply.simplify());
    }
}
